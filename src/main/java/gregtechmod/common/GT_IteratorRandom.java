package gregtechmod.common;

import java.util.Random;

public class GT_IteratorRandom extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2001727503848927794L;
	public int mIterationStep = Integer.MAX_VALUE;
	
	@Override public int nextInt(int aParameter) {
		if (mIterationStep == 0 || mIterationStep > aParameter) {
			mIterationStep = aParameter;
		}
		return --mIterationStep;
	}
}
