package functional.http;

import rebeccapurple.exception.InvalidParameterException;
import rebeccapurple.http.Identifier;

import java.util.regex.Pattern;

public class identifier {
    public static Identifier from(String identifier) throws InvalidParameterException {
        if(functional.string.equal(identifier, "*")){
            return new Identifier("*");
        } else if(functional.string.validate(Pattern.compile("^/.+"), identifier)){
            return new Identifier(functional.collection.to.list(functional.string.split(identifier, "/")));
        } else {
            return new Identifier(functional.identifier.from(identifier));
        }
    }
}
