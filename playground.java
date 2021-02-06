
import java.util.stream.IntStream;

public class playground {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    public static void countTest() {
        int loop = 1000;
        SyncCounter counter = new SyncCounter();
        IntStream.range(0, loop).parallel().forEach(i -> counter.incrAndGet());
        System.out.printf("sync counter = %d\n", counter.getCount());
        
        Counter counter2 = new Counter();
        IntStream.range(0, 1000).parallel().forEach(i -> counter2.incrAndGet());
        System.out.printf("counter  = %d\n", counter2.getCount());

        
    }

}


class SyncCounter {

    private int count;

    public synchronized int incrAndGet() {
        return ++count;
    }

    public int addAndGet () {
        synchronized(this) {
            return ++count;
        }
    }

    public int getCount() {
        return count;
    }
}

class Counter {

    private volatile int count;

    public int incrAndGet() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}


class FinalTest {
    private final int i;


    FinalTest() {
        i = 1;
    }

    public void test() {
    }
}