package rebeccapurple;

public interface Factory<IN, OUT> { OUT create(IN in) throws Throwable; }
