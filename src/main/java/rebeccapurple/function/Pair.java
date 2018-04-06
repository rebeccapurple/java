package rebeccapurple.function;

public interface Pair<A, B, OUT> { OUT call(A first, B second) throws Throwable; }
