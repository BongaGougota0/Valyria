
public class Worker extends Thread{
	private Sequence seq = null;
	

	public Worker(Sequence seq) {
		this.seq = seq;
	}


	@Override
	public void run() {
		for( int i = 0; i < 100; i++) {
			System.out.println("This is thread "+
					Thread.currentThread().getName()+" value= "+seq.getNext());
		}
	}
	
}
