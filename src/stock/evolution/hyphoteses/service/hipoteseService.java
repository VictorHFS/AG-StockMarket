package stock.evolution.hyphoteses.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.enterprise.Empresa;
import stock.data.enterprise.EmpresaService;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HipoteseRepository;
import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.hypotheses.HypothesesFactory;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.selecao.SelecaoService;

@Service
@Transactional
public class hipoteseService {
	@Autowired
	RecordRepository registroRepo;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	SelecaoService selecaoService;
	@Autowired
	HypothesesFactory factory;
	@Autowired
	ExecutorService executor;
	
	GeradorRandomico gerador = new GeradorRandomico();

	public void gerarHipotese(String nomeEmpresa, int ano) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				factory.criar(nomeEmpresa, ano);
			}
		});
	}
	
	public void salvarHipotese(Hypotheses h) {
		hipoteseRepo.save(h);
	}
	public List<Hypotheses> buscarHipotesesByEmpresa(String nomeEmpresa) {
		return hipoteseRepo.getHipoteseByEmpresa(empresaService.get(nomeEmpresa));		
	}
	public void deleteAll() {
		hipoteseRepo.deleteAll();		
	}

	public List<Hypotheses> buscarHipotesesMaisAptasByEmpresa(Empresa empresa) {	
		try {
			List<Hypotheses> hipoteses = hipoteseRepo.findTop200ByEmpresaOrderByIndiceDesc(empresa);			
			return  hipoteses;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}