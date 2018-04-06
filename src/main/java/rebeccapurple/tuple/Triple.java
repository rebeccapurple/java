package rebeccapurple.tuple;

public class Triple<A, B, C> extends Pair<A, B> {
    public C third;

    public Triple(){
        super();
        this.third = null;
    }

    public Triple(A first, B second, C third){
        super(first, second);
        this.third = third;
    }

    public boolean equals(A first, B second, C third){
        return functional.object.equal(this.first, first) &&
               functional.object.equal(this.second, second) &&
               functional.object.equal(this.third, third); }
}
