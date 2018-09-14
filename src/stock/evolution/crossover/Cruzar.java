package stock.evolution.crossover;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hypotheses.HipoteseRepository;
import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.model.chromosome.Cromossomo;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.mutacao.mutacaoService;
import stock.evolution.selecao.SelecaoService;

public class Cruzar extends Thread{
	private Hypotheses hipotese,auxiliar;
	private HipoteseRepository repo;
	private GeradorRandomico random;
	private SelecaoService selecao;
	private List<Record> registros;
	private mutacaoService mutacao;
	public Cruzar(Hypotheses hipotese, Hypotheses auxiliar) {
		this.random = new GeradorRandomico();
		this.hipotese = hipotese;
		this.auxiliar = auxiliar;
	}
	public Cruzar comReposotorio(HipoteseRepository repo) {
		this.repo = repo;
		return this;
	}
	private synchronized void salvar(Hypotheses novo) {
		repo.save(novo);
	}
	@Override
	public void run() {
		try {
			System.out.println("hipotese: "+hipotese+" + auxiliar: "+auxiliar+" - iniciado.");
			List<Cromossomo> resultado = new ArrayList<Cromossomo>();
			resultado.addAll(cortar(hipotese.getCromossomo(), true));
			resultado.addAll(cortar(auxiliar.getCromossomo(), false));
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
	public List<Cromossomo> cortar(List<Cromossomo> cromossomos, boolean inicio){
		Hibernate.initialize(cromossomos);
		int pontoDeCorte = random.nextInt(1, cromossomos.size()-1);
		List<Cromossomo> resultado;
		if(inicio) {
			resultado = cromossomos.subList(0, pontoDeCorte);
		}else {
			resultado = cromossomos.subList(pontoDeCorte, cromossomos.size());
		}			
		return resultado;
	}
	public Cruzar comService(SelecaoService selecao) {
		this.selecao = selecao;
		return this;
	}
	public Cruzar comRegistros(List<Record> registros) {
		this.registros = registros;
		return this;
	}
	public Cruzar buscarRegistros(RecordRepository repo) {
		this.registros = repo.getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(hipotese.getEmpresa(), hipotese.getAno());
		return this;
	}
	public Cruzar comMutacao(mutacaoService mutacao) {
		this.mutacao = mutacao;
		return this;
	}
}
