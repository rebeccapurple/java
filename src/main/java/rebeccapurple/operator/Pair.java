package rebeccapurple.operator;

public interface Pair<A, B, OUT> { void call(A first, B second, rebeccapurple.Operator.On<OUT> callback); }
