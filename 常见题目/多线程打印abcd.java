public class ThreadSortPrintABCD implements Runnable {
	private static int newIndex = 0;
	private static int runIndex = 0;
	private int id;
	private boolean isFirstRun;
	private String name;
	private Object self;
	private Object next;
	
 
	private ThreadSortPrintABCD(String name, Object self, Object next) {
		id = newIndex++;
		this.name = name;
		this.self = self;
		this.next = next;
	}
 
	@Override    
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (self) {
            	if(id > runIndex) {
            		try {
						self.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
            	}
            	try {
					Thread.sleep((int)(Math.random()*1000));
				} catch (InterruptedException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
            	System.out.print(name);
            	if(!isFirstRun) {
            		runIndex++;
            		isFirstRun = true;
            	}
                count--;
                synchronized (next) {
                    next.notify();
                }
                if(count > 0) {
	                try {
	                	self.wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
                }
            }     
    
        }
    }     
    
    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        Object d = new Object();
        ThreadSortPrintABCD pa = new ThreadSortPrintABCD("A", a, b);
        ThreadSortPrintABCD pb = new ThreadSortPrintABCD("B", b, c);
        ThreadSortPrintABCD pc = new ThreadSortPrintABCD("C", c, d);
        ThreadSortPrintABCD pd = new ThreadSortPrintABCD("D", d, a);
             
             
        new Thread(pa).start();
        new Thread(pb).start();
        new Thread(pc).start();
        new Thread(pd).start();
    }
}
