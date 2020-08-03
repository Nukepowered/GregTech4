package gregtechmod.common.items;

import gregtechmod.api.items.GT_Generic_Item;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public class GT_NeutronReflector_Item extends GT_Generic_Item implements IReactorComponent {
	
    public GT_NeutronReflector_Item(int aID, String aName, int aMaxDamage) {
        super(aID, aName, null);
        setMaxStackSize(64);
    	setMaxDamage(aMaxDamage);
    }
    
	@Override
	public void processChamber(IReactor aReactor, ItemStack aStack, int x, int y) {
		return;
	}
	
	@Override
	public boolean acceptUraniumPulse(IReactor aReactor, ItemStack aStack, ItemStack pulsingStack, int x, int y, int pulseX, int pulseY) {
		if (aStack.stackSize > 1) return false;
		((IReactorComponent)pulsingStack.getItem()).acceptUraniumPulse(aReactor, pulsingStack, aStack, pulseX, pulseY, x, y);
		if (getMaxDamage() > 0)
			if (aStack.getItemDamage() + 1 >= getMaxDamage())
				aReactor.setItemAt(x, y, null);
			else
				aStack.setItemDamage(aStack.getItemDamage() + 1);
		return true;
	}
	
	@Override
	public boolean canStoreHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return false;
	}
	
	@Override
	public int getMaxHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return 0;
	}
	
	@Override
	public int getCurrentHeat(IReactor aReactor, ItemStack aStack, int x, int y) {
		return 0;
	}
	
	@Override
	public float influenceExplosion(IReactor aReactor, ItemStack aStack) {
		return -1.0F;
	}
	
	@Override
    public int alterHeat(IReactor aReactor, ItemStack aStack, int x, int y, int aHeat) {
        return aHeat;
    }
	
    private void setHeatForStack(ItemStack aStack, int aHeat) {
    	
    }
    
    private int getHeatOfStack(ItemStack aStack) {
    	return 0;
    }
}
