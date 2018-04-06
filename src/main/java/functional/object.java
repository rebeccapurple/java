package functional;

public class object {
    public static <T> boolean equal(T x, T y){ return x==null ? y==null : x.equals(y); }
}
