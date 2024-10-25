import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue<T> {
    private BlockingQueue<T> queue = new LinkedBlockingQueue<>();

    public void enqueue(T item) {
        try {
            queue.put(item);
            System.out.println("Producer added: " + item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public T dequeue() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}

class Producer<T> implements Runnable {
    private MyQueue<T> myQueue;
    private java.util.function.Supplier<T> produceItem;

    public Producer(MyQueue<T> queue, java.util.function.Supplier<T> produceItem) {
        myQueue = queue;
        this.produceItem = produceItem;
    }

    @Override
    public void run() {
        while (true) {
            T item = produceItem.get();
            myQueue.enqueue(item);
            try {
                Thread.sleep(1000); // Імітація часу на створення об'єкта
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer<T> implements Runnable {
    private MyQueue<T> myQueue;
    private String name;

    public Consumer(MyQueue<T> queue, String name) {
        myQueue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            T item = myQueue.dequeue();
            if (item != null) {
                System.out.println(name + " processed: " + item);
            }
            try {
                Thread.sleep(500); // Імітація часу на обробку об'єкта
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}