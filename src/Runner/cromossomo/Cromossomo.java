package Runner.cromossomo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cromossomo {
	@Column(name="nomeEmpresa")
	private String nomeDaEmpresa;
	private Date dataCotacao;
	private Date dataDeVencimento;	
	private Double fatorDeCotacao, indicadorDeCorrecaoDePreco, numeroDeNegociacoes, 
				precoDaMelhorOfertaDeCompra, precoDaMelhorOfertaDeVenda, precoDeAbertura, 
				precoDeExercicioEmPontos, precoDeExercicios, precoDoUltimoNegocio, 
				precoMaximo, precoMedio, precoMinimo, quantidadeTotalDeTitulosNegociados;
	public Cromossomo() {
		
	}
	public Cromossomo(String nome, 
			Double fatorDeCotacao,Double indicadorDeCorrecaoDePreco,Double numeroDeNegociacoes,
			Double precoDaMelhorOfertaDeCompra,Double precoDaMelhorOfertaDeVenda,Double precoDeAbertura,
			Double precoDeExercicioEmPontos,Double precoDeExercicios,Double precoDoUltimoNegocio,
			Double precoMaximo,Double precoMedio,Double precoMinimo,
			Double quantidadeTotalDeTitulosNegociados, 
			Date dataCotacao, Date dataDeVencimento) {
		this.nomeDaEmpresa = nome;
		this.fatorDeCotacao = fatorDeCotacao;
		this.indicadorDeCorrecaoDePreco = indicadorDeCorrecaoDePreco;
		this.numeroDeNegociacoes = numeroDeNegociacoes;
		this.precoDaMelhorOfertaDeCompra = precoDaMelhorOfertaDeCompra;
		this.precoDaMelhorOfertaDeVenda = precoDaMelhorOfertaDeVenda;
		this.precoDeAbertura = precoDeAbertura;
		this.precoDeExercicioEmPontos = precoDeExercicioEmPontos;
		this.precoDeExercicios = precoDeExercicios;
		this.precoDoUltimoNegocio = precoDoUltimoNegocio;
		this.precoMaximo = precoMaximo;
		this.precoMedio = precoMedio;
		this.precoMinimo = precoMinimo;
		this.quantidadeTotalDeTitulosNegociados = quantidadeTotalDeTitulosNegociados;
		
		
		this.dataCotacao = dataCotacao;
		this.dataDeVencimento = dataDeVencimento;
	}

	public String getNomeDaEmpresa() {
		return nomeDaEmpresa;
	}


	public Date getDataCotacao() {
		return dataCotacao;
	}


	public Date getDataDeVencimento() {
		return dataDeVencimento;
	}


	public Double getFatorDeCotacao() {
		return fatorDeCotacao;
	}


	public Double getIndicadorDeCorrecaoDePreco() {
		return indicadorDeCorrecaoDePreco;
	}


	public Double getNumeroDeNegociacoes() {
		return numeroDeNegociacoes;
	}


	public Double getPrecoDaMelhorOfertaDeCompra() {
		return precoDaMelhorOfertaDeCompra;
	}


	public Double getPrecoDaMelhorOfertaDeVenda() {
		return precoDaMelhorOfertaDeVenda;
	}


	public Double getPrecoDeAbertura() {
		return precoDeAbertura;
	}


	public Double getPrecoDeExercicioEmPontos() {
		return precoDeExercicioEmPontos;
	}


	public Double getPrecoDeExercicios() {
		return precoDeExercicios;
	}


	public Double getPrecoDoUltimoNegocio() {
		return precoDoUltimoNegocio;
	}


	public Double getPrecoMaximo() {
		return precoMaximo;
	}


	public Double getPrecoMedio() {
		return precoMedio;
	}


	public Double getPrecoMinimo() {
		return precoMinimo;
	}


	public Double getQuantidadeTotalDeTitulosNegociados() {
		return quantidadeTotalDeTitulosNegociados;
	}


	public void setNomeDaEmpresa(String nomeDaEmpresa) {
		this.nomeDaEmpresa = nomeDaEmpresa;
	}


	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}


	public void setDataDeVencimento(Date dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}


	public void setFatorDeCotacao(Double fatorDeCotacao) {
		this.fatorDeCotacao = fatorDeCotacao;
	}


	public void setIndicadorDeCorrecaoDePreco(Double indicadorDeCorrecaoDePreco) {
		this.indicadorDeCorrecaoDePreco = indicadorDeCorrecaoDePreco;
	}


	public void setNumeroDeNegociacoes(Double numeroDeNegociacoes) {
		this.numeroDeNegociacoes = numeroDeNegociacoes;
	}


	public void setPrecoDaMelhorOfertaDeCompra(Double precoDaMelhorOfertaDeCompra) {
		this.precoDaMelhorOfertaDeCompra = precoDaMelhorOfertaDeCompra;
	}


	public void setPrecoDaMelhorOfertaDeVenda(Double precoDaMelhorOfertaDeVenda) {
		this.precoDaMelhorOfertaDeVenda = precoDaMelhorOfertaDeVenda;
	}


	public void setPrecoDeAbertura(Double precoDeAbertura) {
		this.precoDeAbertura = precoDeAbertura;
	}


	public void setPrecoDeExercicioEmPontos(Double precoDeExercicioEmPontos) {
		this.precoDeExercicioEmPontos = precoDeExercicioEmPontos;
	}


	public void setPrecoDeExercicios(Double precoDeExercicios) {
		this.precoDeExercicios = precoDeExercicios;
	}


	public void setPrecoDoUltimoNegocio(Double precoDoUltimoNegocio) {
		this.precoDoUltimoNegocio = precoDoUltimoNegocio;
	}


	public void setPrecoMaximo(Double precoMaximo) {
		this.precoMaximo = precoMaximo;
	}


	public void setPrecoMedio(Double precoMedio) {
		this.precoMedio = precoMedio;
	}


	public void setPrecoMinimo(Double precoMinimo) {
		this.precoMinimo = precoMinimo;
	}


	public void setQuantidadeTotalDeTitulosNegociados(Double quantidadeTotalDeTitulosNegociados) {
		this.quantidadeTotalDeTitulosNegociados = quantidadeTotalDeTitulosNegociados;
	}




	
}
