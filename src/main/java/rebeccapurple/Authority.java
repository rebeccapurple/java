package rebeccapurple;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authority {
    @SerializedName("user") @Expose private String __user;
    @SerializedName("host") @Expose private String __host;
    @SerializedName("port") @Expose private Integer __port;

    public void host(String v){ __host = v; }
    public void user(String v){ __user = v; }
    public void port(Integer v){ __port = v; }

    public String host(){ return __host; }
    public String user(){ return __user; }
    public Integer port(){ return __port; }

    public String to(){
        String str = null;
        if(!functional.string.check.empty(__host)){
            str = "";
            if(!functional.string.check.empty(__user)) {
                str += (__user + "@");
            }
            str += __host;
            if(__port!=null && __port > 0) {
                str += (":" + __port);
            }
        } else {
            functional.log.e("functional.string.check.empty(__host)");
        }
        return str;
    }

    public Authority(){
        __host = null;
        __port = null;
        __user = null;
    }

    public Authority(String host){
        __host = host;
        __port = null;
        __user = null;
    }

    public Authority(String host, Integer port){
        __host = host;
        __port = port;
        __user = null;
    }

    public Authority(String host, String user){
        __host = host;
        __port = null;
        __user = user;
    }

    public Authority(String host, Integer port, String user){
        __host = host;
        __port = port;
        __user = user;
    }
}
