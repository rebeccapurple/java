package rebeccapurple.scheduler;

import rebeccapurple.exception.CancelledScheduleException;

public abstract class Loop extends rebeccapurple.concurrent.Condition {
    protected Thread __thread = null;
    protected Runnable __onecycle = this::onecycle;
    protected final Wheel wheel = new Wheel(this);

    protected void onecycle(){ wheel.on(this::complete); }

    protected void complete(Task task, Throwable exception){
        if(task.complete(exception)) {
            if(task.repeatable()) {
                wheel.reset(task);
            }
        }
    }

    public <T extends Task> T dispatch(T task){ return wheel.add(task); }
    public <T extends Task> T reset(T task){ return wheel.reset(task); }

    public void cancel(Task task) {
        if(task != null) {
            wheel.del(task);
            task.cancel(new CancelledScheduleException());
        }
    }

    public void on(){
        lock();
        if(__thread == null) {
            __thread = new Thread(this::loop);
            __thread.start();
            suspend();
        }
        unlock();
    }

    protected abstract void loop();
    public abstract void off();

    protected Loop(){}
}
