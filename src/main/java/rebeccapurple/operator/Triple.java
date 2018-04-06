package rebeccapurple.operator;

public interface Triple<A, B, C, OUT> { void call(A first, B second, C third, rebeccapurple.Operator.On<OUT> callback); }
