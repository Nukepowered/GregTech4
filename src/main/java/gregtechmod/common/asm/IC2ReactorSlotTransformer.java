package gregtechmod.common.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraft.launchwrapper.IClassTransformer;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author TheDarkDnKTv
 *
 */
public class IC2ReactorSlotTransformer implements IClassTransformer {
	
	private final static String TE_REACTOR = GT_CoreMod.getIC2CoreClass("block.reactor.tileentity.TileEntityNuclearReactorElectric");
	private final static String STACK_TYPE = FMLDeobfuscatingRemapper.INSTANCE.map("net/minecraft/item/ItemStack");
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if (TE_REACTOR.equals(name)) {
			ClassReader reader = new ClassReader(basicClass);
			ClassNode clz = new ClassNode();
			reader.accept(clz, 0);

			for (MethodNode method : clz.methods) {
				if (method.name.equals("isUsefulItem") && method.access == ACC_PUBLIC && method.desc.equals(String.format("(L%s;Z)Z", STACK_TYPE))) { // (Ladd;Z)Z
					GT_CoreMod.log.info("Transforming " + TE_REACTOR);
					try {
						for (int i = 0; i < method.instructions.size(); i++) {
							AbstractInsnNode ins = method.instructions.get(i);
							if (ins.getOpcode() == ALOAD && ins.getNext().getOpcode() == INVOKEVIRTUAL && ((MethodInsnNode)ins.getNext()).name.equals("getClass")) {
								ins = ins.getNext().getNext().getNext(); // IF_ACMPNE L5
								LabelNode next = ((JumpInsnNode) ins).label;
								LabelNode lb = new LabelNode(new Label());
								((JumpInsnNode) ins).label = lb;
								
								InsnList list = new InsnList();
								{
									final String GT = "gregtechmod/api/interfaces/IHeatComponent";
									list.add(lb);
									list.add(new LineNumberNode(301, lb));
									list.add(new VarInsnNode(ALOAD, 3));
									list.add(new TypeInsnNode(INSTANCEOF, GT));
									list.add(new JumpInsnNode(IFEQ, next));
									list.add(new VarInsnNode(ALOAD, 3));
									list.add(new TypeInsnNode(CHECKCAST, GT));
									list.add(new VarInsnNode(ALOAD, 1));
									list.add(new MethodInsnNode(INVOKEINTERFACE, GT, "getHeatStored", String.format("(L%s;)I", STACK_TYPE), true));
									list.add(new JumpInsnNode(IFLE, next));
									list.add(new InsnNode(ICONST_0));
									list.add(new InsnNode(IRETURN));
								}
								
								method.instructions.insertBefore(next, list);
								ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
								clz.accept(writer);
								return writer.toByteArray();
							}
						}
						
						throw new NoSuchMethodException("Not found entry signature in isUsefulItem");
					} catch (Throwable e) {
						GT_CoreMod.log.error("Error on transformation");
						GT_CoreMod.log.catching(e);
					}
				}
			}
		}
		
		
		return basicClass;
	}
}
