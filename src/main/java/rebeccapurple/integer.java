package rebeccapurple;

public class integer extends rebeccapurple.concurrent.Lock {
    private int __v = 0;

    public int issue(Function<Integer, Boolean> validator){
        lock();
        int v = ++__v;
        while(!rebeccapurple.exceptional.safe(v, validator, false)){
            v = ++__v;
        }
        unlock();
        return v;
    }
}
