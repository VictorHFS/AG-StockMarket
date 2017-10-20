package Runner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Runner.empresa.EmpresaController;
import Runner.hipoteses.PopulacaoController;
import Runner.historicos.HistoricoController;
import Runner.historicos.RegistroController;

import org.springframework.boot.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@SpringBootApplication
public class app implements ApplicationRunner {
	@Autowired
	HistoricoController historicoController;
	@Autowired
	PopulacaoController populacaoController;
	@Autowired
	EmpresaController empresaController;
	@Autowired
	RegistroController registroController;
	
	public static void main(String[] args) {
		SpringApplication.run(app.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		//populacaoController.testDesempenho("PETRE", "01/01/2017");
		//System.out.println(populacaoController.deleteAll());
		//System.out.println(registroController.deleteAll());
		historicoController.gerarHistorico("C:/Users/Usuario/Desktop/2015.txt");
		//populacaoController.createPopulacao("SABESP");
		//populacaoController.deleteAll();
		//populacaoController.createPopulacao("PETRE",2015);
		//populacaoController.createPopulacao("POSITIVO IN",2015);
		//populacaoController.nextGen("POSITIVO IN");
		//System.out.println(populacaoController.buscarPopulacao("PETRE"));
		//*/
	}
}
