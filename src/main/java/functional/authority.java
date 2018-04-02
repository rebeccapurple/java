package functional;

import rebeccapurple.Authority;
import rebeccapurple.exception.InvalidParameterException;

public class authority {
    public static Authority from(String authority) throws InvalidParameterException {
        if(functional.string.check.empty(authority)) {
            throw new InvalidParameterException();
        }
        String user = null;
        String[] strings = functional.string.split(authority, "@", 2);
        if(strings.length == 2) {
            user = strings[0];
            strings = functional.string.split(strings[1], ":", 2);
        } else {
            strings = functional.string.split(strings[0], ":", 2);
        }
        if(strings.length == 2) {
            try {
                return new Authority(strings[0], Integer.parseInt(strings[1]), user);
            } catch(NumberFormatException e){
                throw new InvalidParameterException(e);
            }
        } else {
            return new Authority(strings[0], user);
        }
    }
}
