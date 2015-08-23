package classes;

import java.util.concurrent.Semaphore;

public class BusStop {
	static Semaphore waitingOnBusMutex = new Semaphore(1);
	static Semaphore currentBusMutex = new Semaphore(1);
	static Semaphore ridersMultiplex = new Semaphore(Runner.BUS_CAPACITY);
	static Semaphore riderCountMutex = new Semaphore(1);
	static Semaphore boardMutex = new Semaphore (1);
	static Semaphore allAboard = new Semaphore(1);
	
	
	static int currentBus ;
	public static synchronized int getCurrentBus() {
		return currentBus;
	}
	public static synchronized void setCurrentBus(int bus) {
		currentBus = bus;
	}
	

	private static int riderCount = 0;
	
	public static int getRiderCount(){
		return riderCount;
	}
	
	public static synchronized void incrementRiderCount(){
		riderCount++;
	}
	
	public static synchronized void decrementRiderCount(){
		riderCount--;
	}
}
