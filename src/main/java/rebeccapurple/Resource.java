package rebeccapurple;

public class Resource {
    private Identifier __local;
    private Identifier __remote;

    public Identifier local(){ return __local; }
    public Identifier remote(){ return __remote; }

    public void local(Identifier v){ __local = v; }
    public void remote(Identifier v){ __remote = v; }

    public Resource(){
        __local = null;
        __remote = null;
    }

    public Resource(Identifier local, Identifier remote){
        __local = local;
        __remote = remote;
    }
}
