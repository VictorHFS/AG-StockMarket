package Runner.gerenciadorDeThreads.custom;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Runner.empresa.EmpresaRepository;
import Runner.historicos.RegistroRepository;

@Service
public class FilaDeThreads extends Thread{
	@Autowired
	RegistroRepository rRepo;
	@Autowired
	EmpresaRepository eRepo;
	boolean alive;
	int tamanho;
	List<Thread> executando;
	List<Thread> dormindo = new ArrayList<Thread>();
	public FilaDeThreads() {
		this.tamanho = 200;
		this.executando = new ArrayList<Thread>();
		this.start();
		this.alive = true;
		this.startGarbage();
	}	
	private void startGarbage() {
		Thread garbage = new Thread() {
			@Override
			public void run() {
				try {
					while(alive) {
						sleep(100);
						for(int i = 0; i < executando.size()-1; i ++) {
							if(!executando.get(i).isAlive()) {
								executando.remove(i);								
							}
						}
					}	
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}
		};
		garbage.start();
	}
	public void adicionarThread(Thread nova) {		
		dormindo.add(nova);
	}
	private void setAlive(boolean status) {
		new Thread(){
			@Override
			public void run() {
				try {
					sleep(500);
					while(alive) {
						if(dormindo.size() == 0) {
							alive = false;
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}
		}.start();
		this.alive = status;
	}	
	private boolean verificaFila() {
		if(executando.size() < tamanho ) {
			if(dormindo.size() > 0) {
				return true;
			}		
		}		
		return false;		
	}
	private void desenfileiraDormindo(int espaco){
		Thread atual;				
		for(int i = espaco; i<dormindo.size();i++) {
			if(dormindo.size()>i+1) {
				atual = dormindo.get(i+1);
			}else {
				atual = null;
			}
			dormindo.set(i, atual);
		}
	}
	private void adicionaNaFila() {
		if(dormindo.size()>0) {			
			if(dormindo.get(0)!=null) {				
				dormindo.get(0).start();		
				executando.add(dormindo.get(0));	
				desenfileiraDormindo(0);
			}
		}
	}	
	@Override
	public void run() {
		try {
			while(alive) {
				sleep(200);		
				if(verificaFila()) {
					adicionaNaFila();
				}				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public RegistroRepository getRegistroRepo() {
		return rRepo;
	}
	public EmpresaRepository getEmpresaRepo() {
		return eRepo;
	}
}
