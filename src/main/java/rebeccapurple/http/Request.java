package rebeccapurple.http;

import java.util.Map;

public class Request extends Message {
    private Identifier __identifier;
    private Method __method;


    public Method method(){ return __method; }
    public String uri(){ return __identifier.to(); }

    public Request(){
        super();
    }

    public Request(Method method, Identifier identifier){
        super();
        __method = method;
        __identifier = identifier;
    }

    public Request(Method method, Identifier identifier, byte[] body){
        super(body);
        __method = method;
        __identifier = identifier;
    }

    public Request(Method method, Identifier identifier, Map<String, String> headers){
        super(headers);
        __method = method;
        __identifier = identifier;
    }

    public Request(Method method, Identifier identifier, Map<String, String> headers, byte[] body){
        super(headers, body);
        __method = method;
        __identifier = identifier;
    }
}
