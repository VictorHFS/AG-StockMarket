package Runner.testeDeDesempenho;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelatorioDeDesempenho {
	@Id
	private UUID id;
	private int acertos,erros,naoIdentificados;
	private Double percentualDeLucro;
	public void acertou() {
		this.acertos ++;
	}
	public void errou() {
		this.erros ++;
	}
	public void naoIdentificou() {
		this.naoIdentificados ++;
	}
	public void adicionarLucro(Double lucro) {
		this.percentualDeLucro = this.percentualDeLucro* lucro;
	}
	public int getAcertos() {
		return acertos;
	}
	public int getErros() {
		return erros;
	}
	public int getNaoIdentificados() {
		return naoIdentificados;
	}
	public Double getPercentualDeLucro() {
		return percentualDeLucro;
	}
}
