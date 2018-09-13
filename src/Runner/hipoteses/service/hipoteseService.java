package Runner.hipoteses.service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.empresa.Empresa;
import Runner.empresa.EmpresaService;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseFactory;
import Runner.hipoteses.HipoteseRepository;
import Runner.historicos.RegistroRepository;
import Runner.random.GeradorRandomico;
import Runner.selecao.SelecaoService;

@Service
@Transactional
public class hipoteseService {
	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	SelecaoService selecaoService;
	@Autowired
	HipoteseFactory factory;
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
	
	public void salvarHipotese(Hipotese h) {
		hipoteseRepo.save(h);
	}
	public List<Hipotese> buscarHipotesesByEmpresa(String nomeEmpresa) {
		return hipoteseRepo.getHipoteseByEmpresa(empresaService.get(nomeEmpresa));		
	}
	public void deleteAll() {
		hipoteseRepo.deleteAll();		
	}

	public List<Hipotese> buscarHipotesesMaisAptasByEmpresa(Empresa empresa) {	
		try {
			List<Hipotese> hipoteses = hipoteseRepo.findTop200ByEmpresaOrderByIndiceDesc(empresa);			
			return  hipoteses;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
