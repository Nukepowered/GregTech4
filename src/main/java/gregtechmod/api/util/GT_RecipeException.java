package gregtechmod.api.util;

import gregtechmod.api.recipe.Recipe;

import java.util.Objects;

/**
 * @author TheDarkDnKTv
 *
 */
public class GT_RecipeException extends RuntimeException {

	private static final long serialVersionUID = -7856745962508588421L;

	private final Recipe recipe;
	
	public GT_RecipeException(Recipe recipe, String reason) {
		super(reason);
		this.recipe = Objects.requireNonNull(recipe);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipe validation error:\n");
		builder.append(this.getMessage());
		builder.append('\n');
		builder.append(this.recipe.toString());
		return builder.toString();
	}
}
