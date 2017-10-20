package Runner.hipoteses;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.gerenciadorDeThreads.custom.FilaDeThreads;
import Runner.hipoteses.service.hipoteseService;

@Service
@Transactional
public class populacaoService {
	@Autowired
	hipoteseService hipoteseService;
	@Autowired
	FilaDeThreads filaDeThreads;
	ExecutorService executor;
	public populacaoService() {
		this.executor = Executors.newFixedThreadPool(10);
	}
	public void gerarPopulacaoThread(int tamanho,String nomeEmpresa, int ano) {
		for (int i = 0; i < tamanho; i++) {
			executor.execute(new Thread() {
				@Override 
				public void run() {					
					this.setName("Hipotese");
					hipoteseService.gerarHipoteseThread(nomeEmpresa, ano);
				}
			});		
		}	
		try {
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	public void salvarPopulacao(Populacao novo) {
		for(Hipotese h: novo.getAllMembers()) {
			hipoteseService.salvarHipotese(h);
		}
	}
	public Populacao buscarPopulacao(String nomeEmpresa) {
		Populacao resultado = new Populacao();
		resultado.addAll(hipoteseService.buscarHipotesesByEmpresa(nomeEmpresa));
		return resultado;
	}
	public void deleteAll() {
		hipoteseService.deleteAll();
		
	}
}
