import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {
	
	private AtomicInteger counter;
	
	public SharedCounter(int s) {
		this.counter = new AtomicInteger(s);
	}
	
	public synchronized void increaseCounter() {
		counter.getAndIncrement();
	}
	
	public synchronized int getSharedCounter()
	{
		return counter.get();
	}

}
