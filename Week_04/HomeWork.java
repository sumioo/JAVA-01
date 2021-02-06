import java.util.concurrent.*;

/**
 * HomeWork
 */
public class HomeWork {
    int mainResult;

    class AttachRetrunValueOnThread extends Thread {
        int result;

        public void run() {
            result = sum();
        }

    }

    class AttachRetrunValueOnMainThread extends Thread {

        public void run() {
            mainResult = sum();
        }

    }
    

    public static void main(String[] args) throws Exception {
        HomeWork homeWork = new HomeWork();
        //方法1：将结果保存到线程对象
        AttachRetrunValueOnThread method1 = homeWork.new AttachRetrunValueOnThread();
        method1.start();
        method1.join();
        System.out.println("method1 result " + method1.result);

        //方法2：将结果保存到主线程对象
        Thread method2 = homeWork.new AttachRetrunValueOnMainThread();
        method2.start();
        method2.join();
        System.out.println("method2 result " + homeWork.mainResult);

        //方法3：FatureTask
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>(){
           public Integer call() {
               return sum();
           } 
        });
        new Thread(task).start();
        int r = task.get();
        System.out.println("method3 result " + r);

        //方法4：ExecutorService
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        FutureTask<Integer> task2 = new FutureTask<Integer>(new Callable<Integer>(){
            public Integer call() {
                return sum();
            }
        });

        executorService.submit(task2);
        System.out.println("method4 result " + task2.get());


    }

    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    
}