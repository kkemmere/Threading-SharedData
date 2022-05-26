import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ChildThread extends Thread { 
	
	private ThreadData threadData;
	private Thread t = null;
	private CyclicBarrier barrier;
	private SharedCounter myCounter;
	// Counter limit for each child thread
	private final int limit = 10000;
	
	ChildThread(ThreadData threadData, CyclicBarrier barrier, SharedCounter c)
	{
		this.threadData = threadData;
		System.out.println("Creating ClientThread " + threadData.getThreadName() );
		this.barrier = barrier;
		this.myCounter = c;
	}


	public void run() 
	{
		try {
			// CyclicBarrier acts as a barrier of entry for each thread. They are all allowed to start execution when 4 threads are waiting at barrier
			// Each thread doesn't start exactly at the same time but fairly close to the same time using this method.
			System.out.println(threadData.getThreadName() + " is waiting on barrier");
			barrier.await();
			System.out.println(threadData.getThreadName() + " has started.");
		} catch (InterruptedException ex) {
			return;
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (threadData.getCount() < limit) 
		{
			for (int i = 0; i < 100; i++)
			{
				// Not sure if having the thread increment its own counter and the shared counter here is the most efficient
				// Could increment the shared counter from the threadData object but i'm not sure if thats more or less efficient
				threadData.setCount();
				myCounter.increaseCounter();
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// Thread failed to increment by 10,000
				System.out.println("Client thread " + threadData.getThreadName() + 
				           " did not complete it's increments because of: " + e.getMessage());
			}
		}
		
		// Child thread is successful in incrementing by (limit size)
		System.out.println("Child thread " +  threadData.getThreadName() + 
				           " is complete incrementing by " + threadData.getCount());
	}

}
