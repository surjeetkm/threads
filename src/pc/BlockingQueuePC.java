package pc;

import java.util.concurrent.BlockingQueue;

class Pro implements Runnable{
	private BlockingQueue<Integer> queue;
	
	public Pro(BlockingQueue queue) {
		this.queue=queue;
	}
	public void run() {
		producer();
	}
	public void producer() {
		
	}
}
public class BlockingQueuePC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
