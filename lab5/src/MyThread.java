import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class MyThread {
    private static final int n = 4;
    private static final double a = 1.0;
    private static final double x = 8.0;
    private static final List<Double> results = new ArrayList<>(); 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BiFunction<Integer, Integer, Double> A = (threadNumber, n) -> {
            double result = 0.0;
            for(int i = 0; i < n; ++i) 
                result +=  a * x;
            return result;
        };

        BiFunction<Integer, Integer, Double> E = (threadNumber, n) -> {
            double result = 0.0;
            for(int i = 1; i < n + 1; ++i) 
                result +=  Math.pow(i, 2) / (1 + Math.pow(i, 2));
            return result;
        };

        BiFunction<Integer, Integer, Double> C = (threadNumber, n) -> {
            double result = 0.0;
            for(int k = 1; k < n + 1; ++k) 
                result +=  1 / Math.pow(k, 2);
            return result;
        };

        Thread threadA = new Thread(new RunnableTask(n, A, 0, results));
        Thread threadE = new Thread(new RunnableTask(n, E, 1, results));
        Thread threadC = new Thread(new RunnableTask(n, C, 2, results));

        threadA.start();
        threadE.start();
        threadC.start();

        try {
            threadA.join();
            threadE.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double sum = 0.0;
        for (double result : results) {
            sum += result;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue = df.format(sum);;
        System.out.println("Сумма результатов: " + formattedValue);
    }
}

class RunnableTask implements Runnable {
    private static int threadNumber;
    private int n;
    private BiFunction<Integer, Integer, Double> formula;
    private int index;
    private List<Double> results;

    public RunnableTask(int n, BiFunction<Integer, Integer, Double> task, int index, List<Double> results) {
        ++threadNumber;
        this.n = n;
        this.formula = task;
        this.index = index;
        this.results = results;
    }

    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue;
        Double y = formula.apply(threadNumber, n);
        synchronized (results) {
            results.add(y); 
            formattedValue = df.format(y);
        }
        System.out.println("Результат функции " + index + ": " + formattedValue);
    }
}