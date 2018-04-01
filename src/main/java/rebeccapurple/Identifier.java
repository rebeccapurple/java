package rebeccapurple;

import java.util.HashMap;
import java.util.LinkedList;

public class Identifier {
    private String __scheme;
    public final Authority authority;
    public final HashMap<String, String> query;
    public final LinkedList<String> path;
    private String __fragment;

    public String scheme(){ return __scheme; }
    public String fragment(){ return __fragment; }

    public void scheme(String v) { __scheme = v; }
    public void fragment(String v){ __fragment = v; }

    public Identifier(){
        __scheme = null;
        authority = new Authority();
        query = new HashMap<>();
        path = new LinkedList<>();
        __fragment = null;
    }

}
