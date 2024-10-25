public class DelayedPrint {
    public static void main(String[] args) {
        String sentence = "We have the java learning course!";
        String[] words = sentence.split(" ");

        for (String word : words) {
            System.out.print(word + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}