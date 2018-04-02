package functional;

public class debug {
    public static class Function {
        public interface Empty<IN> { void call(IN in); }
    }
    public static <IN> void run(IN in, Function.Empty<IN> function){ function.call(in); }
}
