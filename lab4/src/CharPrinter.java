class CharPrinter extends Thread {
    private StringBuilder sharedObject;

    public CharPrinter(StringBuilder sharedObject) {
        this.sharedObject = sharedObject;
    }

    @Override
    public void run() {
        synchronized (sharedObject) {
            for (int i = 0; i < 100; i++) 
                System.out.print(sharedObject);
            System.out.println();
            char newChar = (char) (sharedObject.charAt(0) + 1);
            sharedObject.setCharAt(0, newChar);
        }
    }
}