package Runner.hipoteses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;

import Runner.cromossomo.Cromossomo;
import Runner.empresa.Empresa;
import Runner.empresa.EmpresaService;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import Runner.random.GeradorRandomico;
import Runner.selecao.SelecaoService;

public class HipoteseFactory {

	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	EmpresaService empresaService;
	@Autowired
	SelecaoService selecaoService;
	ExecutorService executor;
	GeradorRandomico gerador = new GeradorRandomico();
	
	public void criar(String nomeEmpresa, int ano){	
		SelecaoService selecao = new SelecaoService(); 
		Hipotese novo = new Hipotese();
		System.out.println(novo +" - iniciado!");
		novo.setAno(ano);
		Empresa empresa = empresaService.get(nomeEmpresa);
		List<Registro> registros = registroRepo.getRegistroByEmpresa(empresa);
		novo.setEmpresa(empresa);
		novo.setCromossomos(gerarCromossomos(novo,ano));
		selecao.classificarHipotese(novo,registros);																
		save(novo);
		System.out.println(novo + "- salvo!");
	}
	
	private List<Cromossomo> gerarCromossomos(Hipotese novo, int ano) {
		try {		
			List<Registro> registros = registroRepo.getRegistroByEmpresaAndAnoOrderByCromossomoDataCotacaoAsc(novo.getEmpresa(),ano);
			int periodo = 0;
			while(periodo<=0) {
				periodo = gerador.nextInt(0, 150);
			}
			int inicioPeriodo = gerador.nextInt(0, periodo);
			novo.setPeriodoFim(periodo);
			novo.setPeriodoInicio(inicioPeriodo);
			novo.setPeriodo(periodo - inicioPeriodo);
			List<Cromossomo> resultado = new ArrayList<>();
			for(int i=inicioPeriodo;i<periodo;i++) {
				resultado.add(registros.get(i).getCromossomo());
			}			
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized void save(Hipotese novo){
		try {
			//wait();
			novo.setIndice(novo.getUp()-novo.getDown());		
			hipoteseRepo.save(novo);
			System.out.println("Hipotese - "+novo.getId()+" - salva!");		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
