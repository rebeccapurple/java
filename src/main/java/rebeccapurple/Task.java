package rebeccapurple;

public interface Task<IN> {
    IN in();
    int state();
    Throwable exception();
    Long ttl();

    void cancel(Throwable exception);

    default boolean is(int v){ return (state() & v) == v; }
    default <T extends IN> T in(Class<T> c) throws ClassCastException { return c.cast(in()); }
}
