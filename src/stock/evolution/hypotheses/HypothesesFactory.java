package stock.evolution.hypotheses;

import static stock.evolution.hypotheses.HypothesesException.createException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import stock.data.enterprise.Empresa;
import stock.data.enterprise.EmpresaService;
import stock.data.record.Historic;
import stock.data.record.RecordRepository;
import stock.evolution.model.chromosome.Chromossome;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.selecao.SelecaoService;

public class HypothesesFactory {

	@Autowired
	RecordRepository registroRepo;

	@Autowired
	HypothesesRepository hipoteseRepo;

	@Autowired
	EmpresaService empresaService;

	@Autowired
	SelecaoService selecaoService;

	@Autowired
	GeradorRandomico gerador;
	
	Historic historic = Historic.getInstance();

	public List<Hypoteses> createAListOfHypotheses(String nomeEmpresa, int ano, int quantidade) {
		Empresa empresa = empresaService.get(nomeEmpresa);
		int iterations = 0;
		ArrayList<Hypoteses> hypotesesList = new ArrayList<>();
		while(iterations < quantidade) {
			Hypoteses hypoteses = createAnHypoteses(empresa, ano);
			hypotesesList.add(hypoteses);
		}
		return hypotesesList;
	}

	private Hypoteses createAnHypoteses(Empresa empresa, int ano) {
		try {
			int quantidadeTransacoes = gerador.nextInt(1, 150);
			int indiceInicialTransacao = gerador.nextInt(0, quantidadeTransacoes);
			List<Chromossome> resultado = new ArrayList<>();
			for (int i = indiceInicialTransacao; i < quantidadeTransacoes; i++) {
				resultado.add(historic.getCromossomoAt(1));
			}
			return new Hypoteses(empresa, resultado);
		} catch (Exception e) {
			throw createException(e);
		}
	}
}
