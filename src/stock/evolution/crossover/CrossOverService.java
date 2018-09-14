package stock.evolution.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stock.data.enterprise.Empresa;
import stock.data.enterprise.EmpresaRepository;
import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.hyphoteses.service.hipoteseService;
import stock.evolution.hypotheses.HipoteseRepository;
import stock.evolution.hypotheses.Hypotheses;
import stock.evolution.model.generator.GeradorRandomico;
import stock.evolution.mutacao.mutacaoService;
import stock.evolution.selecao.SelecaoService;

@Service
public class CrossOverService {
	@Autowired
	hipoteseService hipoteseService;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	SelecaoService selecao;
	@Autowired
	RecordRepository registroRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	@Autowired
	mutacaoService mutacao;
	int tamanho;
	Empresa empresa;
	List<Record> registros;
	GeradorRandomico random;
	ExecutorService executor;
	int ano;
	public CrossOverService() {
		this.random = new GeradorRandomico();				
	}
	public void proximaGeracao(String nomeEmpresa, int ano) {
		System.out.println("iniciando processo de evolução...");
		this.ano = ano;
		empresa = empresaRepo.getOne(nomeEmpresa);
		this.registros = registroRepo.getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(empresa, ano);
		System.out.println("registros carregados.");
		List<Hypotheses> hipoteses = hipoteseService.buscarHipotesesMaisAptasByEmpresa(empresa);
		System.out.println("hipoteses carregadas na memoria.");
		hipoteseService.deleteAll();
		System.out.println("hipoteses deletadas da base de dados.");
		tamanho = hipoteses.size();
		//hipoteseService.deleteAll();
		List<Integer> rank = new ArrayList<Integer>();
		inicializaRank(hipoteses,rank);
		System.out.println("rank carregado.");
		hipoteses = selecionar(hipoteses,rank);
		System.out.println("hipoteses selecionadas.");
		List<Hypotheses> auxiliar = new ArrayList<Hypotheses>();		
		inicializaListAuxiliar(hipoteses, auxiliar);
		System.out.println("lista auxiliar carregada");
		cruzarHipoteses(hipoteses,auxiliar);				
	}
	private void inicializaRank(List<Hypotheses> hipoteses, List<Integer> rank) {
		Integer vezes = 0;
		for(int i = 0; i<hipoteses.size();i++){ 
			vezes = rankearDouble(hipoteses.get(i).getIndice());
			for(int y = 0; y<vezes; y++){
				rank.add(i);							
			}
		}		
	}
	private int rankearDouble(Double ind){
		int resultado = ind.intValue();		
		if(resultado == 0) {
			return 1;
		}else {
			if(resultado<0){
				return -resultado;
			}else{
				return resultado;
			}			
		}
	}
	public List<Hypotheses> selecionar(List<Hypotheses> hipoteses, List<Integer> rank){
		GeradorRandomico random = new GeradorRandomico();
		List<Hypotheses> resultado = new ArrayList<>();
		for(int i = 0; i<tamanho*5;i++){
			resultado.add(hipoteses.get(rank.get( random.nextInt(0, rank.size()))));
		}
		
		return resultado;
	}
	public List<Hypotheses> gerarListaEmbaralhada(List<Hypotheses> hipoteses){
		
		List<Hypotheses> resultado = new ArrayList<Hypotheses>();
		for(int i = 0; i< hipoteses.size();i++) {
			resultado.add(
					hipoteses.get(random.nextInt(0, hipoteses.size()))
					);
		}
		return resultado;
	}
	private void cruzarHipoteses(List<Hypotheses> selecionadas, List<Hypotheses> auxiliar) {		
		try {			

			executor = Executors.newFixedThreadPool(15);
			System.out.println("iniciando cruzamento de hipoteses...");
			for(int index = 0; index<selecionadas.size();index++) {				
				if(selecionadas.get(index) == null || auxiliar.get(index) == null){
					throw new NullPointerException("Hipotese nula!");
				}
				executor.execute(
						new Cruzar(selecionadas.get(index), auxiliar.get(index))
						.comReposotorio(hipoteseRepo)
						.comService(selecao)
						.comMutacao(mutacao)
						.buscarRegistros(registroRepo)
						);			
			}		
			try {
				executor.shutdown();
				executor.awaitTermination(1, TimeUnit.HOURS);					
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("finalizada evolução.");
	}
	private void inicializaListAuxiliar(List<Hypotheses> hipoteses, List<Hypotheses> auxiliar){
		List<Hypotheses> aux2 = new ArrayList<Hypotheses>();
		aux2.addAll(hipoteses);
		GeradorRandomico random = new GeradorRandomico();		
		int index = 0;
		while(auxiliar.size()!= hipoteses.size()){
			index = random.nextInt(0, aux2.size());
			auxiliar.add(aux2.get(index));
			aux2.remove(index);
		}
		
	}	
}
