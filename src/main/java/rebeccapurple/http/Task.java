package rebeccapurple.http;

import rebeccapurple.Listener;

import java.util.Map;

public abstract class Task implements rebeccapurple.communicator.Task<Message>, rebeccapurple.Request<Message, Message> {
    protected final Request __request;
    protected Response __response;
    protected int __state;
    protected Throwable __exception;
    protected Long __ttl;
    protected Listener<Response> __listener;

    public String uri(){ return __request.uri(); }

    public Map<String, String> headers(){ return __request.headers(); }
    public byte[] body(){ return __request.body(); }

    synchronized protected void on(rebeccapurple.http.Response response){
        if(!is(STATE.COMPLETED) && !is(STATE.CANCELLED)) {
            __state = STATE.COMPLETED;
            __response = response;
            if(__listener != null){
                __listener.on(__response);
            }
        } else {
            functional.log.e("is(STATE.COMPLETED) || is(STATE.CANCELLED)");
        }
    }

    @Override public rebeccapurple.http.Message in() { return __request; }
    @Override public int state() { return __state; }
    @Override public Throwable exception() { return __exception; }
    @Override public Long ttl() { return __ttl; }
    @Override public rebeccapurple.http.Message out() { return __response; }

    public Task(rebeccapurple.http.Request request){
        __request = request;
        __state = STATE.UNKNOWN;
        __exception = null;
        __ttl = null;
        __response = null;
        __listener = null;
    }

    public Task(rebeccapurple.http.Request request, Listener<Response> listener){
        __request = request;
        __state = STATE.UNKNOWN;
        __exception = null;
        __ttl = null;
        __response = null;
        __listener = listener;
    }
}
