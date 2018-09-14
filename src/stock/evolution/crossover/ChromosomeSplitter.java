package stock.evolution.crossover;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.model.chromosome.Cromossomo;
import stock.evolution.model.generator.GeradorRandomico;

public class ChromosomeSplitter {
	
	@Autowired
	private GeradorRandomico random;
	
	public List<Cromossomo> split(List<Cromossomo> cromossomos, boolean inicio){
		Hibernate.initialize(cromossomos);
		int pontoDeCorte = random.nextInt(1, cromossomos.size()-1);
		List<Cromossomo> resultado;
		if(inicio) {
			resultado = cromossomos.subList(0, pontoDeCorte);
		}else {
			resultado = cromossomos.subList(pontoDeCorte, cromossomos.size());
		}			
		return resultado;
	}
	

	public Hypotheses split(Hypotheses hipotese) {
		try {
			boolean inicio = random.nextBool();
			if(inicio) {
				hipotese.setCromossomos(hipotese.getCromossomo().subList(0, random.nextInt(0, hipotese.getCromossomo().size())));			
			}else {
				hipotese.setCromossomos(hipotese.getCromossomo().subList(random.nextInt(0, hipotese.getCromossomo().size()-1), hipotese.getCromossomo().size()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return hipotese;
	}
}
