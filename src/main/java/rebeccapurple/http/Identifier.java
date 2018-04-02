package rebeccapurple.http;

import rebeccapurple.exception.InvalidParameterException;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @see     <a href="https://stackoverflow.com/questions/2912894/how-to-match-any-character-in-java-regular-expression">How to match “any character” in Java Regular Expression?</a>
 */

public class Identifier {
    private rebeccapurple.Identifier __identifier;
    private LinkedList<String> __path;
    private String __v;

    public String to(){
        if(__identifier!=null){
            return __identifier.to();
        }
        if(__path != null){
            return functional.string.join("/", __path);
        }
        return __v;
    }

    public Identifier(){
        __identifier = null;
        __path = null;
        __v = null;
    }

    public Identifier(String v){
        __identifier = null;
        __path = null;
        __v = v;
    }

    public Identifier(List<String> path){
        __identifier = null;
        __path = (path != null ? new LinkedList<>(path) : null);
        __v = null;
    }

    public Identifier(rebeccapurple.Identifier identifier){
        __identifier = identifier;
        __path = null;
        __v = null;
    }
}
