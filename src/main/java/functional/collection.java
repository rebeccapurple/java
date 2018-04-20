package functional;

import java.util.*;

import rebeccapurple.Condition;
import rebeccapurple.Function;
import rebeccapurple.Listener;

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

    public static <K, V> V get(Map<K, V> map, K k, Function<K, V> function){ return map.computeIfAbsent(k, key -> functional.exception.safe(key, function)); }

    public static <K, V> V get(Map<K, V> map, K k){ return map != null ? map.get(k) : null; }

    public static <K, V> V get(List<V> list, K k, rebeccapurple.condition.Pair<V, K> condition){
        if(list != null && condition != null){
            for(V v : list){
                if(condition.check(v, k)){
                    return v;
                }
            }
        }
        return null;
    }

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

    public static <T> Collection<T> set(Collection<T> destination, Collection<? extends T> source){
        if(destination != null){
            destination.clear();
            if(source != null){
                destination.addAll(source);
            }
        } else {
            functional.log.e("destination == null");
        }
        return destination;
    }

    public static <K, V, MAP extends Map<K, V>> void put(MAP destination, Map<K, V> source){ destination.putAll(source); }
    public static <K, V, MAP extends Map<K, V>> void put(MAP destination, K k, V v){ destination.put(k, v); }

    public static <V, LIST extends List<V>> V put(LIST list, V v, rebeccapurple.condition.Pair<V, V> condition){
        for(V value : list) {
            if(condition.check(value, v)){
                list.remove(value);
                list.add(v);
                return value;
            }
        }
        list.add(v);
        return null;
    }

    public static <K, V, MAP extends Map<K, V>> MAP create(MAP map, Map<K, V> source){
        map.putAll(source);
        return map;
    }

    public static class to {
        @SafeVarargs
        public static <T> LinkedList<T> list(T... elements) {
            if (elements != null) {
                LinkedList<T> o = new LinkedList<>();
                Collections.addAll(o, elements);
                return o;
            }
            return null;
        }

        @SafeVarargs
        public static <T, COLLECTION extends Collection<T>> COLLECTION list(COLLECTION collection, T... elements) {
            collection.addAll(Arrays.asList(elements));
            return collection;
        }
    }
    public static <T, COLLECTION extends Collection<? super T>> COLLECTION add(COLLECTION collection, T o){ return add(collection, o, true); }
    public static <T, COLLECTION extends Collection<? super T>> COLLECTION add(COLLECTION collection, T o, boolean nullable){
        if(collection != null) {
            if(nullable){
                collection.add(o);
            } else if(o != null){
                collection.add(o);
            }
        }
        return collection;
    }

    public static <K, V, MAP extends Map<K, V>> V del(MAP map, K k){ return map != null ? map.remove(k) : null; }
    public static <K, V, LIST extends List<V>> V del(LIST list, K k, rebeccapurple.condition.Pair<V, K> condition){
        if(list != null){
            for(V v : list) {
                if(condition.check(v, k)) {
                    list.remove(v);
                    return v;
                }
            }
        }
        return null;
    }
}
