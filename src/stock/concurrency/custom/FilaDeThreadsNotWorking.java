package stock.concurrency.custom;

import java.util.ArrayList;
import java.util.List;

public class FilaDeThreadsNotWorking extends Thread{
	boolean alive;
	GarbageCollector garbageCollector;
	Thread[] executando;
	List<Thread> dormindo = new ArrayList<Thread>();
	public FilaDeThreadsNotWorking(int tamanho) {
		//this.garbageCollector = new GarbageCollector(executando);
		this.executando = new Thread[tamanho];
		this.start();
		this.alive = true;
	}
	public void adicionarThread(Thread nova) {		
		dormindo.add(nova);
	}
	private boolean espacoParaExecutar() {
		if(executando[executando.length]!=null) {
			return true;
		}
		return false;		
	}
	public void setAlive(boolean status) {
		this.alive = status;
	}	
	private boolean verificaFila() {
		for(int i = 0; i < executando.length-1; i++) {
			if(executando[i]== null) {
				if(dormindo.size()>0) {
					return true;
				}
			}	
		}
		
		return false;		
	}
	private int espacoVazioNaFila() {
		for(int i = 0; i < executando.length;i++) {
			if(executando[i] == null) {
				return i;
			}
		}
		return -1;
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
		dormindo.get(0).start();
		int espacoLivre = espacoVazioNaFila();
		if(espacoLivre != -1){			
			executando[espacoLivre] = dormindo.get(0);	
			desenfileiraDormindo(0);
		}		
	}
	@Override
	public void run() {
		try {
			while(alive) {
				sleep(100);		
				if(verificaFila()) {
					adicionaNaFila();
	//				garbageCollector.run(espacoVazioNaFila()-1);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}

}
