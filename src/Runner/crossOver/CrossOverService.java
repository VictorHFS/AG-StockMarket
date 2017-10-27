package Runner.crossOver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.empresa.Empresa;
import Runner.empresa.EmpresaRepository;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseRepository;
import Runner.hipoteses.service.hipoteseService;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import Runner.mutacao.mutacaoService;
import Runner.random.GeradorRandomico;
import Runner.selecao.SelecaoService;

@Service
public class CrossOverService {
	@Autowired
	hipoteseService hipoteseService;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	SelecaoService selecao;
	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	@Autowired
	mutacaoService mutacao;
	int tamanho;
	Empresa empresa;
	List<Registro> registros;
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
		List<Hipotese> hipoteses = hipoteseService.buscarHipotesesMaisAptasByEmpresa(empresa);
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
		List<Hipotese> auxiliar = new ArrayList<Hipotese>();		
		inicializaListAuxiliar(hipoteses, auxiliar);
		System.out.println("lista auxiliar carregada");
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
	public List<Hipotese> selecionar(List<Hipotese> hipoteses, List<Integer> rank){
		GeradorRandomico random = new GeradorRandomico();
		List<Hipotese> resultado = new ArrayList<>();
		for(int i = 0; i<tamanho*5;i++){
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
		try {			

			executor = Executors.newFixedThreadPool(10);
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
}
