package rebeccapurple;

public class functional {
    public static <IN, OUT> OUT safe(IN in, Function<IN, OUT> function){
        try {
            return function.call(in);
        } catch(Throwable e){
            rebeccapurple.log.e("function.call(in)", e);
            return null;
        }
    }
    public static <IN, OUT> OUT safe(IN in, Function<IN, OUT> function, OUT o){
        try {
            return function.call(in);
        } catch(Throwable e){
            rebeccapurple.log.e("function.call(in)", e);
            return o;
        }
    }
}
