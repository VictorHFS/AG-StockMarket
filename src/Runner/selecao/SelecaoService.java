package Runner.selecao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import Runner.cromossomo.Cromossomo;
import Runner.hipoteses.Hipotese;
import Runner.historicos.Registro;
import Runner.random.GeradorRandomico;

@Service
public class SelecaoService {
	GeradorRandomico random;
	ExecutorService classificarExecutor;	
	public SelecaoService() {
		classificarExecutor = Executors.newSingleThreadExecutor();
		random = new GeradorRandomico();
	} 
	public void classificar(Hipotese hipotese,List<Registro> registros) {
		classificarHipotese(hipotese,registros);
	}
	private  Hipotese classificarHipotese(Hipotese hipotese,List<Registro> registros) {		
			try {											
				int periodo = hipotese.getPeriodo();
				if(periodo < registros.size()) {
					Executors.newSingleThreadExecutor().execute(
							new ClassificarHipotese()
							.comDependencias(registros.subList(0, periodo+1))					
							.comHipotese(hipotese)
							);
					return classificarHipotese(hipotese, registros.subList(1, registros.size()));
				}else {
					return shutDown(hipotese);
				}																						
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	private Hipotese shutDown(Hipotese hipotese) {
		try {
			classificarExecutor.shutdown();
			classificarExecutor.awaitTermination(30, TimeUnit.MINUTES);
			if(hipotese.getDown() == 0 || hipotese.getUp() == 0) {
				hipotese.setDown(0.0);
				hipotese.setUp(0.0);
				hipotese.setIndice(0.0);
			}else {
				hipotese.setIndice(hipotese.getUp()-hipotese.getDown());
			}					
		} catch (InterruptedException e) {
			e.printStackTrace();					
		}				
		return hipotese;
	}
	private void inicializaCromossomo(Cromossomo c) {
		c.setFatorDeCotacao(0.0);
		c.setIndicadorDeCorrecaoDePreco(0.0);
		c.setNumeroDeNegociacoes(0.0);
		c.setPrecoDaMelhorOfertaDeCompra(0.0);
		c.setPrecoDaMelhorOfertaDeVenda(0.0);
		c.setPrecoDeAbertura(0.0);
		c.setPrecoDeExercicioEmPontos(0.0);
		c.setPrecoDeExercicios(0.0);
		c.setPrecoDoUltimoNegocio(0.0);
		c.setPrecoMaximo(0.0);
		c.setPrecoMedio(0.0);
		c.setPrecoMinimo(0.0);
		c.setQuantidadeTotalDeTitulosNegociados(0.0);
	}
	
}
