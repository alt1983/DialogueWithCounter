import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] threadsNames = {"поток 1", "поток 2", "поток 3", "поток 4"};
        int[] times1 = {3, 3, 3, 3};
        int[] times2 = {5, 3, 5, 5};
        final ExecutorService threadPool1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Callable<Integer>> threads1 = new ArrayList<>();
        ArrayList<Callable<Integer>> threads2 = new ArrayList<>();
        for (int i = 0; i < threadsNames.length; i++) {
            threads1.add(new MyCallable(times1[i], threadsNames[i]));
            threads2.add(new MyCallable(times2[i], threadsNames[i]));
        }

        final List<Future<Integer>> tasks = threadPool1.invokeAll(threads1);
        for (Future<Integer> task : tasks) {
            System.out.println("Количесто выполнений потока: " + task.get());
        }
        threadPool1.shutdown();
        final ExecutorService threadPool2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final Integer task = threadPool2.invokeAny(threads2);
        System.out.println("Самый быстрый поток выполнил: " + task);
        threadPool2.shutdown();

    }
}
