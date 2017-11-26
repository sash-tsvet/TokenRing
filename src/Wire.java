import java.util.concurrent.atomic.AtomicBoolean;

public interface Wire {
    public Integer read();
    public void write(Integer i);
}
