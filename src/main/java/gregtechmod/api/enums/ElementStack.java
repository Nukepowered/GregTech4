package gregtechmod.api.enums;

public class ElementStack implements Cloneable {
	public static final char[] formulaNumbers = new char[] { '₀', '₁', '₂', '₃', '₄', '₅', '₆', '₇', '₈', '₉' };
	
	public int mAmount;
	public Element mElement;
	
	public ElementStack(Element aElement, int aAmount) {
		mElement = aElement==null?Element._NULL:aElement;
		mAmount = aAmount;
	}
	
	public ElementStack copy(int aAmount) {
		return new ElementStack(mElement, aAmount);
	}
	
	public String getLowIndex() {
		char[] chars = Integer.toString(mAmount).toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int index = Integer.parseInt(chars[i] + "");
			chars[i] = formulaNumbers[index];
		}
		
		return String.valueOf(chars);
	}
	
	@Override
	public ElementStack clone() {
		return new ElementStack(mElement, mAmount);
	}
	
	@Override
	public boolean equals(Object aObject) {
		if (aObject == this) return true;
		if (aObject == null) return false;
		if (aObject instanceof Element) return aObject == mElement;
		if (aObject instanceof ElementStack) return ((ElementStack)aObject).mElement == mElement && (mAmount < 0 || ((ElementStack)aObject).mAmount < 0 || ((ElementStack)aObject).mAmount == mAmount);
		return false;
	}
	
	@Override
	public String toString() {
		return mElement.toString() + this.getLowIndex();
	}

	@Override
	public int hashCode() {
		return mElement.hashCode();
	}
}