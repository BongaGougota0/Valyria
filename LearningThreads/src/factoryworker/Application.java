package factoryworker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Application {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		
		Thread worker0 = new Thread(new ProductionLine1(queue));
		Thread worker1 = new Thread(new ProductionLine2(queue));
		
		worker0.start();
		worker1.start();
	}
}
