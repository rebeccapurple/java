package rebeccapurple;

public class Authority {
    private String __user;
    private String __host;
    private Integer __port;

    public void host(String v){ __host = v; }
    public void user(String v){ __user = v; }
    public void port(Integer v){ __port = v; }

    public String host(){ return __host; }
    public String user(){ return __user; }
    public Integer port(){ return __port; }

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
