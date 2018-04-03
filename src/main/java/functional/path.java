package functional;

import rebeccapurple.Identifier;

public class path {
    public static class to {
        public static String string(Identifier identifier){
            if(identifier != null){
                return functional.string.join("/", identifier.path);
            }
            return null;
        }
    }

}
