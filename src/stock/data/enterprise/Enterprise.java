package stock.data.enterprise;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import stock.data.record.Record;

@Entity
public class Enterprise {
	@Id
	private String nome;
	@OneToMany(mappedBy="empresa")
	private List<Record> registro;
	
	
	public Enterprise() {
	}
	public Enterprise(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public List<Record> getRegistro() {
		return registro;
	}
	public void setRegistro(List<Record> registro) {
		this.registro = registro;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
