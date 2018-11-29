package stock.report.performance;

import java.util.Date;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.enterprise.Enterprise;
import stock.data.enterprise.EnterpriseRepository;
import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HypothesesRepository;
import stock.evolution.hypotheses.Hypoteses;

@Service
public class TesteDeDesempenho {
	@Autowired
	RecordRepository registroRepo;
	@Autowired
	HypothesesRepository hipoteseRepo;
	@Autowired
	EnterpriseRepository empresaRepo;
	
	private RelatorioDeDesempenho relatorio;	
	public TesteDeDesempenho() {
		//as hipoteses devem estar ordenadas por periodo
		this.relatorio = new RelatorioDeDesempenho();
	}
	public List<Hypoteses> detectarPadrao(List<Hypoteses> hipoteses, List<Record> registros){
		for (int i = 0; i < registros.size(); i++) {
			
		}
		return null;		
	}
	public RelatorioDeDesempenho testarPopulacao(Date data,String nomeEmpresa){
		//TODO
		throw new NotYetImplementedException();
	}
}
