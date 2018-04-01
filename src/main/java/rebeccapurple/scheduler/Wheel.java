package rebeccapurple.scheduler;

import functional.collection;
import rebeccapurple.Operator;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class Wheel extends rebeccapurple.concurrent.Lock {
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
                        functional.log.e("wheel.add(task)");
                        task.unready();
                    }
                } else {
                    functional.log.e("task.ready(__internal) == null");
                }
            } else {
                functional.log.e("__tasks.containsKey(task)");
            }
            unlock();
        } else {
            functional.log.e("task == null");
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
                        functional.log.e("wheel.add(task) == false");
                        __tasks.remove(task);
                        task.unready();
                    }
                }
            } else {
                functional.log.e("timestamp == null");
            }
            unlock();
        } else {
            functional.log.v("task == null");
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
            functional.log.e("task == null");
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
