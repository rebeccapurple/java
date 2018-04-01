package functional;

import rebeccapurple.Function;

public class exception {
    public static <IN, OUT> OUT safe(IN in, Function<IN, OUT> function){
        try {
            return function!=null ? function.call(in) : null;
        } catch(Throwable e) {
            functional.log.e("function.call(in)", e);
        }
        return null;
    }

    public static <IN, OUT> OUT safe(IN in, Function<IN, OUT> function, OUT v){
        try {
            return function!=null ? function.call(in) : v;
        } catch(Throwable e) {
            functional.log.e("function.call(in)", e);
        }
        return v;
    }
}
