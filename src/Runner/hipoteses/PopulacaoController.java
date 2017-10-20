package Runner.hipoteses;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Runner.crossOver.CrossOverService;
import Runner.testeDeDesempenho.RelatorioDeDesempenho;
import Runner.testeDeDesempenho.TesteDeDesempenho;


@RestController
@RequestMapping("/populacao")
public class PopulacaoController {
	@Autowired
	CrossOverService crossOver;
	@Autowired
	populacaoService populacaoService;
	@Autowired
	TesteDeDesempenho desempenhoService;
	@PostMapping("/post/{nomeEmpresa}/{ano}")
	public void createPopulacao(@PathVariable String nomeEmpresa, @PathVariable int ano) {
		//populacaoService.gerarPopulacao(1000, string);
		Thread gerar= new Thread("gerar") {
			@Override
			public void run() {
				populacaoService.gerarPopulacaoThread(1, nomeEmpresa, ano);
			}
		};
		gerar.start();
	}
	@GetMapping("/get/{nomeEmpresa}")
	public Populacao getPopulacao(@PathVariable String string) {
		return populacaoService.buscarPopulacao(string);
	}
	@DeleteMapping
	public ResponseEntity<String> deleteAll() {
		try {
			populacaoService.deleteAll();
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possível excluir o resgistro");
		}
		return ResponseEntity.ok().build();
	}
	@PostMapping("/test/{nomeEmpresa}/{data}")
	public RelatorioDeDesempenho testDesempenho(@PathVariable String nomeEmpresa,@PathVariable String data) {
		return desempenhoService.testarPopulacao(new Date(data), nomeEmpresa);
	}
	
	@PostMapping("/test/{nomeEmpresa}}")
	public void nextGen(@PathVariable String nomeEmpresa) {
		crossOver.proximaGeracao(nomeEmpresa);
	}
	
}
