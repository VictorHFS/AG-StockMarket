package Runner.testeDeDesempenho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Runner.analise.AnaliseService;
import Runner.empresa.EmpresaRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;

@Service
@Transactional
public class TesteDeDesempenho {
	@Autowired
	private RelatórioDeDesempenhoRepository repo;
	@Autowired
	AnaliseService analise;
	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	private RelatorioDeDesempenho relatorio;	
	public TesteDeDesempenho() {
		//as hipoteses devem estar ordenadas por periodo
		this.relatorio = new RelatorioDeDesempenho();
	}
	public RelatorioDeDesempenho testarPopulacao(int ano, String nomeEmpresa) {
		List<Registro> registros = registroRepo.getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(
				empresaRepo.findOne(nomeEmpresa), ano);
		for(int i = 0;i < registros.size();i++) {
			if(analise.analisarAcoes(
					registros.subList(registros.size()-1, registros.size())
					, nomeEmpresa)) {
				relatorio.acertou();
			}else {
				relatorio.errou();
			}			
		}
		return relatorio;
	}
	
	
}
