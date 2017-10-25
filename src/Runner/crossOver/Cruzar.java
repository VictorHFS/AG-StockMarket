package Runner.crossOver;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import Runner.cromossomo.Cromossomo;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import Runner.random.GeradorRandomico;
import Runner.selecao.SelecaoService;

public class Cruzar extends Thread{
	private Hipotese hipotese,auxiliar;
	private HipoteseRepository repo;
	private GeradorRandomico random;
	private SelecaoService selecao;
	private List<Registro> registros;
	public Cruzar(Hipotese hipotese, Hipotese auxiliar) {
		this.random = new GeradorRandomico();
		this.hipotese = hipotese;
		this.auxiliar = auxiliar;
	}
	public Cruzar comReposotorio(HipoteseRepository repo) {
		this.repo = repo;
		return this;
	}
	private synchronized void salvar(Hipotese novo) {
		repo.save(novo);
	}
	@Override
	public void run() {
		try {
			System.out.println("hipotese: "+hipotese+" + auxiliar: "+auxiliar+" - iniciado.");
			List<Cromossomo> resultado = new ArrayList<Cromossomo>();
			resultado.addAll(cortar(hipotese.getCromossomo(), true));
			resultado.addAll(cortar(auxiliar.getCromossomo(), false));
			Hipotese novo = new Hipotese(resultado);
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
	public Cruzar comRegistros(List<Registro> registros) {
		this.registros = registros;
		return this;
	}
	public Cruzar buscarRegistros(RegistroRepository repo) {
		this.registros = repo.getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(hipotese.getEmpresa(), hipotese.getAno());
		return this;
	}
}
