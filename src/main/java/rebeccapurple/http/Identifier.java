package rebeccapurple.http;

import java.util.LinkedList;

public class Identifier {
    private rebeccapurple.Identifier __identifier;
    private LinkedList<String> __path;
    private String __v;

    public String uri(){
        if(__identifier!=null){
            return __identifier.to();
        }
        if(__path != null){
            return functional.string.join("/", __path);
        }
        return __v;
    }

    public Identifier(){
        __identifier = null;
        __path = null;
        __v = null;
    }
}
