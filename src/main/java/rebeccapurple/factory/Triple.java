package rebeccapurple.factory;

public interface Triple<A, B, C, OUT> { OUT create(A first, B second, C third) throws Throwable; }
