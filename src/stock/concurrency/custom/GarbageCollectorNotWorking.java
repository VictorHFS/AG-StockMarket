package stock.concurrency.custom;

public class GarbageCollectorNotWorking{
	Thread[] threads;
	public GarbageCollectorNotWorking(Thread[] threads) {
		this.threads = threads;
	}	
	
	public void run(int lenght) {		
		for(int i = 0; i < lenght-1; i ++) {
			if(!threads[i].isAlive()) {
				threads[i].destroy();
			}
		}
	}
}
