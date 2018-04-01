package functional;

public class scheduler {
    private static rebeccapurple.scheduler.Loop __internal = null;

    public static void init(rebeccapurple.scheduler.Loop internal){
        synchronized (scheduler.class) {
            if(__internal == null) {
                __internal = internal;
            }
        }
    }

    public static void on(){ __internal.on(); }
    public static void off(){ __internal.off(); }

    public static <T extends rebeccapurple.scheduler.Task> T reset(T schedule){ return __internal.reset(schedule); }
    public static <T extends rebeccapurple.scheduler.Task> T dispatch(T schedule){ return __internal.dispatch(schedule); }
    public static <T extends rebeccapurple.scheduler.Task> void cancel(T schedule){ __internal.cancel(schedule); }
}
