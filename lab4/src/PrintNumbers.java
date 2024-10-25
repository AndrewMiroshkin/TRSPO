class PrintNumbers implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i += 10) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
        }
    }
}