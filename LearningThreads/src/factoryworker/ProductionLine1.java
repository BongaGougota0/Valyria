package factoryworker;

import java.util.concurrent.BlockingQueue;

// Initiates production line
public class ProductionLine1 implements Runnable{
	private BlockingQueue<Integer> queue = null;
	private int index = 0;
	
	public ProductionLine1(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized(this) {
			while(true) {
				
				try {
					// Uncomment for slower execution.
//					Thread.sleep(2000);
					queue.put(index++);
					System.out.println("Prod1, passing task "+ index+" to production line 2:");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
