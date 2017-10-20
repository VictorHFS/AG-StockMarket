package Runner.historicos.service;

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

import Runner.AppConfig;
import Runner.Layout.LayoutBovespa;
import Runner.MineracaoDeDados.Leitor;
import Runner.cromossomo.Cromossomo;
import Runner.empresa.EmpresaRepository;
import Runner.gerenciadorDeThreads.custom.FilaDeThreads;
import Runner.historicos.Historico;
import Runner.historicos.Registro;
import Runner.historicos.RegistroRepository;

@Service
@Transactional
public class HistoricoService {
	@Autowired
	RegistroRepository rRepo;
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
