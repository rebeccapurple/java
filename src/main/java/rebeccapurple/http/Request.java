package rebeccapurple.http;

public class Request extends Message {
    private rebeccapurple.http.Identifier __identifier;
    private Method __method;


    public Method method(){ return __method; }
    public String uri(){ return __identifier.uri(); }

}
