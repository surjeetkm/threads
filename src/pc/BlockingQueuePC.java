package pc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Pro implements Runnable{
	private BlockingQueue<Integer> queue;
	private int value=1;
	public Pro(BlockingQueue queue) {
		this.queue=queue;
	}
	public void run() {
		producer();
	}
	public void producer() {
		while(true) {
			try {
				for(int i=1;i<1000;i++) {
					queue.put(i);
					System.out.println("Producer:"+i);
					Thread.sleep(1000);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
class Cons implements Runnable{
	private BlockingQueue<Integer> queue;
	private int value=1;
	public Cons(BlockingQueue queue) {
		this.queue=queue;
	}
	public void run() {
		consumer();
	}
	public void consumer() {
		while(true) {
			try {
				int val = queue.take();
				System.out.println("Cons:"+ val);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
public class BlockingQueuePC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numProducers = 4;
        int numConsumers = 3;
        
		BlockingQueue<Integer> queue=new LinkedBlockingQueue<Integer>(1);
		Thread t1=new Thread(new Pro(queue));
		Thread t2=new Thread(new Cons(queue));
		for (int i = 0; i < numProducers; i++) {
			new Thread(new Pro(queue)).start();;
		}
		for (int i = 0; i < numConsumers; i++) {
			new Thread(new Cons(queue)).start();;
		}
	}

}
