package rebeccapurple;

public class thread {
    public static StackTraceElement[] stacktrace(Thread current){ return current.getStackTrace(); }
    public static long id(Thread current){ return current.getId(); }
}
