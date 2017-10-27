package Runner.hipoteses.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.cromossomo.Cromossomo;
import Runner.empresa.Empresa;
import Runner.empresa.EmpresaService;
import Runner.gerenciadorDeThreads.custom.FilaDeThreads;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import Runner.random.GeradorRandomico;
import Runner.selecao.SelecaoService;

@Service
@Transactional
public class hipoteseService {
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
	
public void gerarHipoteseThread(String nomeEmpresa, int ano){	
		SelecaoService selecao = new SelecaoService(); 
		Hipotese novo = new Hipotese();
		novo.setAno(ano);
		Empresa empresa = empresaService.get(nomeEmpresa);
		List<Registro> registros = registroRepo.getRegistroByEmpresa(empresa);
		novo.setEmpresa(empresa);
		novo.setCromossomos(gerarCromossomos(novo,ano));
		selecao.classificarHipotese(novo,registros);																
		save(novo);
	}
public synchronized void save(Hipotese novo){
	try {
		//wait();
		novo.setIndice(novo.getUp()-novo.getDown());		
		hipoteseRepo.save(novo);
	}catch(Exception e) {
		e.printStackTrace();
	}
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
	
	@Deprecated
	private Cromossomo gerarCromossomo(String nomeEmpresa) {
		try {
			Empresa empresa = empresaService.get(nomeEmpresa);
			GeradorRandomico gerador = new GeradorRandomico();
			List<Registro> registros = registroRepo.getRegistroByEmpresa(empresa);
			int periodo = gerador.nextInt(1, registros.size()-1);
			Registro r1 = registros.get(0);
			Registro r2 = registros.get(periodo);
			Double fatorDeCotacao = r1.getCromossomo().getFatorDeCotacao()-r2.getCromossomo().getFatorDeCotacao();
			Double indicadorDeCorrecaoDePreco = r1.getCromossomo().getIndicadorDeCorrecaoDePreco()-r2.getCromossomo().getIndicadorDeCorrecaoDePreco();
			Double numeroDeNegociacoes = r1.getCromossomo().getNumeroDeNegociacoes()-r2.getCromossomo().getNumeroDeNegociacoes();
			Double precoDaMelhorOfertaDeCompra = r1.getCromossomo().getPrecoDaMelhorOfertaDeCompra()-r2.getCromossomo().getPrecoDaMelhorOfertaDeCompra();
			Double precoDaMelhorOfertaDeVenda = r1.getCromossomo().getPrecoDaMelhorOfertaDeVenda()-r2.getCromossomo().getPrecoDaMelhorOfertaDeVenda();
			Double precoDeAbertura = r1.getCromossomo().getPrecoDeAbertura()-r2.getCromossomo().getPrecoDeAbertura();
			Double precoDeExercicioEmPontos = r1.getCromossomo().getPrecoDeExercicioEmPontos()-r2.getCromossomo().getPrecoDeExercicioEmPontos();
			Double precoDeExercicios = r1.getCromossomo().getPrecoDeExercicios()-r2.getCromossomo().getPrecoDeExercicios();
			Double precoDoUltimoNegocio = r1.getCromossomo().getPrecoDoUltimoNegocio()-r2.getCromossomo().getPrecoDoUltimoNegocio();
			Double precoMaximo = r1.getCromossomo().getPrecoMaximo()-r2.getCromossomo().getPrecoMaximo();
			Double precoMedio = r1.getCromossomo().getPrecoMedio()-r2.getCromossomo().getPrecoMedio();
			Double precoMinimo = r1.getCromossomo().getPrecoMinimo()-r2.getCromossomo().getPrecoMinimo();
			Double quantidadeTotalDeTitulosNegociados = r1.getCromossomo().getQuantidadeTotalDeTitulosNegociados()-r2.getCromossomo().getQuantidadeTotalDeTitulosNegociados();
			Date dataCotacao = null;
			Date dataDeVencimento = null;			
			Cromossomo novo = new Cromossomo(nomeEmpresa,fatorDeCotacao,indicadorDeCorrecaoDePreco,numeroDeNegociacoes,precoDaMelhorOfertaDeCompra,
					precoDaMelhorOfertaDeVenda,precoDeAbertura,precoDeExercicioEmPontos,precoDeExercicios,precoDoUltimoNegocio,precoMaximo,
					precoMedio,precoMinimo,quantidadeTotalDeTitulosNegociados,dataCotacao,dataDeVencimento);
			//novo.setPeriodo(periodo);
			return novo;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void salvarHipotese(Hipotese h) {
		hipoteseRepo.save(h);
	}
	public List<Hipotese> buscarHipotesesByEmpresa(String nomeEmpresa) {
		return hipoteseRepo.getHipoteseByEmpresa(empresaService.get(nomeEmpresa));		
	}
	public void deleteAll() {
		hipoteseRepo.deleteAll();		
	}
	public List<Hipotese> buscarHipotesesMaisAptasByEmpresa(Empresa empresa) {	
		try {
			List<Hipotese> hipoteses = hipoteseRepo.findTop1000ByEmpresaOrderByIndiceDesc(empresa);			
			return  hipoteses;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	private Hipotese reiniciaHipotese(Hipotese h) {
		try {
			Hipotese nova = new Hipotese(h.getCromossomo());
			nova.setEmpresa(h.getEmpresa());
			nova.setPeriodo(h.getCromossomo().size());
			return nova;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return h;
	}
}
