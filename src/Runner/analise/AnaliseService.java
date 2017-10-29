package Runner.analise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Runner.cromossomo.Cromossomo;
import Runner.empresa.EmpresaRepository;
import Runner.hipoteses.Hipotese;
import Runner.hipoteses.HipoteseRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;

@Service
@Transactional
public class AnaliseService {
	@Autowired
	RegistroRepository registroRepo;
	@Autowired
	HipoteseRepository hipoteseRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	private List<Cromossomo> historicos;
	Hipotese atual;
	public boolean analisarAcoes(List<Registro> registros,String nomeEmpresa){
		//as hipoteses devem estar ordenadas por periodo
		inicializaHistorico(registros);		 
		Hipotese similar = detectarHipoteseSimilar(
				hipoteseRepo.getHipoteseByEmpresaOrderByPeriodo(
						empresaRepo.getOne(nomeEmpresa)
						)
				);			
		return lucro(similar);
	}
	private boolean lucro(Hipotese similar) {
		int size = historicos.size();
		return historicos.get(size-1).getPrecoDoUltimoNegocio() < historicos.get(size).getPrecoDoUltimoNegocio();
	}
	private void inicializaHistorico(List<Registro> registros) {
		try {
			historicos = new ArrayList<Cromossomo>();
			for(Registro r:registros) {
				historicos.add(r.getCromossomo());
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	private Double similaridade(Hipotese hipotese) {				
		return compararComDadosReais(hipotese);
	}
	private Hipotese detectarHipoteseSimilar(List<Hipotese> hipoteses){
		if(hipoteses.size()>0) {
			if(similaridade(atual)>similaridade(hipoteses.get(0))) {
				hipoteses.remove(0);
				return detectarHipoteseSimilar(hipoteses);
			}else {
				atual = hipoteses.get(0);
				hipoteses.remove(0);
				return detectarHipoteseSimilar(hipoteses);
			}						
		}else {
			return atual;
		}
	}	
	private Double compararComDadosReais(Hipotese hipotese) {
		try {
			Double indice = 0.0;
			Cromossomo cromossomoHipotetico,cromossomoReal;		
			List<Cromossomo> cromossomosHipoteticos = hipotese.getCromossomo();
			List<Cromossomo> dadosReais = historicos.subList(historicos.size()-1- cromossomosHipoteticos.size(), historicos.size()-1);
			for(int i = 0; i< dadosReais.size();i++){
				if(cromossomosHipoteticos.size()< dadosReais.size()) {
					System.out.println("erro");
				}
				cromossomoReal = dadosReais.get(i);
				cromossomoHipotetico = cromossomosHipoteticos.get(i);
				indice = indice + (DiferenciarDoubles(cromossomoHipotetico.getFatorDeCotacao(), cromossomoReal.getFatorDeCotacao())
					+ DiferenciarDoubles(cromossomoHipotetico.getIndicadorDeCorrecaoDePreco(), cromossomoReal.getIndicadorDeCorrecaoDePreco())
					+ DiferenciarDoubles(cromossomoHipotetico.getNumeroDeNegociacoes(), cromossomoReal.getNumeroDeNegociacoes())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDaMelhorOfertaDeCompra(), cromossomoReal.getPrecoDaMelhorOfertaDeCompra())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDaMelhorOfertaDeVenda(), cromossomoReal.getPrecoDaMelhorOfertaDeVenda())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDeAbertura(), cromossomoReal.getPrecoDeAbertura())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDeExercicioEmPontos(), cromossomoReal.getPrecoDeExercicioEmPontos())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDeExercicios(), cromossomoReal.getPrecoDeExercicios())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoDoUltimoNegocio(), cromossomoReal.getPrecoDoUltimoNegocio())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoMaximo(), cromossomoReal.getPrecoMaximo())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoMedio(), cromossomoReal.getPrecoMedio())
					+ DiferenciarDoubles(cromossomoHipotetico.getPrecoMinimo(), cromossomoReal.getPrecoMinimo())
					+ DiferenciarDoubles(cromossomoHipotetico.getQuantidadeTotalDeTitulosNegociados(), cromossomoReal.getQuantidadeTotalDeTitulosNegociados())
					)/13;						
			}
			return indice;
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return 0.0;
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
