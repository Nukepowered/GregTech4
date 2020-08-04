package gregtechmod.common;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import net.minecraft.client.audio.SoundPoolEntry;

public class GT_SoundAdder {
	public GT_SoundAdder() {
		net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
	}
	@SuppressWarnings("deprecation")
	@net.minecraftforge.event.ForgeSubscribe
    public void onSound(net.minecraftforge.client.event.sound.SoundLoadEvent event) {
		try {
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/chainsaw/ChainsawIdle" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/chainsaw/ChainsawStop" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/chainsaw/ChainsawUseOne" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/chainsaw/ChainsawUseTwo" + ".ogg");
			
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/drill/DrillHard" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/drill/DrillSoft" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/drill/DrillUseLoop" + ".ogg");
			
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/jetpack/JetpackFire" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/jetpack/JetpackLoop" + ".ogg");
			
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/mininglaser/MiningLaser" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/mininglaser/MiningLaserExplosive" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/mininglaser/MiningLaserLongRange" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/mininglaser/MiningLaserLowFocus" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/mininglaser/MiningLaserScatter" + ".ogg");
			
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/nanosabre/NanosabreIdle" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/nanosabre/NanosabrePowerup" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/nanosabre/NanosabreSwing1" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/nanosabre/NanosabreSwing2" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/nanosabre/NanosabreSwing3" + ".ogg");
			
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/quantumsuit/HelmetLoop" + ".ogg");
			event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/quantumsuit/QuantumsuitBoots" + ".ogg");
			
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/BatteryUse" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/Dynamiteomote" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/InsulationCutters" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/NukeExplosion" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/ODScanner" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/Painter" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/RubberTrampoline" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/Treetap" + ".ogg");
	        event.manager.soundPoolSounds.addSound(GregTech_API.IC2_MOD_ID + ":" + "tools/Wrench" + ".ogg");
		} catch(Throwable e) {
			GT_Log.log.catching(e);
		}
    }
}