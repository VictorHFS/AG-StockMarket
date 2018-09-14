package stock.data.record.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import stock.AppConfig;
import stock.concurrency.custom.FilaDeThreads;
import stock.data.enterprise.EmpresaRepository;
import stock.data.extractor.layout.LayoutBovespa;
import stock.data.extractor.reader.Leitor;
import stock.data.record.Historic;
import stock.data.record.Record;
import stock.data.record.RecordRepository;
import stock.evolution.model.chromosome.Cromossomo;

@Service
@Transactional
public class HistoricoService {
	@Autowired
	RecordRepository rRepo;
	@Autowired
	EmpresaRepository eRepo;	
	@Autowired
	FilaDeThreads fila;		
	ExecutorService executor;
	public HistoricoService() {
		executor = Executors.newFixedThreadPool(200);
	}
	public void gerarHistoricoThread(String caminho) throws InterruptedException {	
		Leitor leitor = new Leitor();
		final ArrayList<String> registros = leitor.ler(caminho);			
		for (int i = 0; i < registros.size(); i++) {	
			
			executor.execute(new SalvarRegistro(registros.get(i))
					.comDependencias(fila.getRegistroRepo(),fila.getEmpresaRepo())
					);
			/*fila.adicionarThread(
					new SalvarRegistro(registros.get(i))
					.comDependencias(fila.getRegistroRepo(),fila.getEmpresaRepo())
					);*/		
			System.out.println("Registro - "+i+" - iniciado");
		}
		executor.shutdown();
	}
}
