package rebeccapurple;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class collection {
    public static class check {
        public static <T> boolean empty(T[] array){ return array == null || array.length == 0; }
    }

    public static <T> void remove(Collection<T> destination, Collection<T> items){
        if(destination != null && items != null){
            for(T item : items){
                destination.remove(item);
            }
        }
    }

    public static <T> void remove(Map<T, ?> destination, Collection<T> items){
        if(destination != null && items != null){
            for(T item : items){
                destination.remove(item);
            }
        }
    }

    public static <K, V> V get(Map<K, V> map, K k, Function<K, V> function){ return map.computeIfAbsent(k, key -> rebeccapurple.functional.safe(key, function)); }

    public static <T> void each(Collection<T> collection, Listener<T> function){
        for(T o : collection){
            function.on(o);
        }
    }

    public static <T> T pop(Collection<T> collection){
        if(collection != null) {
            Iterator<T> it = collection.iterator();
            if(it.hasNext()){
                return it.next();
            }
        }
        return null;
    }
}
