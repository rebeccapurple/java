package rebeccapurple;

/**
 *
 * @author      sean <hyunsik-park@textory.com>
 * @since       0.0.1
 * @date        2018. 4. 4.
 * @visibility  public
 * @stereo      interface
 * @param       <IN>  input object type
 * @param       <OUT> output object type
 */
public interface Request<IN, OUT> extends Task<IN> {
    OUT out();

    default <T extends OUT> T out(Class<T> c) throws ClassCastException { return c.cast(out()); }
}
