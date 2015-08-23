package classes;

public class Bus implements Runnable {

	int threadId;

	public Bus(int id) {
//		System.out.printf("bus %d created\n", id);
		threadId = id;
	}

	@Override
	public void run() {

		try {

			BusStop.currentBusMutex.acquire();

			BusStop.setCurrentBus(threadId);

			BusStop.boardMutex.acquire();

//			System.out.printf("bus %d is waiting\n", threadId);

			if (BusStop.getRiderCount() > 0) {
				BusStop.waitingOnBusMutex.release();
				BusStop.allAboard.acquire();
			}

			BusStop.boardMutex.release();

			depart();

			BusStop.currentBusMutex.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void depart() {
		System.out.println("Bus with thread id " + threadId + " departed.");
	}
}
