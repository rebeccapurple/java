package rebeccapurple.concurrent.function;

public abstract class Thread<T> extends java.lang.Thread {
    protected final rebeccapurple.Operator.On<T> __callback;
    protected boolean __running;

    protected abstract boolean dispatched();
    protected abstract T call() throws Throwable;

    @Override
    public void run(){
        synchronized (this) { __running = true; }
        while(dispatched()){
            try {
                __callback.on(call(), null);
            } catch (Throwable e) {
                __callback.on(null, e);
            }
        }
        synchronized (this) { __running = false; }
    }

    public Thread(rebeccapurple.Operator.On<T> callback){
        __callback = callback;
        __running = false;
    }
}
