package stock.evolution.hypotheses;

@SuppressWarnings("serial")
public class HypothesesException extends RuntimeException{

	private HypothesesException(Exception e) {
		super(e);
	}

	public static HypothesesException createException(Exception e) {
		return new HypothesesException(e);
	}

}
