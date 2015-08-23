package classes;

import java.util.Random;

public class Runner {

	public static int BUS_CAPACITY = 50;
	public static int THREAD_COUNT = 100;

	public static void main(String args[]) throws InterruptedException {

		Thread threads[] = new Thread[THREAD_COUNT];

		createThreads(threads);

		for (int i = 0; i < THREAD_COUNT; i++) {
			threads[i].start();
		}

		for (int i = 0; i < THREAD_COUNT; i++) {
			threads[i].join();
		}
		
		return;
	}

	public static void createThreads(Thread threads[]) {
		
		int offSet = 40;
		Random rand = new Random();
		int MOD = offSet  + rand.nextInt(THREAD_COUNT-offSet); 
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			if ((i % MOD) == 0)
				threads[i] = new Thread(new Bus(i));
			else
				threads[i] = new Thread(new Rider(i));
		}
	}
}
