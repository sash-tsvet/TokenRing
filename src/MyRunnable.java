public class MyRunnable implements Runnable {
    private Wire readFrom;
    private Wire writeTo;

    MyRunnable(Wire readFrom, Wire writeTo) {
        this.readFrom = readFrom;
        this.writeTo = writeTo;
    }

    @Override
    public void run() {
        while (true) {
            int value = readFrom.read();
            System.out.println(value + " " + Thread.currentThread().getName() + " read");
            writeTo.write(value);
        }
    }
}