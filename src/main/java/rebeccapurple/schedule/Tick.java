package rebeccapurple.schedule;

public class Tick extends Timeout {
    protected boolean __repeatable;

    public void repeatable(boolean v){ __repeatable = v; }

    @Override public boolean repeatable(){ return __repeatable; }

    public Tick(long millisecond, Operator operator) {
        super(millisecond, operator);
        __repeatable = true;
    }
}
