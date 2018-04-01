package rebeccapurple;

public interface Request<IN, OUT> extends Task<IN> {
    OUT out();

    default <T extends OUT> T out(Class<T> c) throws ClassCastException { return c.cast(out()); }
}
