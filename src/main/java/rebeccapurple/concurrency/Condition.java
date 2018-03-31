package rebeccapurple.concurrency;

public class Condition {
    private java.util.concurrent.locks.Condition __condition;

    public void suspend(){
        try {
            __condition.await();
        } catch (InterruptedException e) {
            rebeccapurple.log.e("", e);
        }
    }
}
