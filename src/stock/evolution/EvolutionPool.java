package stock.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import stock.evolution.classification.ClassificationService;
import stock.evolution.hyphoteses.ClassifiedHypoteses;
import stock.evolution.hypotheses.Hypoteses;

public class EvolutionPool {

	private static final int filterNumber = 1000;
	private final List<Hypoteses> classifiedHypotheses;
	private static final EvolutionPool EVOLUTION_POOL = new EvolutionPool();
	
	@Autowired
	private ClassificationService classificatioService;
	
	public static EvolutionPool getInstance() {
		return EVOLUTION_POOL;
	}
	
	private EvolutionPool() {
		this.classifiedHypotheses = new ArrayList<>();
	}
	
	public List<Hypoteses> getMostAptHypoteses() {
		return classifiedHypotheses.stream().sorted((one, other) -> {
			double value = one.getIndice()-other.getIndice();
			return new Double(value)
					.intValue();
		}).collect(Collectors.toList())
		.subList(0, filterNumber);
	}

	public ClassifiedHypoteses classify(Hypoteses hypotheses) {
		ClassifiedHypoteses classified =
				classificatioService.classify(hypotheses);
		return classified;
	}
}
