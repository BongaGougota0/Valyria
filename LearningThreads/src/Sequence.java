
public class Sequence {
	private int value = 0;
	
// ------	Not so thread safe method
//	public int getNext() {
//		value = value +1;
//		return value;
//	}
	
//	Thread safe.
//	public int getNext() {
//		synchronized(this) {
//			value = value +1;
//			return value;
//		}
//	}
	
	
//	another way of writing a synchronized block, even better
//	method acquires a lock, at this point method executes fully.
//	this process is never intercepted
	public synchronized int getNext() {
			value = value +1;
			return value;
	}
}

