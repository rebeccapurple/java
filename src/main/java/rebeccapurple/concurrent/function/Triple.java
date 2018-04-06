package rebeccapurple.concurrent.function;

import rebeccapurple.Operator;

import java.util.LinkedList;

public abstract class Triple<A, B, C, T> extends Thread<T> implements rebeccapurple.execute.Triple<A, B, C>, rebeccapurple.function.Triple<A, B, C, T> {
    protected final LinkedList<rebeccapurple.tuple.Triple<A, B, C>> __queue = new LinkedList<>();

    protected rebeccapurple.tuple.Triple<A, B, C> pop(){
        synchronized (__queue) {
            return __queue.pollFirst();
        }
    }
    @Override
    protected T call() throws Throwable {
        rebeccapurple.tuple.Triple<A, B, C> triple = pop();
        return call(triple.first, triple.second, triple.third);
    }

    @Override
    public void execute(A first, B second, C third) {
        synchronized (__queue) {
            rebeccapurple.tuple.Triple<A, B, C> item = __queue.peekLast();
            if(item == null || !item.equals(first, second, third)){
                __queue.add(new rebeccapurple.tuple.Triple<>(first, second, third));
            }
        }
    }

    @Override
    protected boolean dispatched(){
        synchronized (__queue){
            return __queue.size() != 0;
        }
    }

    public Triple(Operator.On<T> callback, A first, B second, C third) {
        super(callback);
        execute(first, second, third);
    }
}
