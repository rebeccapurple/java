package rebeccapurple.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Response extends Message {
    @SerializedName("status") @Expose private Integer __status;
    @SerializedName("message") @Expose private String __message;

    public Integer status(){ return __status; }
    public String message(){ return __message; }

    public void status(Integer v){ __status = v; }
    public void message(String v){ __message = v; }

    public Response(){
        super();
        __status = null;
        __message = null;
    }

    public Response(Integer status, byte[] body){
        super(body);
        __status = status;
        __message = functional.http.response.to.message(status);
    }

    public Response(Integer status, Map<String, String> headers){
        super(headers);
        __status = status;
        __message = functional.http.response.to.message(status);
    }

    public Response(Integer status, Map<String, String> headers, byte[] body){
        super(headers, body);
        __status = status;
        __message = functional.http.response.to.message(status);
    }

    public Response(Integer status, String message, byte[] body){
        super(body);
        __status = status;
        __message = message;
    }

    public Response(Integer status, String message, Map<String, String> headers){
        super(headers);
        __status = status;
        __message = message;
    }

    public Response(Integer status, String message, Map<String, String> headers, byte[] body){
        super(headers, body);
        __status = status;
        __message = message;
    }
}
