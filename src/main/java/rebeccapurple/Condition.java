package rebeccapurple;

public interface Condition<IN> extends Function<IN, Boolean> { Boolean call(IN in); }
