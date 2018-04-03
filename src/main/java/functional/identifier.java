package functional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rebeccapurple.Authority;
import rebeccapurple.exception.InvalidParameterException;
import rebeccapurple.Identifier;

public class identifier {
    public static Identifier from(String identifier) throws InvalidParameterException {
        String[] strings = functional.string.split(identifier, "://", 2);
        if(strings.length != 2 || functional.string.check.empty(strings[0]) || functional.string.check.empty(strings[1])) {
            throw new InvalidParameterException();
        }
        String scheme = strings[0];
        strings = functional.string.split(strings[1], "/", 2);
        Authority authority;
        if(strings.length == 1){
            return new rebeccapurple.Identifier(scheme, functional.authority.from(strings[0]));
        } else {
            authority = functional.authority.from(strings[0]);
        }
        strings = functional.string.split(strings[1], "[?]", 2);
        List<String> path = null;
        if(strings.length == 2){
            path = functional.collection.to.list(functional.string.split(strings[0], "/"));
            strings = functional.string.split(strings[1], "#", 2);
        } else {
            strings = functional.string.split(strings[0], "#", 2);
        }
        String fragment = null;
        if(strings.length == 2) {
            fragment = strings[1];
        }
        Map<String, String> query = null;
        if(!functional.string.check.empty(strings[0])) {
            strings = functional.string.split(strings[0], "&");
            query = new HashMap<>();
            for(String item : strings) {
                String[] pair = functional.string.split(item, "=", 2);
                if(functional.string.check.empty(pair[0])) {
                    functional.log.e("functional.string.check.empty(pair[0])");
                    continue;
                }
                if(pair.length == 2){
                    query.put(pair[0], pair[1]);
                }
            }
        }
        return new Identifier(scheme, authority, path, query, fragment);
    }
}
