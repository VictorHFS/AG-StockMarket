package Runner.hipoteses;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.gerenciadorDeThreads.custom.FilaDeThreads;
import Runner.hipoteses.service.hipoteseService;

@Service
@Transactional
public class PopulacaoService {
	@Autowired
	hipoteseService hipoteseService;
	@Autowired
	FilaDeThreads filaDeThreads;
	@Autowired
	HipoteseRepository repository;
	ExecutorService executor;

	public PopulacaoService() {
		this.executor = Executors.newFixedThreadPool(6);
	}

	public long quantidadeDeHipoteses() {
		return repository.count();
	}

	public void gerarPopulacaoThread(int tamanho, String nomeEmpresa, int ano) {
		try {
			for (int i = 0; i < tamanho; i++) {
				hipoteseService.gerarHipotese(nomeEmpresa, ano);
			}
		} catch (Exception e) {
			String message = "Erro ao gerar Hipotese\n";
			JOptionPane.showMessageDialog(null, message+e.getMessage());
		}
	}

	public void salvarPopulacao(Populacao novo) {
		for (Hipotese h : novo.getAllMembers()) {
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
