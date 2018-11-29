package stock.evolution.mutation;

import static stock.evolution.mutation.MutationException.createException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.Hypoteses;
import stock.evolution.model.chromosome.Chromossome;
import stock.evolution.model.generator.GeradorRandomico;

@Service
public class MutationService {
	private static final int SEPARATE = 1;
	private static final int SPLIT = 0;
	GeradorRandomico random;
	@Autowired
	RecordRepository repo;
	public MutationService() {
		this.random = new GeradorRandomico();
	}
	public Hypoteses mutar(Hypoteses hipotese) {
		try {
			if(hipotese.getNumeroTransacoes()>250) {
				hipotese.split();
			}
			if(random.nextInt(0, 100) <= 15) {
				int metodo = random.nextInt(0, 1);
				switch(metodo) {
				case SPLIT: hipotese.split();
				break;
				case SEPARATE: substitution(hipotese);
				break;
				}
			}
			
		}catch(Exception e) {
			throw createException(e);
		}
		return hipotese;
	}
	
	public Hypoteses substitution(Hypoteses hipotese) {
		try {
			List<Record> registros = repo.getRegistroByEmpresa(hipotese.getEmpresa());
			Stream<Chromossome> cromossomes = registros.stream().map(Record::getCromossomo);
			int size = new Long(cromossomes.count()).intValue();
			int randomInt = random.nextInt(0, size);
			Chromossome randomCromo = registros.get(randomInt).getCromossomo();
			hipotese.setGeneAt(randomInt, randomCromo);
			return hipotese;
		}catch(Exception e) {
			throw createException(e);
		}
	}
}
