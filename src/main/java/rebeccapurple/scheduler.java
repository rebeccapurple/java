package rebeccapurple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class scheduler {
    public static void log(Task task, Throwable exception, rebeccapurple.Operator.On<Task> callback) {
        rebeccapurple.log.e(task, exception);
        if(callback != null){
            callback.on(task, exception);
        }
    }

    public static abstract class Task implements rebeccapurple.Task<Long> {
        public interface Operator {
            void call(Task task, Throwable exception, rebeccapurple.Operator.On<Task> callback);
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

    public static class Wheel extends rebeccapurple.concurrent.Lock {
        private final Loop __internal;
        private HashMap<Task, Long> __tasks = new HashMap<>();
        private TreeMap<Long, LinkedHashSet<Task>> __wheel = new TreeMap<>();

        public <T extends Task> T add(T task) {
            if(task != null) {
                lock();
                if(!__tasks.containsKey(task)) {
                    Long timestamp = task.ready(__internal);
                    if(timestamp != null){
                        LinkedHashSet<Task> wheel = collection.get(__wheel, timestamp, k -> new LinkedHashSet<>());
                        if(wheel.add(task)) {
                            __tasks.put(task, timestamp);
                        } else {
                            rebeccapurple.log.e("wheel.add(task)");
                            task.unready();
                        }
                    } else {
                        rebeccapurple.log.e("task.ready(__internal) == null");
                    }
                } else {
                    rebeccapurple.log.e("__tasks.containsKey(task)");
                }
                unlock();
            } else {
                rebeccapurple.log.e("task == null");
            }
            return task;
        }

        public <T extends Task> T reset(T task) {
            if(task != null) {
                lock();
                Long timestamp = task.reset(__internal);
                if(timestamp != null) {
                    Long previous = __tasks.put(task, timestamp);
                    if(previous != null && previous!=timestamp){
                        LinkedHashSet<Task> wheel = __wheel.get(previous);
                        if(wheel != null) {
                            wheel.remove(task);
                            if(wheel.size() == 0){
                                __wheel.remove(previous);
                            }
                        }
                    }
                    if(previous == null || previous != timestamp){
                        LinkedHashSet<Task> wheel = collection.get(__wheel, timestamp, k -> new LinkedHashSet<>());
                        if(!wheel.add(task)){
                            rebeccapurple.log.e("wheel.add(task) == false");
                            __tasks.remove(task);
                            task.unready();
                        }
                    }
                } else {
                    rebeccapurple.log.e("timestamp == null");
                }
                unlock();
            } else {
                rebeccapurple.log.e("task == null");
            }
            return task;
        }

        public void del(Task task){
            if(task != null){
                lock();
                Long timestamp = __tasks.remove(task);
                if(timestamp != null) {
                    LinkedHashSet<Task> wheel = __wheel.get(timestamp);
                    if(wheel != null){
                        wheel.remove(task);
                        if(wheel.size() == 0){
                            __wheel.remove(timestamp);
                        }
                    }
                    task.unready();
                }
                unlock();
            } else {
                rebeccapurple.log.e("task == null");
            }
        }

        private void on(Task task, Operator.On<Task> callback){
            task.__state = Task.STATE.INPROGRESS;
            task.on(null, callback);
        }

        protected void on(Operator.On<Task> callback){
            lock();
            Map.Entry<Long, LinkedHashSet<Task>> entry = __wheel.firstEntry();
            while(entry != null && entry.getKey() <= System.currentTimeMillis()) {
                entry = __wheel.pollFirstEntry();
                LinkedHashSet<Task> wheel = entry.getValue();
                collection.remove(__tasks, wheel);
                unlock();
                collection.each(wheel, task -> on(task, callback));
                lock();
                entry = __wheel.firstEntry();
            }
            unlock();
        }

        public Wheel(Loop internal){ __internal = internal; }
    }

    public static abstract class Loop extends rebeccapurple.concurrent.Condition {
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
                task.cancel(new rebeccapurple.exception.CancellationScheduleException());
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

    private static Loop __internal = null;

    public static void init(Loop internal){
        synchronized (scheduler.class) {
            if(__internal == null) {
                __internal = internal;
            }
        }
    }

    public static void on(){ __internal.on(); }
    public static void off(){ __internal.off(); }

    public static <T extends Task> T reset(T schedule){ return __internal.reset(schedule); }
    public static <T extends Task> T dispatch(T schedule){ return __internal.dispatch(schedule); }
    public static <T extends Task> void cancel(T schedule){ __internal.cancel(schedule); }
}
