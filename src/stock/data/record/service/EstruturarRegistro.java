package stock.data.record.service;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import stock.data.extractor.layout.LayoutBovespa;
import stock.data.record.Record;
import stock.evolution.model.chromosome.Cromossomo;

@Component
@Scope("prototype")
public class EstruturarRegistro extends Thread{
	private Record registro;
	private String registroString;
	public EstruturarRegistro(String registroString) {
		this.setName("EstruturarRegistro");
		this.registroString = registroString;
	}
	@Override
	public void run() {
		try {			
			//estruturarRegistro(registroString);				
			notify();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	private void estruturarRegistro(String registro) throws Exception {		
		LayoutBovespa layout = new LayoutBovespa();
		Cromossomo crom;
		if(layout.tipoDeRegistro(registro) == stock.data.extractor.layout.LayoutRecord.Contacao) {
			Date dataCotacao = layout.dataCotacao(registro);
			Date dataDeVencimento = layout.dataDeVencimento(registro);				
			crom = new Cromossomo(layout.nomeResCotacao(registro), (double) layout.fatorDeCotacao(registro),(double) layout.indicadorDeCorrecaoDePreco(registro),
								(double) layout.numeroDeNegociacoes(registro),layout.precoDaMelhorOfertaDeCompra(registro),layout.precoDaMelhorOfertaDeVenda(registro),
								layout.precoDeAbertura(registro),layout.precoDeExercicioEmPontos(registro),layout.precoDeExercicios(registro),
								layout.precoDoUltimoNegocio(registro),layout.precoMaximo(registro),layout.precoMedio(registro),
								layout.precoMinimo(registro),(double) layout.quantidadeTotalDeTitulosNegociados(registro), 
								dataCotacao, dataDeVencimento);
			this.registro = new Record(layout.Id(registro),crom);			
		}
	}
	public Record getRegistro() {
		return registro;
	}
}
