package functional.http;

import rebeccapurple.Listener;
import rebeccapurple.http.Method;
import rebeccapurple.http.Request;
import rebeccapurple.http.Response;
import rebeccapurple.http.Identifier;

public class client {
    public static class task {

        private static rebeccapurple.factory.Pair<Request, Listener<Response>, rebeccapurple.http.Task> __factory = null;
        public static rebeccapurple.http.Task from(Method method, Identifier identifier, Listener<Response> listener) throws Throwable { return __factory.create(new Request(method, identifier), listener); }
    }

    private static rebeccapurple.http.Client __client = null;

    public static void init(rebeccapurple.http.Client o, rebeccapurple.factory.Pair<Request, Listener<Response>, rebeccapurple.http.Task> factory) {
        synchronized (client.class) {
            if(__client == null) {
                __client = o;
            }
            if(task.__factory == null){
                task.__factory = factory;
            }
        }
    }

    public static rebeccapurple.http.Task get(String uri, Listener<Response> listener) throws Throwable { return __client.send(functional.http.client.task.from(Method.GET, functional.http.identifier.from(uri), listener)); }
}
