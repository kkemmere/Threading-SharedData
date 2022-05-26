import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
		
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		// TODO Auto-generated method stub
		
		SharedCounter sharedCount = new SharedCounter(0);
		
		CyclicBarrier barrier = new CyclicBarrier(5);

		// Construct a couple of parameter objects, each to be used by a different child thread.
		ThreadData thread1Data = new ThreadData("Thread_1");
		ThreadData thread2Data = new ThreadData("Thread_2");
		ThreadData thread3Data = new ThreadData("Thread_3");
		ThreadData thread4Data = new ThreadData("Thread_4");
				
	    ChildThread thread1 = new ChildThread(thread1Data, barrier, sharedCount);
		Thread t1 = new Thread (thread1, "Thread_1");
		t1.start(); 

	    ChildThread thread2 = new ChildThread(thread2Data, barrier, sharedCount);
		Thread t2 = new Thread (thread2, "Thread_2");
		t2.start(); 
		
		ChildThread thread3 = new ChildThread(thread3Data, barrier, sharedCount);
		Thread t3 = new Thread (thread3, "Thread_3");
		t3.start(); 
		
		ChildThread thread4 = new ChildThread(thread4Data, barrier, sharedCount);
		Thread t4 = new Thread (thread4, "Thread_4");
		t4.start(); 
		// All threads are waiting at the barrier now
		
		// Initial thread releases the barrier for all child threads starting them at the same time (almost).
		barrier.await();

		for (int i = 1; i < 100; i++)
		{		
			try {Thread.sleep(500);}
			catch(InterruptedException e) {System.out.println("Main thread InterruptedException: " + e.getMessage());}
					
			// Get the current count from shared counter 
			if (sharedCount.getSharedCounter() >= 40000) break;
					
			System.out.println("Initial thread: Shared counter value : " + sharedCount.getSharedCounter());
		}
		System.out.println("Thread 1 Count = " + thread1Data.getCount() + ", Thread 2 Count = " + thread2Data.getCount() + ", Thread 3 Count = " + thread3Data.getCount() + ", Thread 4 Count = " + thread4Data.getCount() + ", Shared Count = " + sharedCount.getSharedCounter());
		System.out.println("Initial thread is complete.");

	}

}
