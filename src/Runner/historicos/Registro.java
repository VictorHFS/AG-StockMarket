package Runner.historicos;

import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import Runner.cromossomo.Cromossomo;
import Runner.empresa.Empresa;

@Entity
public class Registro {
	@Id
	private String id;
		
	@Embedded
	private Cromossomo cromossomo;
	
	@ManyToOne
	private Empresa empresa;

	private int ano;
	
	@SuppressWarnings("unused")
	private Registro() {
	}
	public Registro(String id, Cromossomo c) {
		this.id = id;
		this.empresa = new Empresa(c.getNomeDaEmpresa());
		Calendar ano = Calendar.getInstance();
		ano.setTime(c.getDataCotacao());
		this.ano = ano.get(Calendar.YEAR);
		cromossomo = c;
	}

	public Cromossomo getCromossomo() throws Exception {
		return cromossomo;
	}
	
	public String getId() {
		return this.id;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCromossomo(Cromossomo cromossomo) {
		Calendar ano = Calendar.getInstance();
		ano.setTime(cromossomo.getDataCotacao());
		this.ano = ano.get(Calendar.YEAR);
		this.cromossomo = cromossomo;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
