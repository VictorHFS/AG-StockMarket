package stock.data.record;

import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import stock.data.enterprise.Enterprise;
import stock.evolution.model.chromosome.Chromossome;

@Entity
public class Record {
	@Id
	private String id;
		
	@Embedded
	private Chromossome cromossomo;
	
	@ManyToOne
	private Enterprise empresa;

	private int ano;
	
	@SuppressWarnings("unused")
	private Record() {
	}
	public Record(String id, Chromossome c) {
		this.id = id;
		this.empresa = new Enterprise(c.getNomeDaEmpresa());
		Calendar ano = Calendar.getInstance();
		ano.setTime(c.getDataCotacao());
		this.ano = ano.get(Calendar.YEAR);
		cromossomo = c;
	}

	public Chromossome getCromossomo() {
		return cromossomo;
	}
	
	public String getId() {
		return this.id;
	}
	public Enterprise getEmpresa() {
		return empresa;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCromossomo(Chromossome cromossomo) {
		Calendar ano = Calendar.getInstance();
		ano.setTime(cromossomo.getDataCotacao());
		this.ano = ano.get(Calendar.YEAR);
		this.cromossomo = cromossomo;
	}
	public void setEmpresa(Enterprise empresa) {
		this.empresa = empresa;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
