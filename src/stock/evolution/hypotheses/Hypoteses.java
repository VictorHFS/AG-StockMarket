package stock.evolution.hypotheses;

import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Hibernate;

import stock.data.enterprise.Empresa;
import stock.evolution.model.chromosome.Chromossome;
import stock.evolution.model.generator.GeradorRandomico;

@Entity
public class Hypoteses {
	@Id
	private String id;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Embedded
	private List<Chromossome> cromossomos;
	
	@ManyToOne
	private Empresa empresa;
	
	private Double indice,up,down;
	
	private int geracao;
	
	private int numeroTransacoes;

	
	private void geradorID() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Hypoteses(Empresa empresa, List<Chromossome> cromossomos) {
		geradorID();	
		inicializa();
		setNumeroTransacoes(cromossomos.size());
	}
	private void inicializa() {
		this.indice = 0.0;
		this.up = 0.0;
		this.down = 0.0;
	}
	public Hypoteses(Hypoteses h) {
		inicializa();
		this.id = h.id;
		this.cromossomos = h.cromossomos;
		this.empresa = h.empresa;
		this.indice = h.indice;
		this.up = h.getUp();
		this.down = h.getDown();
		
	}
	
	public Hypoteses(List<Chromossome> c) {
		inicializa();
		geradorID();
		this.geracao = 1;
		cromossomos = c;
	}

	public List<Chromossome> getCromossomos() {
		return cromossomos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public String getId() {
		return id;
	}
	
	public Double getIndice() {
		return indice;
	}	
	
	public void setId(String id) {
		this.id = id;
	}

	public void setCromossomos(List<Chromossome> cromossomos) {
		this.cromossomos = cromossomos;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public void setIndice(Double indice) {
		this.indice = indice;
	}

	public Double getUp() {
		return up;
	}

	public void setUp(Double up) {
		this.up = up;
	}

	public Double getDown() {
		return down;
	}

	public void setDown(Double down) {
		this.down = down;
	}

	public int getGeracao() {
		return geracao;
	}

	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}

	public int getNumeroTransacoes() {
		return numeroTransacoes;
	}

	public void setNumeroTransacoes(int numeroTransacoes) {
		this.numeroTransacoes = numeroTransacoes;
	}

	public Hypoteses split() {
		int switcher = new GeradorRandomico().nextInt(1, 2);
		List<Chromossome> beginning = this.splitBeginning(cromossomos);
		List<Chromossome> ending = this.splitEnding(cromossomos);
		Hypoteses first = new Hypoteses(empresa, beginning);
		Hypoteses second = new Hypoteses(empresa, ending);
		Hypoteses[] hypotheses = new Hypoteses[] {first, second};
		return hypotheses[switcher];
	}
	
	private List<Chromossome> splitBeginning(List<Chromossome> cromossomos){
		GeradorRandomico random = new GeradorRandomico();
		Hibernate.initialize(cromossomos);
		int pontoDeCorte = random.nextInt(1, cromossomos.size()-1);
		List<Chromossome> resultado;
		resultado = cromossomos.subList(0, pontoDeCorte);
		return resultado;
	}
	
	private List<Chromossome> splitEnding(List<Chromossome> cromossomos){
		GeradorRandomico random = new GeradorRandomico();
		Hibernate.initialize(cromossomos);
		int pontoDeCorte = random.nextInt(1, cromossomos.size()-1);
		List<Chromossome> resultado;
		resultado = cromossomos.subList(pontoDeCorte, cromossomos.size());
		return resultado;
	}

	public Hypoteses clone() {
		return new Hypoteses(empresa, cromossomos);
	}

	public void setGeneAt(int index, Chromossome value) {
		this.cromossomos.set(index, value);
	}
}
