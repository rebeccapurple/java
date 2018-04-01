package rebeccapurple.log;


public interface Method {
    void out(int classification, String type, Date current, long thread, String tag, String message, Throwable exception);
}
