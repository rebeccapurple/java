package rebeccapurple.scheduler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class Task implements rebeccapurple.Task<Long> {
    public interface Operator {
        void call(Task task, Throwable exception, rebeccapurple.Operator.On<Task> callback);
    }

    protected static void cancel(Task task, Throwable exception, rebeccapurple.Operator.On<Task> callback){
        if(task != null){
            if(callback != null){
                callback.on(task, exception);
            } else {
                functional.log.e("callback == null", exception);
            }
        } else {
            functional.log.e("task == null", exception);
        }
    }

    @SerializedName("in")    @Expose protected Long __in;
    @SerializedName("state") @Expose protected int __state;
    @SerializedName("out")   @Expose protected Long __out;

    private final Operator __operator;

    protected Throwable __exception;
    protected Loop __internal;
    protected Long __ttl;

    public abstract long timestamp();

    protected void call(Operator operator, Throwable exception, rebeccapurple.Operator.On<Task> callback) {
        if(__operator!=null){
            __operator.call(this, exception, callback);
        }
    }

    protected void on(Throwable exception, rebeccapurple.Operator.On<Task> callback){ call(__operator, exception, callback); }

    public boolean repeatable(){ return false; }

    synchronized boolean schedulable(){ return is(STATE.UNKNOWN); }

    synchronized Long reset(Loop internal){
        if(!is(STATE.INPROGRESS)) {
            __state = STATE.READY;
            __internal = internal;
            return __in = timestamp();
        }
        return null;
    }

    synchronized Long ready(Loop internal){
        if(is(STATE.UNKNOWN)) {
            __state = STATE.READY;
            __internal = internal;
            return __in = timestamp();
        }
        return null;
    }

    synchronized void unready(){
        __state &= (~STATE.READY);
        if(__state == STATE.UNKNOWN) {
            __in = null;
            __internal = null;
        }
    }

    synchronized boolean complete(Throwable exception){
        if(!is(STATE.CANCELLED) && !is(STATE.COMPLETED)){
            __internal = null;
            __out = System.currentTimeMillis();
            if(exception == null){
                __state = STATE.COMPLETED;
            } else {
                __state = STATE.CANCELLED;
                __exception = exception;
            }
            return true;
        }
        return false;
    }

    @Override public Long in() { return __in; }
    @Override public int state() { return __state; }
    @Override public Throwable exception() { return __exception; }
    @Override public Long ttl() { return __ttl; }

    @Override
    public void cancel(Throwable exception) {
        synchronized (this) {
            if(!is(STATE.CANCELLED) && !is(STATE.COMPLETED)) {
                if(__internal != null){
                    __internal.wheel.del(this);
                }
                __state = STATE.CANCELLED;
                __out = System.currentTimeMillis();
                on(__exception = exception, null);
            }
        }
    }

    protected Task(){ __operator = null; }
    protected Task(Operator operator){ __operator = operator; }
}
