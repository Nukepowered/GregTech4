package gregtechmod.api.gui;

import java.util.List;
import java.util.Objects;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Utility;
import gregtechmod.api.util.ListAdapter;
import gregtechmod.common.render.GTRenderHelper;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/** Fluid slot implementation
 * 	Some shitcode, since better way to create fully own child-class of GuiScreen
 * 	For faster inslot update in GT_Container, using a List of this slots. And added to usual slot list to activate onSlotClicked
 * @author TheDarkDnKTv
 *
 */
public class GT_FluidSlot extends Slot {
	
	@SideOnly(Side.CLIENT)
	protected static ResourceLocation DEFAULT_SLOT_OVERLAY;
	
	@SideOnly(Side.CLIENT)
	protected ResourceLocation customOverlay;
	protected List<FluidStack> fluidInvenotry;
	protected boolean renderOverlay;
	protected boolean renderAmount;
	
	/** Index of internal mFluid array */
	public final int fluidIdx;
	public final boolean canDrain;
	public final boolean canFill;
	
	static {
		if (FMLCommonHandler.instance().getSide().isClient()) {
			DEFAULT_SLOT_OVERLAY = new ResourceLocation(GregTech_API.GUI_PATH + "overlays/FluidSlot.png");
		}
	}
	
	public GT_FluidSlot(IGregTechTileEntity te, int slotIndex, int x, int y, int fluidIdx) {
		this(te, slotIndex, x, y, fluidIdx, true, true);
	}
	
	public GT_FluidSlot(IGregTechTileEntity te, int slotIndex, int x, int y, int fluidIdx, boolean canDrain, boolean canFill) {
		super(te, slotIndex, x, y);
		this.fluidIdx = fluidIdx;
		this.canDrain = canDrain;
		this.canFill = canFill;
		this.renderOverlay = true;
		this.renderAmount = true;
		this.fluidInvenotry = new ListAdapter<>(((GT_MetaTileEntity_BasicTank)te.getMetaTileEntity()).mFluid);
	}
	
	/*
	 *  According on shitcode, need to deny any other slot interraction
	 */
	
	@Override
	public boolean isItemValid(ItemStack aStack) {
		return false;
	}
	
	@Override
	public boolean getHasStack() {
		return false;
	}
	
	@Override
	public void putStack(ItemStack stack) {}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}
	
	/**
	 * Drawing slot on UI
	 * @param uiLeft
	 * @param uiTop
	 * @param isMouseOver
	 */
	@SideOnly(Side.CLIENT)
	public void draw(int uiLeft, int uiTop, boolean isMouseOver) {
		int posX = this.xDisplayPosition + uiLeft;
        int posY = this.yDisplayPosition + uiTop;
		
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT);

		// Overlay render
		if (renderOverlay) {
			GTRenderHelper.bindTexture(customOverlay == null ? DEFAULT_SLOT_OVERLAY : customOverlay);
			GTRenderHelper.drawQuad(posX - 1, posY - 1, 40, 18, 18, 0.0F, 1.0F, 0.0F, 1.0F);
		}
		
		// Fluid render
		FluidStack fluid = getFluid();
		if (GT_Utility.isFluidStackValid(fluid)) {
			IIcon text = fluid.getFluid().getIcon(fluid);
			if (text != null) {
				GTRenderHelper.bindTexture(TextureMap.locationBlocksTexture);
				GTRenderHelper.drawQuad(posX, posY, 50, text);
			}
			
			// Amount render
			if (renderAmount) {
			int amount = fluid.amount / 1000;
			if (amount > 0) 
				GTRenderHelper.drawStackAmount(posX, posY, 0xFFFFFF, Integer.toString(amount));
			}
		}
		
		if (isMouseOver)
			GTRenderHelper.drawSlotDim(posX, posY, 51, 16, 16);
		
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
	
	/**
	 * Called any time slot being clicked
	 * @param mouseClick
	 * @param shiftHold
	 * @param player
	 * @return
	 */
	public boolean onClick(int mouseClick, int shiftHold, EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		ItemStack held = inv.getItemStack();
		
		if (GT_Utility.isStackValid(held)) {
			FluidStack fluid = GT_Utility.getFluidForFilledItem(held);
			FluidStack fluidInv = getFluid();
			int amount;
			if (mouseClick == 1 && canDrain && fluid != null) { // Fill (Right click to fill container with liquid)
				amount = Math.min(getSpace(), fluid.amount);
				if (amount > 0) {
					if (!GT_Utility.isFluidStackValid(fluidInv)) { // Fill empty slot
						FluidStack actual = fluid.copy();
						actual.amount = amount;
						
						if (this.useFluidContainer(inv, held, amount)) {
							setFluid(actual);
							return true;
						}
					} else if (fluidInv.isFluidEqual(fluid)) { // Add fluid if same
						if (this.useFluidContainer(inv, held, amount)) {
							fluidInv.amount += amount;
							return true;
						}
					}
				}
			} else if (mouseClick == 0 && canFill && GT_Utility.isFluidStackValid(fluidInv)) {  // Epmty (Left click to drain liquid from container)
				if ((amount = this.tryFill(inv, held, fluidInv.copy())) > 0) {
					fluidInv.amount -= amount;
					if (fluidInv.amount <= 0) 
						setFluid(null);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @param inv Inventory
	 * @param filled stack (some bucket/cell)
	 * @param amount of fluid need to be consumed
	 * @return true if consumed successfully and inventory changed
	 */
	protected boolean useFluidContainer(InventoryPlayer inv, ItemStack filled, int amount) {
		if (filled.getItem() instanceof IFluidContainerItem) {
			FluidStack drained = ((IFluidContainerItem)filled.getItem()).drain(filled, amount, false);
			if (drained != null && drained.amount == amount) {
				((IFluidContainerItem)filled.getItem()).drain(filled, amount, true);
				return true;
			}
		} else if (FluidContainerRegistry.getContainerCapacity(filled) == amount) {
			ItemStack empty = FluidContainerRegistry.drainFluidContainer(filled);
			if (empty != null && this.exchangeStacks(inv, empty)) {
				return true;
			}
		}
		
		return false;
	}
	
	protected int tryFill(InventoryPlayer inv, ItemStack empty, FluidStack fluid) {
		int amount = 0;
		ItemStack filled;
		if (empty.getItem() instanceof IFluidContainerItem) {
			if (empty.stackSize > 1) {
				filled = GT_Utility.copyAmount(1, empty);
				amount = ((IFluidContainerItem)empty.getItem()).fill(filled, fluid, true);
				if (amount > 0) {
					if (!this.exchangeStacks(inv, filled))
						return 0;
				}
			} else {
				amount = ((IFluidContainerItem)empty.getItem()).fill(empty, fluid, true);
			}
		} else {
			filled = FluidContainerRegistry.fillFluidContainer(fluid, empty);
			amount = FluidContainerRegistry.getContainerCapacity(filled);
			if (amount > 0) {
				if (!this.exchangeStacks(inv, filled))
					return 0;
			}
		}
		
		return amount;
	}
	
	/**
	 * Tries to change held item to stack, otherwise tries to put in inventory
	 * @param inv Invenotry
	 * @param stack to try chage held item to
	 * @return true inventory changed
	 */
	protected boolean exchangeStacks(InventoryPlayer inv, ItemStack stack) {
		ItemStack held = inv.getItemStack();
		if (held.stackSize == 1) {
			inv.setItemStack(stack);
		} else {
			if (!inv.addItemStackToInventory(stack))
				return false;
			held.stackSize--;
		}
		
		return true;
	}
	
	/**
	 * @return free space to fill
	 */
	protected int getSpace() {
		IGregTechTileEntity te = (IGregTechTileEntity)inventory;
		int availableSpace = 0;
		
		if (te.getMetaTileEntity() instanceof GT_MetaTileEntity_BasicTank) {
			availableSpace = ((GT_MetaTileEntity_BasicTank)te.getMetaTileEntity()).getCapacity();
		} else
			availableSpace = this.getSlotStackLimit() * 1000;
		
		FluidStack fluid = getFluid();
		if (GT_Utility.isFluidStackValid(fluid))
			availableSpace -= fluid.amount;
		return availableSpace;
	}
	
	/**
	 * Disable or enable overlay rendering
	 * Default value set to true
	 */
	public GT_FluidSlot setRenderOverlay(boolean value) {
		this.renderOverlay = value;
		return this;
	}
	
	/**
	 * Disable or enable amount string rendering
	 * Default value set to true
	 */
	public GT_FluidSlot setRenderAmount(boolean value) {
		this.renderAmount = value;
		return this;
	}
	
	/**
	 * Set render overlay image
	 * @param overlay
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public GT_FluidSlot setRenderOverlay(ResourceLocation overlay) {
		this.customOverlay = Objects.requireNonNull(overlay);
		return this;
	}
	
	public FluidStack getFluid() {
		return fluidInvenotry.get(fluidIdx);
	}
	
	public void setFluid(FluidStack fluid) {
		fluidInvenotry.set(fluidIdx, fluid);
	}
}
