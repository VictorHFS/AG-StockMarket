package stock.evolution.model.generator;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class GeradorRandomico   {

	@Autowired
	private Random rand;
	
	public int nextInt(int minInclusive, int maxExclusive) {
		return rand.nextInt(maxExclusive) + minInclusive;
	}

	public double nextDouble() {
		return rand.nextDouble();
	}
	
	public boolean nextBool() {
		return rand.nextBoolean();		
	}


}
