import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Task implements Runnable {

    private int data;

    private List<Integer> target;

    private static CyclicBarrier barrier;

    public Task(int data, CyclicBarrier barrier, List<Integer> target) {
        this.data = data;
        Task.barrier = barrier;
        this.target = target;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    target.add(data);
                }
                barrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getTarget() {
        return target;
    }

    public void setTarget(List<Integer> target) {
        this.target = target;
    }

}

public class Main {
    public static void main(String[] args) {
        final List<Integer> one = new ArrayList<Integer>();
        final List<Integer> two = new ArrayList<Integer>();
        final List<Integer> three = new ArrayList<Integer>();
        final List<Integer> four = new ArrayList<Integer>();
        final List<Task> main = new ArrayList<Task>();
        ExecutorService executer = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            int rounds = 0;

            @Override
            public void run() {
                System.out.println("Rounds : " + rounds);
                rounds++;
                System.out.println(one);
                System.out.println(two);
                System.out.println(three);
                System.out.println(four);
                System.out.println("Switching targets ");
//				int tempRound = rounds % 4;
                main.get(rounds % 4).setTarget(one);
                main.get((rounds+1) % 4).setTarget(two);
                main.get((rounds+2) % 4).setTarget(three);
                main.get((rounds+3) % 4).setTarget(four);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        });
        
        main.add(new Task(1, barrier, one));
        main.add(new Task(2, barrier, two));
        main.add(new Task(3, barrier, three));
        main.add(new Task(4, barrier, four));


        for (Task mains : main) {
            executer.execute(mains);
        }
    }
}