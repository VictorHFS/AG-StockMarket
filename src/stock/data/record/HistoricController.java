package stock.data.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stock.data.record.service.HistoricoService;

@RestController
@RequestMapping("/historicos")
public class HistoricController {	
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
