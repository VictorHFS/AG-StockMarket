package Runner.selecao;

import java.util.ArrayList;
import java.util.List;

import Runner.cromossomo.Cromossomo;
import Runner.hipoteses.Hipotese;
import Runner.historicos.Registro;

public class ClassificarHipotese extends Thread{
	Hipotese hipotese;
	int i;
	List<Registro> registros;
	public ClassificarHipotese comHipotese(Hipotese hipotese, int i) {
		this.setName("Classificar");
		this.i = i;
		this.hipotese = hipotese;
		return this;
	}
	public ClassificarHipotese comDependencias(List<Registro> registros) {
		this.registros = registros;
		return this;
	}
	
	@Override
	public void run() {
		try {
			List<Cromossomo> cromossomos = new ArrayList<Cromossomo>();					
			int periodo = hipotese.getPeriodo();					
			int inicio = i;
			int fim = inicio + periodo;
			for(int y = inicio; y < fim; y++) {
				cromossomos.add(registros.get(y).getCromossomo());
			}
			Boolean lucro = registros.get(fim+1).getCromossomo().getPrecoDoUltimoNegocio() > registros.get(fim).getCromossomo().getPrecoDoUltimoNegocio();						
			classificaHipotese(hipotese,cromossomos,lucro);						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void classificaHipotese(Hipotese hipotese, List<Cromossomo> cromossomos,Boolean lucro) {
		try {
			Cromossomo cromoHip,cromossomo;			
			Double indice;
			if(lucro) {
				indice = hipotese.getUp();
				if(indice == null) {indice = 0.0;}
				for(int i = 0; i< cromossomos.size();i++){
					if(hipotese.getCromossomo().size()< cromossomos.size()) {
						System.out.println("erro");
					}
					cromossomo = cromossomos.get(i);
					cromoHip = hipotese.getCromossomo().get(i);
					indice = indice + DiferenciarDoubles(cromoHip.getFatorDeCotacao(), cromossomo.getFatorDeCotacao())
						+ DiferenciarDoubles(cromoHip.getIndicadorDeCorrecaoDePreco(), cromossomo.getIndicadorDeCorrecaoDePreco())
						+ DiferenciarDoubles(cromoHip.getNumeroDeNegociacoes(), cromossomo.getNumeroDeNegociacoes())
						+ DiferenciarDoubles(cromoHip.getPrecoDaMelhorOfertaDeCompra(), cromossomo.getPrecoDaMelhorOfertaDeCompra())
						+ DiferenciarDoubles(cromoHip.getPrecoDaMelhorOfertaDeVenda(), cromossomo.getPrecoDaMelhorOfertaDeVenda())
						+ DiferenciarDoubles(cromoHip.getPrecoDeAbertura(), cromossomo.getPrecoDeAbertura())
						+ DiferenciarDoubles(cromoHip.getPrecoDeExercicioEmPontos(), cromossomo.getPrecoDeExercicioEmPontos())
						+ DiferenciarDoubles(cromoHip.getPrecoDeExercicios(), cromossomo.getPrecoDeExercicios())
						+ DiferenciarDoubles(cromoHip.getPrecoDoUltimoNegocio(), cromossomo.getPrecoDoUltimoNegocio())
						+ DiferenciarDoubles(cromoHip.getPrecoMaximo(), cromossomo.getPrecoMaximo())
						+ DiferenciarDoubles(cromoHip.getPrecoMedio(), cromossomo.getPrecoMedio())
						+ DiferenciarDoubles(cromoHip.getPrecoMinimo(), cromossomo.getPrecoMinimo())
						+ DiferenciarDoubles(cromoHip.getQuantidadeTotalDeTitulosNegociados(), cromossomo.getQuantidadeTotalDeTitulosNegociados());				
				}
				hipotese.setUp(indice/14);
			}else{
				indice = hipotese.getDown();
				if(indice == null) {indice = 0.0;}
				for(int i = 0; i< cromossomos.size();i++){
					cromossomo = cromossomos.get(i);
					cromoHip = hipotese.getCromossomo().get(i);
					indice = indice + DiferenciarDoubles(cromoHip.getFatorDeCotacao(), cromossomo.getFatorDeCotacao())
						+ DiferenciarDoubles(cromoHip.getIndicadorDeCorrecaoDePreco(), cromossomo.getIndicadorDeCorrecaoDePreco())
						+ DiferenciarDoubles(cromoHip.getNumeroDeNegociacoes(), cromossomo.getNumeroDeNegociacoes())
						+ DiferenciarDoubles(cromoHip.getPrecoDaMelhorOfertaDeCompra(), cromossomo.getPrecoDaMelhorOfertaDeCompra())
						+ DiferenciarDoubles(cromoHip.getPrecoDaMelhorOfertaDeVenda(), cromossomo.getPrecoDaMelhorOfertaDeVenda())
						+ DiferenciarDoubles(cromoHip.getPrecoDeAbertura(), cromossomo.getPrecoDeAbertura())
						+ DiferenciarDoubles(cromoHip.getPrecoDeExercicioEmPontos(), cromossomo.getPrecoDeExercicioEmPontos())
						+ DiferenciarDoubles(cromoHip.getPrecoDeExercicios(), cromossomo.getPrecoDeExercicios())
						+ DiferenciarDoubles(cromoHip.getPrecoDoUltimoNegocio(), cromossomo.getPrecoDoUltimoNegocio())
						+ DiferenciarDoubles(cromoHip.getPrecoMaximo(), cromossomo.getPrecoMaximo())
						+ DiferenciarDoubles(cromoHip.getPrecoMedio(), cromossomo.getPrecoMedio())
						+ DiferenciarDoubles(cromoHip.getPrecoMinimo(), cromossomo.getPrecoMinimo())
						+ DiferenciarDoubles(cromoHip.getQuantidadeTotalDeTitulosNegociados(), cromossomo.getQuantidadeTotalDeTitulosNegociados());								
				}
				hipotese.setDown(indice/14);
			}						
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	private Double DiferenciarDoubles(Double d1,Double d2) {
		if(d1 == 0.0){
			d1 = 1.0;
		}
		if(d2 == 0.0) {
			d2 = 1.0;
		}
		if(d1>d2) {
			return d2/d1;
		}else {
			return d1/d2;
		}
	}
}
