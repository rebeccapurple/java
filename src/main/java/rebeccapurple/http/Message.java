package rebeccapurple.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public abstract class Message {
    @SerializedName("headers") @Expose protected HashMap<String, String> __headers;
    protected byte[] __body;

    public void body(byte[] v){ __body = v; }
    public void put(Map<String, String> headers){ functional.collection.put(__headers, headers); }

    public Map<String, String> headers(){ return __headers; }
    public byte[] body(){ return __body; }

    public Message(){
        __headers = new HashMap<>();
        __body = null;
    }

    public Message(byte[] body){
        __headers = new HashMap<>();
        __body = body;
    }

    public Message(Map<String, String> headers){
        __headers = functional.collection.create(new HashMap<>(), headers);
        __body = null;
    }

    public Message(Map<String, String> headers, byte[] body){
        __headers = functional.collection.create(new HashMap<>(), headers);
        __body = body;
    }
}
