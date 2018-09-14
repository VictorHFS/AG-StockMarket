package stock.concurrency.custom;

import java.util.List;

public class GarbageCollector{
	List<Thread> threads;
	public GarbageCollector(List<Thread> executando) {
		this.threads = executando;
	}	
	
	public void run() {		
		for(int i = 0; i < threads.size()-1; i ++) {
			if(!threads.get(i).isAlive()) {
				threads.get(i).destroy();
			}
		}
	}
}
