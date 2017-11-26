import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class OneValue implements Wire {
    public AtomicBoolean canWrite;
    private AtomicInteger value;
    public Integer read() {
        while (canWrite.get() == true) {}
        Integer returnValue = value.get();
        canWrite.set(true);
        return returnValue;
    }
    public void write(Integer newValue) {
        while (canWrite.get() != true) {}
        value.set(newValue);
        canWrite.set(false);
    }
    OneValue () {
        this.canWrite = new AtomicBoolean(true);
        this.value = new AtomicInteger(0);
    }
}
