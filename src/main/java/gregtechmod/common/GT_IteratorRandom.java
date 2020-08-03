package gregtechmod.common;

import java.util.Random;

public class GT_IteratorRandom extends Random {
	public int mIterationStep = Integer.MAX_VALUE;
	
	@Override public int nextInt(int aParameter) {
		if (mIterationStep == 0 || mIterationStep > aParameter) {
			mIterationStep = aParameter;
		}
		return --mIterationStep;
	}
}
