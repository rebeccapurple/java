package functional;

import java.util.ArrayList;
import java.util.Arrays;

public class list {
    public static <T> ArrayList<T> from(T... objects){
        if(objects != null && objects.length > 0){
            return new ArrayList<>(Arrays.asList(objects));
        }
        return null;
    }
}
