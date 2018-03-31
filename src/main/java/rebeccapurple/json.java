package rebeccapurple;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class json {

    private static json __singleton = null;

    public static void init(Listener<GsonBuilder> listener){
        synchronized (json.class) {
            if(__singleton == null) {
                __singleton = new json(listener);
            }
        }
    }

    public static json get(){
        synchronized (json.class){
            if(__singleton == null){
                __singleton = new json();
            }
        }
        return __singleton;
    }

    public static <T> String from(T o){ return __singleton.gson.toJson(o); }
    public static <T> T to(String json, Class<T> c){ return __singleton.gson.fromJson(json, c); }
    public static String value(String v){ return v!=null ? "\"" + v.replace("\\", "\\\\").replace("\'", "\\\'").replace("\"", "\\\"").replace("\r\n", "\\n").replace("\n", "\\n") + "\"" : "null"; }

    private final Gson gson;

    private Gson create(){
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        builder.excludeFieldsWithoutExposeAnnotation();
        return builder.create();
    }

    private Gson create(Listener<GsonBuilder> function){
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        builder.excludeFieldsWithoutExposeAnnotation();
        if(function!=null){
            function.on(builder);
        }
        return builder.create();
    }

    private json(){
        gson = create();
    }

    private json(Listener<GsonBuilder> function){
        gson = create(function);
    }
}
