package stock.report.performance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.enterprise.Empresa;
import stock.data.enterprise.EmpresaRepository;
import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HypothesesRepository;
import stock.evolution.hypotheses.Hypotheses;

@Service
public class TesteDeDesempenho {
	@Autowired
	RecordRepository registroRepo;
	@Autowired
	HypothesesRepository hipoteseRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	
	private RelatorioDeDesempenho relatorio;	
	public TesteDeDesempenho() {
		//as hipoteses devem estar ordenadas por periodo
		this.relatorio = new RelatorioDeDesempenho();
	}
	public List<Hypotheses> detectarPadrao(List<Hypotheses> hipoteses, List<Record> registros){
		for (int i = 0; i < registros.size(); i++) {
			
		}
		return null;		
	}
	public RelatorioDeDesempenho testarPopulacao(Date data,String nomeEmpresa){
		//as hipoteses devem estar ordenadas por periodo
		Empresa empresa = empresaRepo.getOne(nomeEmpresa);
		hipoteseRepo.getHipoteseByEmpresaOrderByPeriodo(empresa);
		List<Record> registros = registroRepo.getRegistroByEmpresa(empresa);
		return null;
	}
}
