package stock.evolution.hyphoteses.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.enterprise.Empresa;
import stock.data.enterprise.EmpresaService;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HypothesesRepository;
import stock.evolution.EvolutionPool;
import stock.evolution.hypotheses.Hypoteses;
import stock.evolution.hypotheses.HypothesesFactory;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.selecao.SelecaoService;

@Service
@Transactional
public class HypothesesService {
	@Autowired
	RecordRepository registroRepo;
	@Autowired
	HypothesesRepository hipoteseRepo;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	SelecaoService selecaoService;
	@Autowired
	HypothesesFactory factory;
	@Autowired
	ExecutorService executor;
	
	EvolutionPool pool = EvolutionPool.getInstance();
	
	GeradorRandomico gerador = new GeradorRandomico();

	public void salvarHipotese(Hypoteses h) {
		hipoteseRepo.save(h);
	}
	public List<Hypoteses> buscarHipotesesByEmpresa(String nomeEmpresa) {
		return hipoteseRepo.getHipoteseByEmpresa(empresaService.get(nomeEmpresa));		
	}
	public void deleteAll() {
		hipoteseRepo.deleteAll();		
	}

	public List<Hypoteses> buscarHipotesesMaisAptasByEmpresa(Empresa empresa) {	
		List<Hypoteses> hipoteses = pool.getMostAptHypoteses();			
		return  hipoteses;
	}
}
