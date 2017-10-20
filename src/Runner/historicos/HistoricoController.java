package Runner.historicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Runner.historicos.service.HistoricoService;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {	
	@Autowired
	HistoricoService gerador;
	@PostMapping("/{caminhoDoArquivo}")
	public void gerarHistorico(@PathVariable String caminho){		
		Thread gerarHistorico = new Thread() {
			@Override
			public void run() {
				try {
					gerador.gerarHistoricoThread(caminho);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}						
			}
		};
		gerarHistorico.start();
	}
}
