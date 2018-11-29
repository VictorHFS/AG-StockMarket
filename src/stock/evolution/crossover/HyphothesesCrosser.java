package stock.evolution.crossover;

import stock.evolution.hypotheses.Hypoteses;
import stock.evolution.hypotheses.HypothesesRepository;
import stock.evolution.selecao.SelecaoService;

public class HyphothesesCrosser extends Thread{
	
	private Hypoteses hipotese;
	
	private HypothesesRepository repo;
	
	private SelecaoService selecao;
	
	public HyphothesesCrosser(SelecaoService selecao, Hypoteses hipotese) {
		this.hipotese = hipotese;
		this.selecao = selecao;
	}
	
	private synchronized void salvar(Hypoteses novo) {
		repo.save(novo);
	}
	
	@Override
	public void run() {
		try {
			Hypoteses novo = hipotese.split();			
			salvar(novo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
