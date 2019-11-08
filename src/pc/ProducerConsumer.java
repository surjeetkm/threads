package pc;

import java.util.*;

class Producer implements Runnable {
	private LinkedList<Integer> queue;
	private static final int MAX = 20;
	private int value = 1;

	public Producer(LinkedList queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		produce();
	}

	private void produce() {
		synchronized (queue) {
			while (value != MAX) {
				try {
					if (queue.size() == 5) {
						System.out.println("Waiting::");
						queue.wait();
					}
					queue.add(value);
					Thread.sleep(500);
					System.out.println("Producer:" + value);
					value = value + 1;
					queue.notify();
				} catch (Exception e) {
				}
			}
		}
	}

}

class Consumer implements Runnable {
	private LinkedList<Integer> queue;

	public Consumer(LinkedList queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		consume();
	}

	private void consume() {
		synchronized (queue) {
			while (true) {
				try {
					if (queue.isEmpty())
						queue.wait();
					int value = queue.removeFirst();
					System.out.println("Consumer:" + value);
					Thread.sleep(500);
					queue.notify();
				} catch (Exception e) {

				}
			}
		}

	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> list = new LinkedList<Integer>();
		Thread t = new Thread(new Producer(list));
		Thread t2 = new Thread(new Consumer(list));

		t.start();
		t2.start();
	}

}
