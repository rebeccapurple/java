package rebeccapurple.concurrent.function;

import rebeccapurple.Operator;

import java.util.LinkedList;

public abstract class Pair<A, B, T> extends Thread<T> implements rebeccapurple.execute.Pair<A, B>, rebeccapurple.function.Pair<A, B, T> {
    protected final LinkedList<rebeccapurple.tuple.Pair<A, B>> __queue = new LinkedList<>();

    protected rebeccapurple.tuple.Pair<A, B> pop(){
        synchronized (__queue) {
            return __queue.pollFirst();
        }
    }
    @Override
    protected T call() throws Throwable {
        rebeccapurple.tuple.Pair<A, B> pair = pop();
        return call(pair.first, pair.second);
    }

    @Override
    public void execute(A first, B second) {
        synchronized (__queue) {
            rebeccapurple.tuple.Pair<A, B> item = __queue.peekLast();
            if(item == null || !item.equals(first, second)){
                __queue.add(new rebeccapurple.tuple.Pair<>(first, second));
            }
        }
    }

    @Override
    protected boolean dispatched(){
        synchronized (__queue){
            return __queue.size() != 0;
        }
    }

    public Pair(Operator.On<T> callback, A first, B second) {
        super(callback);
        execute(first, second);
    }
}
