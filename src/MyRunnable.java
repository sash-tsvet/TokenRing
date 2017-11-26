import java.time.Duration;
import java.time.Instant;

public class MyRunnable implements Runnable {
    private Wire readFrom;
    private Wire writeTo;
    private Instant start;
    private Instant end;
    private int numLoops;

    MyRunnable(Wire readFrom, Wire writeTo) {
        this.readFrom = readFrom;
        this.writeTo = writeTo;
        start = Instant.now();
        numLoops = 0;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        boolean shouldCount = name.equals("0");
        while (true) {
            int value = readFrom.read();

            if (shouldCount && value == 0) {
                end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                System.out.println("loop: " + numLoops + " latency: "+ timeElapsed.toMillis() +" milliseconds");
                start = end;
                numLoops++;
            }
//            System.out.println(value + " " + Thread.currentThread().getName() + " read");
            writeTo.write(value);
        }
    }
}