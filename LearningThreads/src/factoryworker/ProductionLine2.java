package factoryworker;

import java.util.concurrent.BlockingQueue;

// Gets and process
public class ProductionLine2 implements Runnable{
	private BlockingQueue<Integer> queue = null;
	
	public ProductionLine2(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("Production line 2 process:: "+queue.take());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
