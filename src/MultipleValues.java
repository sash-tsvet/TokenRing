import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;


public class MultipleValues implements Wire{
    public AtomicBoolean canWrite;
    private ConcurrentLinkedQueue <Integer> values;
    public Integer read() {
        Integer value = values.poll();
        while (value == null) {
            value = values.poll();
        }
        return value;
    }
    public void write(Integer newValue) {
        values.add(newValue);
    }
    MultipleValues () {
        this.canWrite = new AtomicBoolean(true);
        this.values = new ConcurrentLinkedQueue<Integer>();
    }
}
