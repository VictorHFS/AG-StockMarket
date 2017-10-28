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
	public SelecaoService() {
	} 
	public  void classificarHipotese(Hipotese hipotese,List<Registro> registros) {		
			try {
				GeradorRandomico random = new GeradorRandomico();
				ExecutorService classificarExecutor = Executors.newFixedThreadPool(10);										
				int periodo = hipotese.getPeriodo();	
				if(periodo > 500) {
					System.out.println("debug");
				}
				int inicio,fim;
				for (int i = 0; i < registros.size(); i++) {
					inicio = i;
					fim = inicio + periodo;					
					if(fim+1 < registros.size()){
						classificarExecutor.execute(
							new ClassificarHipotese()
							.comDependencias(registros)
							//.comHipotese(hipotese, random.nextInt(0, registros.size()))							
							.comHipotese(hipotese, i)
							);
					}else{
						break;
					}
				}				
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
			}catch(Exception e) {
				e.printStackTrace();
			}
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
