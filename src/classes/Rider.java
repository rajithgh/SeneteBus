package classes;

public class Rider implements Runnable {

	int threadId;

	public Rider(int id) {
		threadId = id;
	}

	@Override
	public void run() {
		try {
			BusStop.ridersMultiplex.acquire();
			
			BusStop.boardMutex.acquire();
			// BusStop.riderCountMutex.acquire();
			BusStop.incrementRiderCount();
			BusStop.boardMutex.release();
			
			BusStop.waitingOnBusMutex.acquire();

			BusStop.ridersMultiplex.release();

			boardBus();

			BusStop.decrementRiderCount();

			if (BusStop.getRiderCount() == 0) {
				System.out.println("rider count 0; threadId " + threadId);
				BusStop.allAboard.release();
			} else
				BusStop.waitingOnBusMutex.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void boardBus() {
		System.out.println("Rider with the thread id " + threadId
				+ " is boarding");
	}

}
