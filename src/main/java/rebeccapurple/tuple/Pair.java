package rebeccapurple.tuple;

public class Pair<A, B> extends Single<A> {
    public B second;

    public Pair(){
        super();
        this.second = null;
    }

    public Pair(A first, B second){
        super(first);
        this.second = second;
    }

    public boolean equals(A first, B second){
        return functional.object.equal(this.first, first) &&
               functional.object.equal(this.second, second);
    }
}
