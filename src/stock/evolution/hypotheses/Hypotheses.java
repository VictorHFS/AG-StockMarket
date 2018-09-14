package stock.evolution.hypotheses;

import java.util.List;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import stock.data.enterprise.Empresa;
import stock.evolution.model.chromosome.Cromossomo;

@Entity
public class Hypotheses {
	@Id
	private String id;
	@ElementCollection(fetch = FetchType.EAGER)
	@Embedded
	private List<Cromossomo> cromossomos;
	@ManyToOne
	private Empresa empresa;
	private Double indice,up,down;
	private int geracao;
	private int periodoInicio,periodoFim,periodo;
	private int ano;
	
	private void geradorID() {
		this.id = UUID.randomUUID().toString();
	}
	
	public Hypotheses() {
		geradorID();	
		inicializa();
	}
	private void inicializa() {
		this.indice = 0.0;
		this.up = 0.0;
		this.down = 0.0;
	}
	public Hypotheses(Hypotheses h) {
		inicializa();
		this.id = h.id;
		this.cromossomos = h.cromossomos;
		this.empresa = h.empresa;
		this.indice = h.indice;
		this.up = h.getUp();
		this.down = h.getDown();
		
	}
	
	public Hypotheses(List<Cromossomo> c) {
		inicializa();
		geradorID();
		this.geracao = 1;
		cromossomos = c;
	}

	public List<Cromossomo> getCromossomo() throws Exception {
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

	public void setCromossomos(List<Cromossomo> cromossomos) {
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

	public int getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(int periodoFim) {
		this.periodoFim = periodoFim;
	}

	public int getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(int periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
