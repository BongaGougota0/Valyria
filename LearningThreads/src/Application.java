
public class Application {

	public static void main(String[] args) {
		Sequence seq = new Sequence();
		Worker w1 = new Worker(seq);
		w1.start();
		
		Worker w2 = new Worker(seq);
		w2.start();
	}

}
