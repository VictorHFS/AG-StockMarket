package stock.data.record.service;

import java.util.Date;

import org.springframework.context.annotation.Scope;

import stock.data.enterprise.EmpresaRepository;
import stock.data.extractor.layout.LayoutBovespa;
import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.model.chromosome.Chromossome;

import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SalvarRegistro extends Thread{
	RecordRepository rRepo;
	EmpresaRepository eRepo;
	String registro;
	
	public SalvarRegistro comDependencias(RecordRepository rRepo, EmpresaRepository eRepo) {
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
			Record novo = estruturarRegistro(registro);
			if(!ExisteEmpresa(novo)) {
				eRepo.save(novo.getEmpresa());
			}
			save(novo);				
			System.out.println("Registro - "+this.toString()+" - salvo!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private synchronized void save(Record novo) {
		rRepo.save(novo);
	}
	private boolean ExisteEmpresa(Record novo) {		 
		return eRepo.exists(novo.getEmpresa().getNome());
	}
	public Record checkRegistro(Record registro) throws Exception{
		if (registro == null){
			throw new NullPointerException();
		}else {
			return registro;
		}
	}
	private Record estruturarRegistro(String registro) throws Exception {		
		LayoutBovespa layout = new LayoutBovespa();
		Chromossome crom;
		if(layout.tipoDeRegistro(registro) == stock.data.extractor.layout.Registro.Contacao) {
			Date dataCotacao = layout.dataCotacao(registro);
			Date dataDeVencimento = layout.dataDeVencimento(registro);				
			crom = new Chromossome(layout.nomeResCotacao(registro), (double) layout.fatorDeCotacao(registro),(double) layout.indicadorDeCorrecaoDePreco(registro),
								(double) layout.numeroDeNegociacoes(registro),layout.precoDaMelhorOfertaDeCompra(registro),layout.precoDaMelhorOfertaDeVenda(registro),
								layout.precoDeAbertura(registro),layout.precoDeExercicioEmPontos(registro),layout.precoDeExercicios(registro),
								layout.precoDoUltimoNegocio(registro),layout.precoMaximo(registro),layout.precoMedio(registro),
								layout.precoMinimo(registro),(double) layout.quantidadeTotalDeTitulosNegociados(registro), 
								dataCotacao, dataDeVencimento);
			return checkRegistro(new Record(layout.Id(registro),crom));			
		}	else {
			System.out.println("o registro "+this.getId()+" não é cotação.");
		}
			
			return null;
	}
}
