package pc;

import java.util.*;

class Producer implements Runnable {

	private LinkedList<Integer> list;
	private static final int SIZE = 10;
	private int value = 1;

	public Producer(LinkedList list) {
		this.list = list;
	}

	@Override
	public void run() {
		try {
			producer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void producer() throws InterruptedException {
		int value = 0; 
        while (true) 
        { 
            synchronized (this) 
            { 
                // producer thread waits while list 
                // is full 
                while (list.size()==SIZE) 
                    list.wait(); 

                System.out.println("Producer produced-"
                                              + value); 

                // to insert the jobs in the list 
                list.add(value++); 

                // notifies the consumer thread that 
                // now it can start consuming 
                notify(); 

                // makes the working of program easier 
                // to  understand 
                Thread.sleep(1000); 
            } 
        } 
	}

}

class Consumer implements Runnable {

	private LinkedList<Integer> list;
	private static final int SIZE = 10;
	private int value = 1;

	public Consumer(LinkedList list) {
		this.list = list;
	}

	@Override
	public void run() {
		try {
			consumer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void consumer() throws InterruptedException {
		while (true) 
        { 
            synchronized (this) 
            { 
                // consumer thread waits while list 
                // is empty 
                while (list.size()==0) 
                    list.wait(); 

                //to retrive the ifrst job in the list 
                int val = list.removeFirst(); 

                System.out.println("Consumer consumed-"
                                                + val); 

                // Wake up producer thread 
                notify(); 

                // and sleep 
                Thread.sleep(1000); 
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
