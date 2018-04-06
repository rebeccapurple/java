package rebeccapurple.function;

public interface Triple<A, B, C, OUT> { OUT call(A first, B second, C third) throws Throwable; }
