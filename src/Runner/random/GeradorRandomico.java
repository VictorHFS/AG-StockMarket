package Runner.random;

import java.util.Random;

public class GeradorRandomico   {

	
	public int nextInt(int minInclusive, int maxExclusive) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return rand.nextInt(maxExclusive) + minInclusive;
	}

	public double nextDouble() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return rand.nextDouble();
	}
	
	public boolean nextBool() {
		Random rand = new Random();
		return rand.nextBoolean();		
	}


}
