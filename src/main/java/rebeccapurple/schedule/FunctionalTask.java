package rebeccapurple.schedule;

public abstract class FunctionalTask extends rebeccapurple.scheduler.Task {
    public interface Operator {
        void call(rebeccapurple.scheduler.Task task, Throwable exception, rebeccapurple.Operator.On<rebeccapurple.scheduler.Task> callback);
    }
    private final Operator __operator;

    @Override protected void on(Throwable exception, rebeccapurple.Operator.On<rebeccapurple.scheduler.Task> callback) { __operator.call(this, exception, callback); }

    protected FunctionalTask(Operator operator){ __operator = operator; }
}
