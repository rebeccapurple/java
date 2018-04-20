package rebeccapurple;

import java.util.LinkedList;

public abstract class Pool<T> {
    private final LinkedList<T> __pool = new LinkedList<>();
    private int __size;

    public abstract T create();

    public T get(){
        if(__pool.size() > 0){
            return __pool.getFirst();
        }
        return create();
    }

    public void release(T o){
        if(o != null){
            if(__pool.size() <= __size){
                __pool.add(o);
            } else {
                functional.log.v("__pool.size() > __size");
            }
        }
    }

    public Pool(){
        __size = 16;
        for(int i = 0; i < __size; i++){
            __pool.add(create());
        }
    }

    public Pool(int size){
        __size = size;
        if(__size < 0){
            /** implement: configurable throw exception */
            functional.log.v("__size < 0");
            __size = 16;
        }
        for(int i = 0; i < __size; i++){
            __pool.add(create());
        }
    }
}
