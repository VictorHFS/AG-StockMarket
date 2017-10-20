package Runner.historicos.service;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import Runner.Layout.LayoutBovespa;
import Runner.cromossomo.Cromossomo;
import Runner.historicos.Registro;

@Component
@Scope("prototype")
public class EstruturarRegistro extends Thread{
	private Registro registro;
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
		if(layout.tipoDeRegistro(registro) == Runner.Layout.Registro.Contacao) {
			Date dataCotacao = layout.dataCotacao(registro);
			Date dataDeVencimento = layout.dataDeVencimento(registro);				
			crom = new Cromossomo(layout.nomeResCotacao(registro), (double) layout.fatorDeCotacao(registro),(double) layout.indicadorDeCorrecaoDePreco(registro),
								(double) layout.numeroDeNegociacoes(registro),layout.precoDaMelhorOfertaDeCompra(registro),layout.precoDaMelhorOfertaDeVenda(registro),
								layout.precoDeAbertura(registro),layout.precoDeExercicioEmPontos(registro),layout.precoDeExercicios(registro),
								layout.precoDoUltimoNegocio(registro),layout.precoMaximo(registro),layout.precoMedio(registro),
								layout.precoMinimo(registro),(double) layout.quantidadeTotalDeTitulosNegociados(registro), 
								dataCotacao, dataDeVencimento);
			this.registro = new Registro(layout.Id(registro),crom);			
		}
	}
	public Registro getRegistro() {
		return registro;
	}
}
