package Runner.selecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import Runner.cromossomo.Cromossomo;
import Runner.hipoteses.Hipotese;
import Runner.historicos.Registro;
import Runner.random.GeradorRandomico;

@Service
public class SelecaoService {

	GeradorRandomico random = new GeradorRandomico();
	ExecutorService executor = Executors.newFixedThreadPool(50);	
	
	public void classificarHipotese(Hipotese hipotese, List<Registro> registros) {
		try {
			int execucoes = 0;
			int periodo = hipotese.getPeriodo();
			int inicio, fim;
			for (int i = 0; i < registros.size(); i++) {
				inicio = i;
				fim = inicio + periodo;
				if (fim + 1 < registros.size()) {
					execucoes++;
					executor.execute(new ClassificarHipotese().comDependencias(registros).comHipotese(hipotese, i));
				} else {
					break;
				}
			}
			hipotese = calcularAptidao(hipotese, execucoes);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao classificar hipotese\n"+e.getMessage());
		}
	}

	private Hipotese calcularAptidao(Hipotese hipotese, int execucoes) throws InterruptedException {
		try {
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.MINUTES);
			if (execucoes == 0) {
				execucoes = 1;
			}
			if (hipotese.getDown() == 0 || hipotese.getUp() == 0) {
				hipotese.setDown(0.0);
				hipotese.setUp(0.0);
				hipotese.setIndice(0.0);
			} else {
				hipotese.setUp(hipotese.getUp() / execucoes);
				hipotese.setDown(hipotese.getDown() / execucoes);
				hipotese.setIndice(hipotese.getUp() - hipotese.getDown());
			}

		} catch (InterruptedException e) {
			throw new InterruptedException("Erro ao calcular aptidão\n"+ e.getMessage());
		}
		return hipotese;
	}
	
}
