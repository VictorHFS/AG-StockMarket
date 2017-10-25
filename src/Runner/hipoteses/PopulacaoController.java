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
	@PostMapping("/post/{nomeEmpresa}/{ano}/{tamanho}")
	public ResponseEntity<String> createPopulacao(@PathVariable String nomeEmpresa, @PathVariable int ano,@PathVariable int tamanho) {		
			try {
				populacaoService.gerarPopulacaoThread(tamanho, nomeEmpresa, ano);
			}catch(Exception e) {
				return ResponseEntity.badRequest().body("Não foi possivel gerar a população");
			}
			return ResponseEntity.ok().build();
	}
	@GetMapping("/get/{nomeEmpresa}")
	public Populacao getPopulacao(@PathVariable String string) {
		return populacaoService.buscarPopulacao(string);
	}
	@DeleteMapping
	public ResponseEntity<String> deleteAll() {
		try {
			if(populacaoService.quantidadeDeHipoteses() > 0) {
				populacaoService.deleteAll();				
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possível excluir o resgistro");
		}
		return ResponseEntity.ok().build();
	}
	@PostMapping("/test/{nomeEmpresa}/{data}")
	public RelatorioDeDesempenho testDesempenho(@PathVariable String nomeEmpresa,@PathVariable String data) {
		return desempenhoService.testarPopulacao(new Date(data), nomeEmpresa);
	}
	
	@PostMapping("/test/{nomeEmpresa}/{ano}")
	public ResponseEntity<String> nextGen(@PathVariable String nomeEmpresa,@PathVariable int ano) {
		try {
			crossOver.proximaGeracao(nomeEmpresa, ano);			
		}catch(Exception e) {
			return ResponseEntity.badRequest().body("não foi possivel gerar a proxima geração");
		}
		return ResponseEntity.ok().build();
	}
	
}
