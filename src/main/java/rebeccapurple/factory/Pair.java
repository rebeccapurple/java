package rebeccapurple.factory;

public interface Pair<A, B, OUT> { OUT create(A first, B second) throws Throwable; }
