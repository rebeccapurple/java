package rebeccapurple.concurrent.function;

import rebeccapurple.Operator;

import java.util.LinkedList;

public abstract class Single<A, T> extends Thread<T> implements rebeccapurple.execute.Single<A>, rebeccapurple.function.Single<A, T> {
    protected final LinkedList<A> __queue = new LinkedList<>();

    protected A pop(){
        synchronized (__queue) {
            return __queue.pollFirst();
        }
    }

    @Override protected T call() throws Throwable { return call(pop()); }

    @Override
    protected boolean dispatched(){
        synchronized (__queue){
            return __queue.size() != 0;
        }
    }

    @Override
    public void execute(A first) {
        synchronized (__queue) {
            if(!functional.object.equal(__queue.peekLast(), first)){
                __queue.add(first);
                synchronized (this){
                    if(!__running){
                        start();
                    }
                }
            }
        }
    }



    public Single(Operator.On<T> callback) {
        super(callback);
    }

    public Single(Operator.On<T> callback, A first) {
        super(callback);
        execute(first);
    }

}
