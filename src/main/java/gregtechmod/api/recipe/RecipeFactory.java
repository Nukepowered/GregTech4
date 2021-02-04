package gregtechmod.api.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * A Factory class for creating Recipe instances
 * @author TheDarkDnKTv
 *
 */
public class RecipeFactory {
	
	private final RecipeMap map;
	private final List<ItemStack> outputItems;
	private final List<ChancedOutput> chancedOutput;
	private final List<Ingredient> inputItems;
	private int EUt;
	private int startEU;
	private int duration;
	// TODO maybe create a fluid I/O
	
	RecipeFactory(RecipeMap map) {
		this.map 			= map;
		this.outputItems 	= new ArrayList<>();
		this.chancedOutput 	= new ArrayList<>();
		this.inputItems 	= new ArrayList<>();
		EUt = -1;
		startEU = 0;
		duration = -1;
	}
	
	
}
