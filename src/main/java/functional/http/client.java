package functional.http;

public class client {
    private static rebeccapurple.http.Client __client = null;

    public static void init(rebeccapurple.http.Client o) {
        synchronized (client.class) {
            if(__client == null) {
                __client = o;
            }
        }
    }

    public static rebeccapurple.http.Client get(){ return __client; }
}
