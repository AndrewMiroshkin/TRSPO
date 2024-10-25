public class AlternatingPrint {
    private static final Object lock = new Object();
    private static boolean dashTurn = true;

    public static void main(String[] args) {
        Thread dashThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (!dashTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print("-");
                    dashTurn = false;
                    lock.notify();
                }
            }
        });

        Thread pipeThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (dashTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print("|");
                    dashTurn = true;
                    lock.notify();
                }
            }
        });

        dashThread.start();
        pipeThread.start();

        try {
            dashThread.join();
            pipeThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
    }
}