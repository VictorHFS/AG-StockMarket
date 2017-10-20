package Runner.historicos.service;

import java.util.Date;

import org.springframework.context.annotation.Scope;

import Runner.Layout.LayoutBovespa;
import Runner.cromossomo.Cromossomo;
import Runner.empresa.EmpresaRepository;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SalvarRegistro extends Thread{
	RegistroRepository rRepo;
	EmpresaRepository eRepo;
	String registro;
	
	public SalvarRegistro comDependencias(RegistroRepository rRepo, EmpresaRepository eRepo) {
		this.rRepo = rRepo;
		this.eRepo = eRepo;
		return this;
	}
	public SalvarRegistro(String registro) {	
		this.setName("SalvarRegistro - "+this.getId());
		this.registro = registro;
	}
	public void setRegistro(String registro) {
	}
	@Override
	public void run() {
		try {	
			Registro novo = estruturarRegistro(registro);
			if(!ExisteEmpresa(novo)) {
				eRepo.save(novo.getEmpresa());
			}
			save(novo);				
			System.out.println("Registro - "+this.toString()+" - salvo!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private synchronized void save(Registro novo) {
		rRepo.save(novo);
	}
	private boolean ExisteEmpresa(Registro novo) {		 
		return eRepo.exists(novo.getEmpresa().getNome());
	}
	public Registro checkRegistro(Registro registro) throws Exception{
		if (registro == null){
			throw new NullPointerException();
		}else {
			return registro;
		}
	}
	private Registro estruturarRegistro(String registro) throws Exception {		
		LayoutBovespa layout = new LayoutBovespa();
		Cromossomo crom;
		if(layout.tipoDeRegistro(registro) == Runner.Layout.Registro.Contacao) {
			Date dataCotacao = layout.dataCotacao(registro);
			Date dataDeVencimento = layout.dataDeVencimento(registro);				
			crom = new Cromossomo(layout.nomeResCotacao(registro), (double) layout.fatorDeCotacao(registro),(double) layout.indicadorDeCorrecaoDePreco(registro),
								(double) layout.numeroDeNegociacoes(registro),layout.precoDaMelhorOfertaDeCompra(registro),layout.precoDaMelhorOfertaDeVenda(registro),
								layout.precoDeAbertura(registro),layout.precoDeExercicioEmPontos(registro),layout.precoDeExercicios(registro),
								layout.precoDoUltimoNegocio(registro),layout.precoMaximo(registro),layout.precoMedio(registro),
								layout.precoMinimo(registro),(double) layout.quantidadeTotalDeTitulosNegociados(registro), 
								dataCotacao, dataDeVencimento);
			return checkRegistro(new Registro(layout.Id(registro),crom));			
		}	else {
			System.out.println("o registro "+this.getId()+" não é cotação.");
		}
			
			return null;
	}
}
