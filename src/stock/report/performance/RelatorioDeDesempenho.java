package stock.report.performance;

public class RelatorioDeDesempenho {
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
