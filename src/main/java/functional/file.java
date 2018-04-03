package functional;

import java.util.List;

public class file {
    public static String name(List<String> path){
        return path != null && path.size() > 0 ? path.get(path.size() - 1) : null;
    }
    public static String prefix(String in){
        if(!functional.string.check.empty(in)){
            int index = in.lastIndexOf(".");
            return index >= 0 ? in.substring(0, index) : in;
        }
        return null;
    }
    public static String suffix(String in){
        if(!functional.string.check.empty(in)){
            int index = in.lastIndexOf(".");
            return index >= 0 ? in.substring(index + 1) : in;
        }
        return null;
    }

    public static String prefix(List<String> path){ return prefix(name(path)); }
    public static String suffix(List<String> path){ return suffix(name(path)); }
}
