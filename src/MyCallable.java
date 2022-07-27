import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    int times;
    int real;
    String name;

    public MyCallable(int times, String name) {
        this.times = times;
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        try {
            Thread.currentThread().setName(this.name);
            for (real = 0; real < this.times; real++) {
                Thread.sleep(2500);
            }
        } catch (InterruptedException err) {

        } finally {
            System.out.println(Thread.currentThread().getName() + " итоговый результат: " + real);
            return new Integer(real);
        }

    }
}
