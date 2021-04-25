package gregtechmod.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

import static org.objectweb.asm.Opcodes.*;

/** 
 * Hook for anvils to work with GT tools
 * @author TheDarkDnKTv
 *
 */
public class AnvilTransformer implements IClassTransformer {
	
	private static final String[] names = {
			"updateRepairOutput",
			"getItem"
	};
	
	private static final String[] namesObf = {
			"func_82848_d",
			"func_77973_b"
	};
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (transformedName.equals("net.minecraft.inventory.ContainerRepair")) {
			GT_CoreMod.log.info("Transforming " + transformedName);
			
			try {
				ClassNode node = new ClassNode();
				ClassReader reader = new ClassReader(basicClass);
				reader.accept(node, 0);
				this.transform(node, name.equals(transformedName) ? names : namesObf);
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
				node.accept(writer);
				return writer.toByteArray();
			} catch (Throwable e) {
				GT_CoreMod.log.error("Unable to transform class!");
				GT_CoreMod.log.catching(e);
			}
		}
		
		
		return basicClass;
	}
	
	private void transform(ClassNode node, String[] names) {
		for (MethodNode meth : node.methods) {
			if (meth.name.equals(names[0]) && meth.desc.equals("()V")) { // updateRepairOutput func_82848_d
				for (int i = 0; i < meth.instructions.size(); i++) {
					AbstractInsnNode ins = meth.instructions.get(i);
					if (ins.getOpcode() == GETFIELD && ins.getNext().getOpcode() == GETFIELD && ins.getNext().getNext().getOpcode() == IFNE && ((FieldInsnNode)ins).owner.equals("net/minecraft/entity/player/EntityPlayer")) {
						ins = ins.getNext().getNext();
						JumpInsnNode jmp = (JumpInsnNode) meth.instructions.get(i + 6);
						LabelNode jmpInto = ((JumpInsnNode) ins).label;
						LabelNode jmpAfter = jmp.label;
						
						meth.instructions.set(jmp, jmp = new JumpInsnNode(IF_ACMPEQ, jmpInto));
						
						InsnList list = new InsnList();
						{
							list.add(new VarInsnNode(ALOAD, 1));
							list.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraft/item/ItemStack", names[1], "()Lnet/minecraft/item/Item;", false)); 
							list.add(new TypeInsnNode(INSTANCEOF, "gregtechmod/api/items/GT_Tool_Item"));
							list.add(new JumpInsnNode(IFEQ, jmpAfter)); // jump after block if item not instanceof GT_Tool_Item
							list.add(new VarInsnNode(ALOAD, 1));		// Loading itemstack in stack
							list.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraft/item/ItemStack", names[1], "()Lnet/minecraft/item/Item;", false)); 
							list.add(new TypeInsnNode(CHECKCAST, "gregtechmod/api/items/GT_Tool_Item"));
							list.add(new VarInsnNode(ALOAD, 16));	 	// Loading enchant in stack
							list.add(new VarInsnNode(ALOAD, 1)); 		// Loading itemstack in stack
							list.add(new MethodInsnNode(INVOKEVIRTUAL, "gregtechmod/api/items/GT_Tool_Item", "checkEnchant", "(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)Z", false));
							list.add(new JumpInsnNode(IFEQ, jmpAfter));
						}
						meth.instructions.insert(jmp, list);
						return;
					}
				}
			}
		}
		
		GT_CoreMod.log.error("Class wasnt transformed");
	}
}
