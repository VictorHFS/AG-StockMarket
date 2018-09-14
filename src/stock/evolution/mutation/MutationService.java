package stock.evolution.mutation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.crossover.ChromosomeSplitter;
import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.model.generator.GeradorRandomico;

@Service
public class MutationService {
	GeradorRandomico random;
	@Autowired
	RecordRepository repo;
	@Autowired
	ChromosomeSplitter splitter;
	public MutationService() {
		this.random = new GeradorRandomico();
	}
	public Hypotheses mutar(Hypotheses hipotese){
		try {
			if(hipotese.getPeriodo()>250) {
				splitter.split(hipotese);
			}
			if(random.nextInt(0, 100) <= 15) {
				int metodo = random.nextInt(0, 1);
				switch(metodo) {
				case 0: splitter.split(hipotese);
				break;
				case 1: substitution(hipotese);
				break;
				}
			}			
			hipotese.setPeriodo(hipotese.getCromossomo().size());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hipotese;
	}
	public Hypotheses substitution(Hypotheses hipotese) {
		try {
			List<Record> registros = repo.getRegistroByEmpresa(hipotese.getEmpresa());				
			hipotese.getCromossomo()
					.set(random.nextInt(0, hipotese.getCromossomo().size()),
						registros.get(random.nextInt(0, registros.size()))
						.getCromossomo()
						);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hipotese;
	}
}
