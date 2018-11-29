package stock.evolution.mutation;

@SuppressWarnings("serial")
public class MutationException extends RuntimeException {

	private MutationException() {}
	
	private MutationException(Exception e) {
		super(e);
	}

	public static MutationException createException(Exception e) {
		return new MutationException(e);
	}

}
