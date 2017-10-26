package Runner.mutacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.hipoteses.Hipotese;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import Runner.random.GeradorRandomico;

@Service
public class mutacaoService {
	GeradorRandomico random;
	@Autowired
	RegistroRepository repo;
	public mutacaoService() {
		this.random = new GeradorRandomico();
	}
	public Hipotese mutar(Hipotese hipotese){
		try {
			if(hipotese.getPeriodo()>250) {
				corte(hipotese);
			}
			if(random.nextInt(0, 100) <= 15) {
				int metodo = random.nextInt(0, 1);
				switch(metodo) {
				case 0: corte(hipotese);
				break;
				case 1: substituicao(hipotese);
				break;
				}
			}			
			hipotese.setPeriodo(hipotese.getCromossomo().size());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return hipotese;
	}
	public Hipotese corte(Hipotese hipotese) {
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
	public Hipotese substituicao(Hipotese hipotese) {
		try {
			List<Registro> registros = repo.getRegistroByEmpresa(hipotese.getEmpresa());				
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
