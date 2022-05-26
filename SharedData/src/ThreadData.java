// Each thread
public class ThreadData {

		private int counter;
		private String threadName = null; 

		public ThreadData(String threadName)
		{
			this.threadName = threadName;
			counter = 0;
		}
		
		public String getThreadName() 
		{ 
			return threadName; 
		}
		
		public synchronized int getCount() 	
		{ 
			return counter; 
		}
		
		public synchronized void setCount()	
		{ 
			++counter; 
		}
}
