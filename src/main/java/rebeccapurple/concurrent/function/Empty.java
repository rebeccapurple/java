package rebeccapurple.concurrent.function;

import rebeccapurple.Operator;

public abstract class Empty<OUT> extends Thread<OUT> implements rebeccapurple.Executable {
    protected boolean __dispatched;

    protected synchronized boolean dispatched() { return __dispatched; }
    protected synchronized void done() { __dispatched = false; }
    protected synchronized void dispatching(){ __dispatched = true; }

    @Override
    public void execute(){
        dispatching();
        synchronized (this){
            if(!__running){
                start();
            }
        }
    }

    @Override
    public void run(){
        synchronized (this) { __running = true; }
        while(dispatched()){
            done();
            try {
                __callback.on(call(), null);
            } catch (Throwable e) {
                __callback.on(null, e);
            }
        }

        synchronized (this) { __running = false; }
    }

    public Empty(Operator.On<OUT> callback) {
        super(callback);
        __dispatched = false;
    }

    public Empty(Operator.On<OUT> callback, boolean dispatching) {
        super(callback);
        if(__dispatched = dispatching){
            start();
        }
    }
}
