package gregtechmod.api.util;

/**
 * @author TheDarkDnKTv
 *
 */
public class GT_RecipeException extends RuntimeException {

	private static final long serialVersionUID = -7856745962508588421L;
	
	public GT_RecipeException(String reason) {
		super(reason);
	}
	
	@Override
	public String toString() {
		return "Something gone wrong trying do stuff around recipes:\n" + this.getMessage();
	}
}
