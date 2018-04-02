package rebeccapurple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Identifier {
    @SerializedName("scheme") @Expose private String __scheme;
    @SerializedName("authority") @Expose public final Authority authority;
    @SerializedName("query") @Expose public final HashMap<String, String> query;
    @SerializedName("path") @Expose public final LinkedList<String> path;
    @SerializedName("fragment") @Expose private String __fragment;

    public String scheme(){ return __scheme; }
    public String fragment(){ return __fragment; }

    public void scheme(String v) { __scheme = v; }
    public void fragment(String v){ __fragment = v; }

    public String to(){
        StringBuilder builder = new StringBuilder();
        String authority = this.authority.to();
        if(!functional.string.check.empty(__scheme) && !functional.string.check.empty(authority)) {
            builder.append(__scheme);
            builder.append("://");
            builder.append(authority);
            if(path.size() > 0){
                for(String segment : path){
                    builder.append("/");
                    builder.append(functional.string.get(segment));
                }
                if(query.size() > 0) {
                    builder.append("?");
                    for(Map.Entry<String, String> entry : query.entrySet()) {
                        if(!functional.string.check.empty(entry.getKey())) {
                            builder.append(entry.getKey());
                            builder.append("=");
                            builder.append(functional.string.get(entry.getValue()));
                            builder.append("&");
                        } else {
                            functional.log.e("functional.string.check.empty(entry.getKey()) == true");
                        }
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    if(!functional.string.check.empty(__fragment)) {
                        builder.append("#");
                        builder.append(__fragment);
                    }
                }
            }
        } else {
            functional.log.e("functional.string.check.empty(__scheme) || functional.string.check.empty(authority)");
        }
        return builder.toString();
    }

    public Identifier(){
        this.__scheme = null;
        this.authority = new Authority();
        this.query = new HashMap<>();
        this.path = new LinkedList<>();
        this.__fragment = null;
    }

    public Identifier(String scheme, String host){
        this.__scheme = scheme;
        this.authority = new Authority(host);
        this.query = new HashMap<>();
        this.path = new LinkedList<>();
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port){
        this.__scheme = scheme;
        this.authority = new Authority(host, port);
        this.query = new HashMap<>();
        this.path = new LinkedList<>();
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, String user){
        this.__scheme = scheme;
        this.authority = new Authority(host, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>();
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port, String user){
        this.__scheme = scheme;
        this.authority = new Authority(host, port, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>();
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, List<String> path){
        this.__scheme = scheme;
        this.authority = new Authority(host);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port, List<String> path){
        this.__scheme = scheme;
        this.authority = new Authority(host, port);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, String user, List<String> path){
        this.__scheme = scheme;
        this.authority = new Authority(host, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port, String user, List<String> path){
        this.__scheme = scheme;
        this.authority = new Authority(host, port, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, List<String> path, Map<String, String> query){
        this.__scheme = scheme;
        this.authority = new Authority(host);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port, List<String> path, Map<String, String> query){
        this.__scheme = scheme;
        this.authority = new Authority(host, port);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, String user, List<String> path, Map<String, String> query){
        this.__scheme = scheme;
        this.authority = new Authority(host, user);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, Integer port, String user, List<String> path, Map<String, String> query){
        this.__scheme = scheme;
        this.authority = new Authority(host, port, user);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = null;
    }

    public Identifier(String scheme, String host, List<String> path, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, Integer port, List<String> path, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, port);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, String user, List<String> path, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, Integer port, String user, List<String> path, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, port, user);
        this.query = new HashMap<>();
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, List<String> path, Map<String, String> query, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, Integer port, List<String> path, Map<String, String> query, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, port);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, String user, List<String> path, Map<String, String> query, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, user);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }

    public Identifier(String scheme, String host, Integer port, String user, List<String> path, Map<String, String> query, String fragment){
        this.__scheme = scheme;
        this.authority = new Authority(host, port, user);
        this.query = new HashMap<>(query);
        this.path = new LinkedList<>(path);
        this.__fragment = fragment;
    }
}
