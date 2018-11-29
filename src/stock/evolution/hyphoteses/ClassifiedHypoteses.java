package stock.evolution.hyphoteses;

import stock.evolution.hypotheses.Hypoteses;

public class ClassifiedHypoteses {
	
	private final Double aptitude;
	private final Hypoteses hypoteses;
	
	public ClassifiedHypoteses(Hypoteses hypoteses, Double aptitude) {
		this.hypoteses = hypoteses;
		this.aptitude = aptitude;
	}

}
