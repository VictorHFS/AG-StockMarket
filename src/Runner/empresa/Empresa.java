package Runner.empresa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Runner.historicos.Registro;

@Entity
public class Empresa {
	@Id
	private String nome;
	@OneToMany(mappedBy="empresa")
	private List<Registro> registro;
	
	
	public Empresa() {
	}
	public Empresa(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public List<Registro> getRegistro() {
		return registro;
	}
	public void setRegistro(List<Registro> registro) {
		this.registro = registro;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
