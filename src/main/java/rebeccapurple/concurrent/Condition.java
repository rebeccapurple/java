package rebeccapurple.concurrent;

public class Condition extends Lock {
    private final java.util.concurrent.locks.Condition __condition;

    public void suspend(){
        try {
            __condition.await();
        } catch (InterruptedException e) {
            rebeccapurple.log.e("__condition.await()", e);
        }
    }

    public void suspend(long nano){
        if(nano > 0) {
            try {
                __condition.awaitNanos(nano);
            } catch (InterruptedException e) {
                rebeccapurple.log.e("__condition.awaitNanos(nano)", e);
            }
        } else {
            suspend();
        }
    }

    public void resume(){
        __condition.signal();
    }

    public void resume(boolean all){
        if(all) {
            __condition.signalAll();
        } else {
            resume();
        }
    }

    public Condition(){
        super();
        __condition = __o.newCondition();
    }

    public Condition(java.util.concurrent.locks.Lock o){
        super(o);
        __condition = __o.newCondition();
    }
}
