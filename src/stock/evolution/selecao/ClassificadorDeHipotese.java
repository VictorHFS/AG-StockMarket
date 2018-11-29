package stock.evolution.selecao;

import java.util.ArrayList;
import java.util.List;

import stock.data.record.Historic;
import stock.data.record.Record;
import stock.evolution.hypotheses.Hypoteses;
import stock.evolution.model.chromosome.Chromossome;

public class ClassificadorDeHipotese implements Runnable{
	private Hypoteses hipotese;
	private int inicio;
	private final Historic historic = Historic.getInstance();
	
	@Override
	public void run() {
		try {
			List<Chromossome> chromossomes = new ArrayList<Chromossome>();					
			int numeroDeTransacoes = hipotese.getNumeroTransacoes();
			List<Record> records = historic.getAllRecords();
			int fim = inicio + numeroDeTransacoes;
			for(int y = inicio; y < fim; y++) {
				chromossomes.add(records.get(y).getCromossomo());
			}
			Boolean lucro = records.get(fim+1).getCromossomo().getPrecoDoUltimoNegocio() > records.get(fim).getCromossomo().getPrecoDoUltimoNegocio();						
			classificaHipotese(hipotese,chromossomes,lucro);						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void classificaHipotese(Hypoteses hipotese, Boolean lucro) {
		List<Chromossome> chromossomes = historic.getAllCromossomes();
		try {
			Chromossome cromoHip,cromossomo;			
			Double indice;
			if(lucro) {
				indice = hipotese.getUp();
				if(indice == null) {indice = 0.0;}
				for(int i = 0; i< chromossomes.size();i++){
					if(hipotese.getCromossomos().size()< chromossomes.size()) {
						System.out.println("erro");
					}
					cromossomo = chromossomes.get(i);
					cromoHip = hipotese.getCromossomos().get(i);
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
				for(int i = 0; i< chromossomes.size();i++){
					cromossomo = chromossomes.get(i);
					cromoHip = hipotese.getCromossomos().get(i);
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
