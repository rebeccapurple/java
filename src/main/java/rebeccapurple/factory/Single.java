package rebeccapurple.factory;

public interface Single<A, OUT> { OUT create(A first) throws Throwable; }
