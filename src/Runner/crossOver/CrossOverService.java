package Runner.crossOver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.hipoteses.Hipotese;
import Runner.hipoteses.service.hipoteseService;
import Runner.random.GeradorRandomico;

@Service
public class CrossOverService {
	@Autowired
	hipoteseService hipoteseService;
	int tamanho;
	GeradorRandomico random;
	public CrossOverService() {
		this.random = new GeradorRandomico();
	}
	public void proximaGeracao(String nomeEmpresa) {
		List<Hipotese> hipoteses = hipoteseService.buscarHipotesesMaisAptasByEmpresa(nomeEmpresa);
		List<Integer> rank = new ArrayList<Integer>();
		inicializaRank(hipoteses,rank);
		hipoteses = selecionar(hipoteses,rank);
		List<Hipotese> auxiliar = new ArrayList<Hipotese>();
		inicializaListAuxiliar(hipoteses, auxiliar);
		cruzarHipoteses(hipoteses,auxiliar);		
	}
	private void inicializaRank(List<Hipotese> hipoteses, List<Integer> rank) {
		Integer vezes = 0;
		for(int i = 0; i<hipoteses.size();i++){ 
			vezes = rankearDouble(hipoteses.get(i).getIndice());
			for(int y = 0; y<vezes; y++){
				rank.add(i);							
			}
		}		
	}
	private int rankearDouble(Double ind){
		if(ind<0){
			return -ind.intValue();
		}else{
			return ind.intValue();
		}
	}
	public List<Hipotese> selecionar(List<Hipotese> hipoteses, List<Integer> rank){
		GeradorRandomico random = new GeradorRandomico();
		List<Hipotese> resultado = new ArrayList<>();
		for(int i = 0; i<tamanho;i++){
			resultado.add(hipoteses.get(rank.get( random.nextInt(0, rank.size()))));
		}
		
		return resultado;
	}
	public List<Hipotese> gerarListaEmbaralhada(List<Hipotese> hipoteses){
		
		List<Hipotese> resultado = new ArrayList<Hipotese>();
		for(int i = 0; i< hipoteses.size();i++) {
			resultado.add(
					hipoteses.get(random.nextInt(0, hipoteses.size()))
					);
		}
		return resultado;
	}
	private void cruzarHipoteses(List<Hipotese> selecionadas, List<Hipotese> auxiliar) {
		for(int i = 0; i< selecionadas.size();i++){
			
		}
	}
	private void inicializaListAuxiliar(List<Hipotese> hipoteses, List<Hipotese> auxiliar){
		List<Hipotese> aux2 = new ArrayList<Hipotese>();
		aux2.addAll(hipoteses);
		GeradorRandomico random = new GeradorRandomico();		
		int index = 0;
		while(auxiliar.size()!= hipoteses.size()){
			index = random.nextInt(0, aux2.size());
			auxiliar.add(aux2.get(index));
			aux2.remove(index);
		}
		
	}
	private List<Integer> criarRoleta(List<Hipotese> selecionadas) {
		List<Integer> resultado = new ArrayList<Integer>();
		for(int y = 0; y < selecionadas.size();y++) {
			int contador = selecionadas.get(y).getIndice().intValue();
			for(int i = 0; i < contador; i++) {
				resultado.add(y);
			}
		}
		return resultado;
	}
	private void gerarFilhos(List<Hipotese> hip, int tamanho) {
		List<Hipotese> resultado = new ArrayList<Hipotese>();
		while(resultado.size()< tamanho) {
			
		}
	}
}
