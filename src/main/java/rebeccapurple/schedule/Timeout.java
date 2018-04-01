package rebeccapurple.schedule;


public class Timeout extends rebeccapurple.scheduler.Task {
    public void ttl(Long v){ __ttl = v; }

    @Override public long timestamp() { return System.currentTimeMillis() + __ttl; }

    public Timeout(long millisecond, Operator operator) {
        super(operator);
        __ttl = millisecond;
    }
}
