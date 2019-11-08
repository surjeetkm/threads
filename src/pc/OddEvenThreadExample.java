package pc;

class MyPrinter{
	private static final int MAX=20;
	private int value=1;
	private boolean isOddEven=true;
	public synchronized void printEven() {
		while(value!=MAX) {
			try {
				if(isOddEven==true) {
					wait();
				}
				System.out.println("Even:"+value);
				isOddEven=true;
				value=value+1;
				Thread.sleep(500);
				notify();
			}catch(Exception e) {}
		}
	}
	public synchronized void printOdd() {
		while(value!=MAX) {
			try {
				if(isOddEven==false)
					wait();
				System.out.println("Odd:"+value);
				value=value+1;
				isOddEven=false;
				Thread.sleep(500);
				notify();
			}catch(Exception e) {}
		}
	}
}
class EvenOddRunnable implements Runnable{
	private MyPrinter printer;
	private boolean test;
    public EvenOddRunnable(MyPrinter printer,boolean test) {
    	this.printer=printer;
    	this.test=test;
	}
	@Override
	public void run() {
		if(test) {
			printer.printEven();
		}else {
			printer.printOdd();
		}
	}
}
public class OddEvenThreadExample {

	public static void main(String[] args) {
		MyPrinter printer =new MyPrinter();
		EvenOddRunnable even=new EvenOddRunnable(printer, true);
		EvenOddRunnable odd=new EvenOddRunnable(printer, false);
		Thread t1=new Thread(even);
		Thread t2=new Thread(odd);
		t1.start();
		t2.start();
		
	}

}
