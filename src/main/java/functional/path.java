package functional;

import rebeccapurple.Identifier;

import java.util.List;

public class path {
    public static class to {
        public static String string(Identifier identifier){
            if(identifier != null){
                return functional.string.join("/", identifier.path);
            }
            return null;
        }
        public static class directory {
            public static String string(Identifier identifier){ return string(identifier.path); }
            public static String string(List<String> path){
                return path != null && path.size()>0 ? functional.string.join("/", path, 0, path.size() - 1) : null;
            }
            public static String string(String path){ return string(functional.collection.to.list(functional.string.split(path, "/"))); }
        }
    }

}
