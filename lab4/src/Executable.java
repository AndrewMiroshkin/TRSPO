
public class Executable {
    public static void main(String[] args) {
// 1. Створити клас, що розширює Thread
/*
        NewThread newThread = new NewThread();
        newThread.start();
*/

//2. Створити клас, що реалізує Runnable
/*
        Runnable task = new PrintNumbers();

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
 */

// 3. Виведення символу у трьох потоках
/*
        StringBuilder sharedObject = new StringBuilder("a");

        CharPrinter thread1 = new CharPrinter(sharedObject);
        CharPrinter thread2 = new CharPrinter(sharedObject);
        CharPrinter thread3 = new CharPrinter(sharedObject);

        thread1.start();
        thread2.start();
        thread3.start();
*/    

// 4. Змінити клас MyQueue
/*
        MyQueue<Integer> queue = new MyQueue<>();

        Producer<Integer> producer = new Producer<>(queue, () -> ThreadLocalRandom.current().nextInt(100));
        Consumer<Integer> consumer1 = new Consumer<>(queue, "Consumer1");
        Consumer<Integer> consumer2 = new Consumer<>(queue, "Consumer2");

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
*/  
    }  
}