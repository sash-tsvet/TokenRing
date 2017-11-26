import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int numNodes = 10;
    private static int numMessages = 6;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<Thread>();
        ArrayList<Wire> messages = new ArrayList<Wire>();
        for (int i = 0; i < numNodes; i++) {
            messages.add(new MultipleValues());
            if (i < numMessages) {
                messages.get(i).write(i);
            }
        }
        for (int i = 0; i < numNodes; i++) {
            Runnable task = new MyRunnable(messages.get(i), messages.get((i + 1) % numNodes));
            Thread worker = new Thread(task);
            worker.setName(String.valueOf(i));
            worker.start();
            threads.add(worker);
        }
        int running = 0;
        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    running++;
                }
            }
        } while (running > 0);

    }
}