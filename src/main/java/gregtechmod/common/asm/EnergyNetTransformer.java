package gregtechmod.common.asm;

import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author TheDarkDnKTv
 *
 */
public class EnergyNetTransformer implements IClassTransformer {
	
	private static final List<String> workingClasses;
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		int idx = workingClasses.indexOf(name);
		
		if (idx >= 0) {
			GT_CoreMod.log.info("Trying to transform " + workingClasses.get(idx));
			
			try {
				ClassNode node = new ClassNode();
				ClassReader reader = new ClassReader(basicClass);
				reader.accept(node, 0);
				
				int flags = ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS;
				
				switch (idx) {
				case 0:
					this.transformInterface(node);
					break;
				case 1:
					this.transformChange(node);
					break;
				case 2:
					this.transformEnergyNetLocal(node);
					break;
				case 3:
					this.transformGrid(node);
					flags = ClassWriter.COMPUTE_MAXS;
					break;
				}
				
				ClassWriter writer = new ClassWriter(flags);
				node.accept(writer);
				return writer.toByteArray();
			} catch (Throwable e) {
				GT_CoreMod.log.error("Unable to transform class!");
				GT_CoreMod.log.catching(e);
			}
		}
		
		return basicClass;
	}
	
	private void transformGrid(ClassNode node) {
		/*
		 * Patching ic2.core.energy.Grid to supply already pathced ic2.core.energy.Change with Net tier
		 * btw it was just easier to replace all method body
		 *  Grid.java:323
		 *  try { 
		 *  	final Iterable<Node> result = this.calculation.get();
		 +  	int networkTier = 0;
		 +  	List<Change> changes = new ArrayList<>();
		 * 		for (Node node : result) {
		 *  
		 *      
		 * 
		 - 	 	this.energyNet.addChange(node, dir, node.getAmount(), node.getVoltage());
		 +   	if (node.nodeType == NodeType.Source && node.getTier() > networkTier) {
         +   		networkTier = node.getTier();
         +   	}
         +     
         +     changes.add(new Change(node, dir, node.getAmount(),  node.getVoltage(), networkTier));
         +   	}
         +  
         +  	for (Change change : changes)
         +  		change.netSourceTier = networkTier;
         *  	
         +  	this.energyNet.changes.addAll(changes);
		 * 
		 */

		for (MethodNode meth : node.methods) { // Rewriting full method body
			if (meth.name.equals("finishCalculation") && meth.desc.equals("()V")) {
				meth.localVariables.clear();
				meth.tryCatchBlocks.clear();
				meth.instructions.clear();
				
				final String gridClass 			= GT_CoreMod.formatName(workingClasses.get(3));
				final String changeClass 		= GT_CoreMod.formatName(workingClasses.get(1));
				final String iterableClass 		= "java/lang/Iterable";
				final String directionClass 	= "net/minecraftforge/common/util/ForgeDirection";
				final String nodeClass 			= "ic2/core/energy/Node";
				
				/*
				 <localVar:index=0 , name=this , desc=Lic2/core/energy/Grid;, sig=null, start=L1, end=L2>
		         <localVar:index=1 , name=result , desc=Ljava/lang/Iterable;, sig=Ljava/lang/Iterable<Lic2/core/energy/Node;>;, start=L3, end=L4>
		         <localVar:index=2 , name=networkTier , desc=I, sig=null, start=L5, end=L4>
		         <localVar:index=3 , name=changes , desc=Ljava/util/List;, sig=Ljava/util/List<Lic2/core/energy/Change;>;, start=L6, end=L4>
		         <localVar:index=4 , name=node , desc=Lic2/core/energy/Node;, sig=null, start=L7, end=L8>
		         <localVar:index=6 , name=dir , desc=Lnet/minecraftforge/common/util/ForgeDirection;, sig=null, start=L9, end=L10>
		         <localVar:index=6 , name=dir , desc=Lnet/minecraftforge/common/util/ForgeDirection;, sig=null, start=L11, end=L8>
		         <localVar:index=4 , name=change , desc=Lic2/core/energy/Change;, sig=null, start=L12, end=L13>
		         <localVar:index=1 , name=e , desc=Ljava/lang/InterruptedException;, sig=null, start=L14, end=L15>
		         <localVar:index=1 , name=e2 , desc=Ljava/util/concurrent/ExecutionException;, sig=null, start=L16, end=L17>
         		 <localVar:index=2 , name=ps , desc=Ljava/io/PrintStream;, sig=null, start=L18, end=L17>
				 */
				int line = 320;
				InsnList inss = new InsnList();
				LabelNode[] labels = new LabelNode[40];
				for (int i = 0; i < labels.length; i++)
					labels[i] = new LabelNode(new Label());
				
				meth.localVariables.add(new LocalVariableNode("this"		, getType(gridClass)							, null, labels[ 0], labels[ 1], 0));
				meth.localVariables.add(new LocalVariableNode("result"		, getType(iterableClass)						, null, labels[ 2], labels[ 3], 1));
				meth.localVariables.add(new LocalVariableNode("networkTier"	, "I"											, null, labels[ 4], labels[ 3], 2));
				meth.localVariables.add(new LocalVariableNode("changes"		, "Ljava/util/List;"							, null, labels[ 5], labels[ 3], 3));
				meth.localVariables.add(new LocalVariableNode("node"		, getType(nodeClass)							, null, labels[ 6], labels[ 7], 4));
				meth.localVariables.add(new LocalVariableNode("dir"			, getType(directionClass)						, null, labels[ 8], labels[ 9], 6));
				meth.localVariables.add(new LocalVariableNode("dir"			, getType(directionClass)						, null, labels[10], labels[ 7], 6));
				meth.localVariables.add(new LocalVariableNode("change"		, getType(changeClass)							, null, labels[11], labels[12], 4));
				meth.localVariables.add(new LocalVariableNode("exception"	, "Ljava/lang/InterruptedException;"			, null, labels[13], labels[14], 1));
				meth.localVariables.add(new LocalVariableNode("exception1"	, "Ljava/util/concurrent/ExecutionException;"	, null, labels[15], labels[16], 1));
				meth.localVariables.add(new LocalVariableNode("ps"			, "Ljava/io/PrintStream;"						, null, labels[17], labels[16], 2));
				
				meth.tryCatchBlocks.add(new TryCatchBlockNode(labels[18], labels[3], labels[19], "java/lang/InterruptedException"));
				meth.tryCatchBlocks.add(new TryCatchBlockNode(labels[18], labels[3], labels[20], "java/util/concurrent/ExecutionException"));
				
				inss.add(labels[0]);
				inss.add(new LineNumberNode(line, labels[0])); // 320
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new FieldInsnNode(GETFIELD, gridClass, "calculation", "Ljava/util/concurrent/Future;"));
					inss.add(new JumpInsnNode(IFNONNULL, labels[18]));
				}
				
				inss.add(labels[21]);
				inss.add(new LineNumberNode(++line, labels[21])); // 321
				{
					inss.add(new InsnNode(RETURN));
				}
				
				inss.add(labels[18]);
				inss.add(new LineNumberNode(line += 3, labels[18])); // 324
				{
					inss.add(new FrameNode(F_NEW, 1, new Object[] {gridClass}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new FieldInsnNode(GETFIELD, gridClass, "calculation", "Ljava/util/concurrent/Future;"));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/concurrent/Future", "get", "()Ljava/lang/Object;", true));
					inss.add(new TypeInsnNode(CHECKCAST, iterableClass));
					inss.add(new VarInsnNode(ASTORE, 1));
				}
				
				inss.add(labels[2]); // 325
				inss.add(new LineNumberNode(++line, labels[2]));
				{
					inss.add(new InsnNode(ICONST_0));
					inss.add(new VarInsnNode(ISTORE, 2));
				}
				
				inss.add(labels[4]); // 326
				inss.add(new LineNumberNode(++line, labels[4]));
				{	
					inss.add(new TypeInsnNode(NEW, "java/util/ArrayList"));
					inss.add(new InsnNode(DUP));
					inss.add(new MethodInsnNode(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false));
					inss.add(new VarInsnNode(ASTORE, 3));
				}
				
				inss.add(labels[5]); // 327
				inss.add(new LineNumberNode(++line, labels[5]));
				{	
					inss.add(new VarInsnNode(ALOAD, 1));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, iterableClass, "iterator", "()Ljava/util/Iterator;", true));
					inss.add(new VarInsnNode(ASTORE, 5));
					inss.add(new JumpInsnNode(GOTO, labels[7]));
				}
				
				inss.add(labels[22]); // 327, for loop brackets
				inss.add(new LineNumberNode(line, labels[22]));
				{	
					inss.add(new FrameNode(F_NEW, 6, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", 0, "java/util/Iterator"}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 5));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true));
					inss.add(new TypeInsnNode(CHECKCAST, nodeClass));
					inss.add(new VarInsnNode(ASTORE, 4));
				}
				
				inss.add(labels[6]); // 329
				inss.add(new LineNumberNode(line += 2, labels[6]));
				{	
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new FieldInsnNode(GETFIELD, nodeClass, "links", "Ljava/util/List;"));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "isEmpty", "()Z", true));
					inss.add(new JumpInsnNode(IFNE, labels[9]));
				}
				
				inss.add(labels[23]); // 330
				inss.add(new LineNumberNode(++line, labels[23]));
				{	
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new FieldInsnNode(GETFIELD, nodeClass, "links", "Ljava/util/List;"));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "get", "(I)Ljava/lang/Object;", true));
					inss.add(new TypeInsnNode(CHECKCAST, "ic2/core/energy/NodeLink"));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/energy/NodeLink", "getDirFrom", String.format("(L%s;)L%s;", nodeClass, directionClass), false));
					inss.add(new VarInsnNode(ASTORE, 6));
				}
				
				inss.add(labels[8]); // 330
				inss.add(new LineNumberNode(line, labels[8]));
				{	
					inss.add(new JumpInsnNode(GOTO, labels[24]));
				}
				
				inss.add(labels[9]); // 333
				inss.add(new LineNumberNode(line += 3, labels[9]));
				{	
					inss.add(new FrameNode(F_NEW, 6, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", nodeClass, "java/util/Iterator"}, 0, new Object[0]));
					inss.add(new FieldInsnNode(GETSTATIC, directionClass, "UNKNOWN", getType(directionClass)));
					inss.add(new VarInsnNode(ASTORE, 6));
				}
				
				inss.add(labels[10]); // 334
				inss.add(new LineNumberNode(++line, labels[10]));
				{	
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/energy/EnergyNetGlobal", "debugGrid", "Z"));
					inss.add(new JumpInsnNode(IFEQ, labels[24]));
				}
				
				inss.add(labels[25]); // 335
				inss.add(new LineNumberNode(++line, labels[25]));
				{	
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/IC2", "log", "Lic2/core/util/Log;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/util/LogCategory", "EnergyNet", "Lic2/core/util/LogCategory;"));
					inss.add(new LdcInsnNode("Can't determine direction for %s."));
					inss.add(new InsnNode(ICONST_1));
					inss.add(new TypeInsnNode(ANEWARRAY, "java/lang/Object"));
					inss.add(new InsnNode(DUP));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new InsnNode(AASTORE));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/util/Log", "warn", "(Lic2/core/util/LogCategory;Ljava/lang/String;[Ljava/lang/Object;)V", false));
				}
				
				inss.add(labels[26]); // 336
				inss.add(new LineNumberNode(++line, labels[26]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/IC2", "log", "Lic2/core/util/Log;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/util/LogCategory", "EnergyNet", "Lic2/core/util/LogCategory;"));
					inss.add(new FieldInsnNode(GETSTATIC, "org/apache/logging/log4j/Level", "DEBUG", "Lorg/apache/logging/log4j/Level;"));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/util/Log", "getPrintStream", "(Lic2/core/util/LogCategory;Lorg/apache/logging/log4j/Level;)Ljava/io/PrintStream;", false));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, gridClass, "dumpNodeInfo", "(Ljava/io/PrintStream;ZLic2/core/energy/Node;)V", false));
				}
				
				inss.add(labels[27]); // 337
				inss.add(new LineNumberNode(++line, labels[27]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, gridClass, "dumpGraph", "(Z)V", false));
				}
				
				inss.add(labels[24]); // 341
				inss.add(new LineNumberNode(line += 4, labels[24]));
				{
					inss.add(new FrameNode(F_NEW, 7, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", nodeClass, "java/util/Iterator", directionClass}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new FieldInsnNode(GETFIELD, nodeClass, "nodeType", "Lic2/core/energy/NodeType;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/energy/NodeType", "Source", "Lic2/core/energy/NodeType;"));
					inss.add(new JumpInsnNode(IF_ACMPNE, labels[28]));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, nodeClass, "getTier", "()I", false));
					inss.add(new VarInsnNode(ILOAD, 2));
					inss.add(new JumpInsnNode(IF_ICMPLE, labels[28]));
				}
				
				inss.add(labels[29]); // 342
				inss.add(new LineNumberNode(++line, labels[29]));
				{
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, nodeClass, "getTier", "()I", false));
					inss.add(new VarInsnNode(ISTORE, 2));
				}
				
				inss.add(labels[28]); // 345
				inss.add(new LineNumberNode(line += 3, labels[28]));
				{
					inss.add(new FrameNode(F_NEW, 7, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", nodeClass, "java/util/Iterator", directionClass}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 3));
					inss.add(new TypeInsnNode(NEW, changeClass));
					inss.add(new InsnNode(DUP));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new VarInsnNode(ALOAD, 6));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, nodeClass, "getAmount", "()D", false));
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, nodeClass, "getVoltage", "()D", false));
					inss.add(new VarInsnNode(ILOAD, 2));
					inss.add(new MethodInsnNode(INVOKESPECIAL, changeClass, "<init>", String.format("(L%s;L%s;DDI)V", nodeClass, directionClass), false));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true));
					inss.add(new InsnNode(POP));
				}
				
				inss.add(labels[7]); // 348
				inss.add(new LineNumberNode(line += 3, labels[7]));
				{
					inss.add(new FrameNode(F_NEW, 6, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", 0, "java/util/Iterator"}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 5));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true));
					inss.add(new JumpInsnNode(IFNE, labels[22]));
				}
				
				inss.add(labels[30]); // 348
				inss.add(new LineNumberNode(line, labels[30]));
				{
					inss.add(new VarInsnNode(ALOAD, 3));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true));
					inss.add(new VarInsnNode(ASTORE, 5));
					inss.add(new JumpInsnNode(GOTO, labels[12]));
				}
				
				inss.add(labels[31]); // 348
				inss.add(new LineNumberNode(line, labels[31]));
				{
					inss.add(new FrameNode(F_NEW, 6, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", 0, "java/util/Iterator"}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 5));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true));
					inss.add(new TypeInsnNode(CHECKCAST, changeClass));
					inss.add(new VarInsnNode(ASTORE, 4));
				}
				
				inss.add(labels[11]); // 349
				inss.add(new LineNumberNode(++line, labels[11]));
				{
					inss.add(new VarInsnNode(ALOAD, 4));
					inss.add(new VarInsnNode(ILOAD, 2));
					inss.add(new FieldInsnNode(PUTFIELD, changeClass, "netSourceTier", "I"));
				}
				
				inss.add(labels[12]); // 350
				inss.add(new LineNumberNode(++line, labels[12]));
				{
					inss.add(new FrameNode(F_NEW, 6, new Object[] {gridClass, "java/lang/Iterable", 1, "java/util/List", 0, "java/util/Iterator"}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 5));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true));
					inss.add(new JumpInsnNode(IFNE, labels[31]));
				}
				
				inss.add(labels[32]); // 352
				inss.add(new LineNumberNode(line += 2, labels[32]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new FieldInsnNode(GETFIELD, gridClass, "energyNet", "Lic2/core/energy/EnergyNetLocal;"));
					inss.add(new FieldInsnNode(GETFIELD, "ic2/core/energy/EnergyNetLocal", "changes", "Ljava/util/List;"));
					inss.add(new VarInsnNode(ALOAD, 3));
					inss.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/List", "addAll", "(Ljava/util/Collection;)Z", true));
					inss.add(new InsnNode(POP));
				}
				
				inss.add(labels[3]); // 353
				inss.add(new LineNumberNode(++line, labels[3]));
				{
					inss.add(new JumpInsnNode(GOTO, labels[16]));
				}
				
				inss.add(labels[19]); // 354 first catch
				inss.add(new LineNumberNode(++line, labels[19]));
				{
					inss.add(new FrameNode(F_NEW, 1, new Object[] {gridClass}, 1, new Object[] {"java/lang/InterruptedException"}));
					inss.add(new VarInsnNode(ASTORE, 1));
				}
				
				inss.add(labels[13]); // 355 first catch body
				inss.add(new LineNumberNode(++line, labels[13]));
				{
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/IC2", "log", "Lic2/core/util/Log;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/util/LogCategory", "EnergyNet", "Lic2/core/util/LogCategory;"));
					inss.add(new VarInsnNode(ALOAD, 1));
					inss.add(new LdcInsnNode("Calculation interrupted."));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/util/Log", "debug", "(Lic2/core/util/LogCategory;Ljava/lang/Throwable;Ljava/lang/String;)V", false));
				}
				
				inss.add(labels[14]); // 356 go to end of method
				inss.add(new LineNumberNode(++line, labels[14]));
				{
					inss.add(new JumpInsnNode(GOTO, labels[16]));
				}
				
				inss.add(labels[20]); // 357 second catch
				inss.add(new LineNumberNode(++line, labels[20]));
				{
					inss.add(new FrameNode(F_NEW, 1, new Object[] {gridClass}, 1, new Object[] {"java/util/concurrent/ExecutionException"}));
					inss.add(new VarInsnNode(ASTORE, 1));
				}
				
				inss.add(labels[15]); // 358 second catch body 
				inss.add(new LineNumberNode(++line, labels[15]));
				{
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/IC2", "log", "Lic2/core/util/Log;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/util/LogCategory", "EnergyNet", "Lic2/core/util/LogCategory;"));
					inss.add(new VarInsnNode(ALOAD, 1));
					inss.add(new LdcInsnNode("Calculation interrupted."));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/util/Log", "debug", "(Lic2/core/util/LogCategory;Ljava/lang/Throwable;Ljava/lang/String;)V", false));
				}
				
				inss.add(labels[33]); // 359
				inss.add(new LineNumberNode(++line, labels[33]));
				{
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/IC2", "log", "Lic2/core/util/Log;"));
					inss.add(new FieldInsnNode(GETSTATIC, "ic2/core/util/LogCategory", "EnergyNet", "Lic2/core/util/LogCategory;"));
					inss.add(new FieldInsnNode(GETSTATIC, "org/apache/logging/log4j/Level", "WARN", "Lorg/apache/logging/log4j/Level;"));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, "ic2/core/util/Log", "getPrintStream", "(Lic2/core/util/LogCategory;Lorg/apache/logging/log4j/Level;)Ljava/io/PrintStream;", false));
					inss.add(new VarInsnNode(ASTORE, 2));
				}
				
				inss.add(labels[17]); // 360
				inss.add(new LineNumberNode(++line, labels[17]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new VarInsnNode(ALOAD, 2));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, gridClass, "dumpStats", "(Ljava/io/PrintStream;Z)V", false));
				}
				
				inss.add(labels[34]); // 361
				inss.add(new LineNumberNode(++line, labels[34]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new VarInsnNode(ALOAD, 2));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new InsnNode(ICONST_1));
					inss.add(new InsnNode(ICONST_1));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, gridClass, "dumpMatrix", "(Ljava/io/PrintStream;ZZZ)V", false));
				}
				
				inss.add(labels[35]); // 362
				inss.add(new LineNumberNode(++line, labels[35]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new InsnNode(ICONST_0));
					inss.add(new MethodInsnNode(INVOKEVIRTUAL, gridClass, "dumpGraph", "(Z)V", false));
				}
				
				inss.add(labels[36]); // 363
				inss.add(new LineNumberNode(++line, labels[36]));
				{
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new InsnNode(ICONST_1));
					inss.add(new FieldInsnNode(PUTFIELD, gridClass, "failed", "Z"));
				}
				
				inss.add(labels[16]); // 365
				inss.add(new LineNumberNode(line =+ 2, labels[16]));
				{
					inss.add(new FrameNode(F_NEW, 1, new Object[] {gridClass}, 0, new Object[0]));
					inss.add(new VarInsnNode(ALOAD, 0));
					inss.add(new InsnNode(ACONST_NULL));
					inss.add(new FieldInsnNode(PUTFIELD, gridClass, "calculation", "Ljava/util/concurrent/Future;"));
				}
				
				inss.add(labels[37]); // 366 return
				inss.add(new LineNumberNode(++line, labels[37]));
				{
					inss.add(new InsnNode(RETURN));
				}
				
				inss.add(labels[1]); // 366 return
				inss.add(new LineNumberNode(line, labels[1]));
				
				meth.instructions.add(inss);
			}
		}
	}
	
	private void transformEnergyNetLocal(ClassNode node) {
		/*
		 * Changing constructor call's signature in ic2.core.energy.EnergyNetLocal#addChange
		 * Refresh to new Change class constructor signature, putting 0 on last arg for compat
		 * Aksi changing call of IEnergySink#injectEnergy to default method
		 * 
		 */
		
		final String sinkClass = GT_CoreMod.formatName(workingClasses.get(0));
		final String changeClass = GT_CoreMod.formatName(workingClasses.get(1));
		
		for (MethodNode meth : node.methods) {
			if ((meth.name.equals("addChange") && meth.desc.equals("(Lic2/core/energy/Node;Lnet/minecraftforge/common/util/ForgeDirection;DD)V")) || meth.name.equals("processChanges")) {
				boolean patched = false;
				
				for (int i = 0; i < meth.instructions.size(); i++) {
					AbstractInsnNode ins = meth.instructions.get(i);
					if (ins.getOpcode() == INVOKESPECIAL || ins.getOpcode() == INVOKEINTERFACE) {
						MethodInsnNode call = (MethodInsnNode) ins;
						
						if (call.name.equals("<init>") && call.owner.equals(changeClass)) {
							GT_CoreMod.log.info("EnergyNetLocal#addChange constructor signature fixed");
							meth.instructions.insertBefore(ins, new InsnNode(ICONST_0));
							call.desc = "(Lic2/core/energy/Node;Lnet/minecraftforge/common/util/ForgeDirection;DDI)V";
							patched = true;
							break;
						} else if (call.name.equals("injectEnergy") && call.owner.equals(sinkClass)) {
							GT_CoreMod.log.info("EnergyNetLocal#processChanges changed call of IEnergySink#injectEnergy to default generated method");
							meth.instructions.insertBefore(ins, new VarInsnNode(ALOAD, 2));
							meth.instructions.insertBefore(ins, new FieldInsnNode(GETFIELD, changeClass, "netSourceTier", "I"));
							call.desc = "(Lnet/minecraftforge/common/util/ForgeDirection;DDI)D";
							patched = true;
							break;
						}
					}
				}
				
				if (!patched) throw new IllegalStateException("Can not find method patch point for " + meth.name);
			}
		}
	}
	
	private void transformChange(ClassNode node) {
		/*
		 * Changing signarute of constructor ic2.core.energy.Change and adding member
		 * 
		 * public int netSourceTier;
		 * 
		 *  Change(Node node, ForgeDirections dir, double amount, double voltage, int netSourceTier) {
		 * 		*
		 * 		*
		 * 		*
		 * 		this.netSourceTier = netSourceTier;
		 *  }
		 * 
		 */
		
		final String oldDesc = "(Lic2/core/energy/Node;Lnet/minecraftforge/common/util/ForgeDirection;DD)V";
		final String newDesc = "(Lic2/core/energy/Node;Lnet/minecraftforge/common/util/ForgeDirection;DDI)V";
		final String filedName = "netSourceTier";
		
		node.fields.add(new FieldNode(ACC_PUBLIC, filedName, "I", null, null));
		
		for (MethodNode meth : node.methods) {
			if (meth.name.equals("<init>") && meth.desc.equals(oldDesc)) {
				meth.desc = newDesc;
				LocalVariableNode var = meth.localVariables.get(0);
				meth.localVariables.add(new LocalVariableNode("netSourceTier", "I", null, var.start, var.end, 7));
				
				InsnList list = new InsnList();
				{
					list.add(new LabelNode(new Label()));
					list.add(new VarInsnNode(ALOAD, 0));
					list.add(new VarInsnNode(ILOAD, 7));
					list.add(new FieldInsnNode(PUTFIELD, "ic2/core/energy/Change", filedName, "I"));
				}
				
				meth.instructions.insertBefore(meth.instructions.get(meth.instructions.size() - 3 - 1), list);
				return;
			}
		}
	}
	
	private void transformInterface(ClassNode node) {
		/*
		 * Adding a default method in ic2.api.energy.tile.IEnergySink
		 * 
		 *  default double injectEnergy(ForgeDirection directionFrom, double amount, double voltage, int netMaxSouceTier) {
		 *		return this.injectEnergy(directionFrom, amount, voltage);
		 *  }
		 * 
		 */
		
		final String clazz = GT_CoreMod.formatName(workingClasses.get(0));
		final String methName = "injectEnergy";
		node.version = V1_8;
		
		MethodNode newMeth = (MethodNode) node.visitMethod(ACC_PUBLIC, methName, "(Lnet/minecraftforge/common/util/ForgeDirection;DDI)D", null, new String[0]);
		LabelNode L1 = new LabelNode(new Label());
		LabelNode L2 = new LabelNode(new Label());
		
		newMeth.localVariables.add(new LocalVariableNode("this", String.format("L%s;", clazz), null, L1, L2, 0));
		newMeth.localVariables.add(new LocalVariableNode("directionFrom", "Lnet/minecraftforge/common/util/ForgeDirection;", null, L1, L2, 1));
		newMeth.localVariables.add(new LocalVariableNode("amount", "D", null, L1, L2, 2));
		newMeth.localVariables.add(new LocalVariableNode("voltage", "D", null, L1, L2, 4));
		newMeth.localVariables.add(new LocalVariableNode("netMaxSouceTier", "I", null, L1, L2, 6));
		
		newMeth.instructions.add(L1);
		{
			newMeth.instructions.add(new VarInsnNode(ALOAD, 0));	
			newMeth.instructions.add(new VarInsnNode(ALOAD, 1));
			newMeth.instructions.add(new VarInsnNode(DLOAD, 2));
			newMeth.instructions.add(new VarInsnNode(DLOAD, 4));
			newMeth.instructions.add(new MethodInsnNode(INVOKEINTERFACE, clazz, methName, "(Lnet/minecraftforge/common/util/ForgeDirection;DD)D", true));
			newMeth.instructions.add(new InsnNode(DRETURN));
		}
		newMeth.instructions.add(L2);
	}
	
	private String getType(String clz) {
		return String.format("L%s;", clz);
	}
	
	static {
		workingClasses = Arrays.asList(new String[] {
				GT_CoreMod.getIC2APIClass("energy.tile.IEnergySink"),
				GT_CoreMod.getIC2CoreClass("energy.Change"),
				GT_CoreMod.getIC2CoreClass("energy.EnergyNetLocal"),
				GT_CoreMod.getIC2CoreClass("energy.Grid")
		});
	}
}
