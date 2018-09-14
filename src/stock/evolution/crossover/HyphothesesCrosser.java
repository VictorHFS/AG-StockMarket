package stock.evolution.crossover;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HypothesesRepository;
import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.model.chromosome.Cromossomo;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.mutation.MutationService;
import stock.evolution.selecao.SelecaoService;

public class HyphothesesCrosser extends Thread{
	private Hypotheses hipotese,auxiliar;
	private HypothesesRepository repo;
	private SelecaoService selecao;
	private List<Record> registros;
	private ChromosomeSplitter splitter;
	public HyphothesesCrosser(Hypotheses hipotese, Hypotheses auxiliar) {
		this.hipotese = hipotese;
		this.auxiliar = auxiliar;
	}
	private synchronized void salvar(Hypotheses novo) {
		repo.save(novo);
	}
	@Override
	public void run() {
		try {
			System.out.println("hipotese: "+hipotese+" + auxiliar: "+auxiliar+" - iniciado.");
			List<Cromossomo> resultado = new ArrayList<Cromossomo>();
			resultado.addAll(splitter.split(hipotese.getCromossomo(), true));
			resultado.addAll(splitter.split(auxiliar.getCromossomo(), false));
			Hypotheses novo = new Hypotheses(resultado);
			System.out.println("hipotese: "+hipotese+" + auxiliar: "+auxiliar+" - finalizado.");
			System.out.println(novo+" criado.");
			novo.setAno(hipotese.getAno());
			novo.setEmpresa(hipotese.getEmpresa());
			novo.setGeracao(hipotese.getGeracao()+1);
			novo.setPeriodo(resultado.size());			
			selecao.classificarHipotese(novo, registros);
			System.out.println(novo+" classificado.");
			int count = 0;
			if(novo.getIndice() == null) {
				System.out.println("indice nulo!");
			}
			salvar(novo);
			System.out.println(novo+" salvo.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
