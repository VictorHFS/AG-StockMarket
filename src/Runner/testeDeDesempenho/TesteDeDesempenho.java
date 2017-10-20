package Runner.testeDeDesempenho;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.empresa.Empresa;
import Runner.empresa.EmpresaRepository;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;

@Service
public class TesteDeDesempenho {
	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	
	private RelatorioDeDesempenho relatorio;	
	public TesteDeDesempenho() {
		//as hipoteses devem estar ordenadas por periodo
		this.relatorio = new RelatorioDeDesempenho();
	}
	public List<Hipotese> detectarPadrao(List<Hipotese> hipoteses, List<Registro> registros){
		for (int i = 0; i < registros.size(); i++) {
			
		}
		return null;		
	}
	public RelatorioDeDesempenho testarPopulacao(Date data,String nomeEmpresa){
		//as hipoteses devem estar ordenadas por periodo
		Empresa empresa = empresaRepo.getOne(nomeEmpresa);
		hipoteseRepo.getHipoteseByEmpresaOrderByPeriodo(empresa);
		List<Registro> registros = registroRepo.getRegistroByEmpresa(empresa);
		return null;
	}
}
