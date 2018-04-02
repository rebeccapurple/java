package io.textory.rebeccapurple;

import rebeccapurple.Identifier;
import rebeccapurple.exception.InvalidParameterException;

import java.util.HashMap;
import java.util.Map;

public class ExampleIdentifier {

    private static void debug(Identifier identifier){
        functional.log.e(identifier);
        functional.log.e(identifier.to());
    }

    private static void method(Identifier identifier) {
        functional.log.e(identifier);
        identifier.scheme("https");
        identifier.fragment("page");
        identifier.authority.host("textory.io");
        identifier.authority.port(8080);
        identifier.authority.user("sean");
        identifier.path.add("version");
        identifier.query.clear();
        identifier.query.put("hello", "world");
        functional.log.e(identifier.authority);
        functional.log.e(identifier.path);
        functional.log.e(identifier.query);
        functional.log.e(identifier.scheme());
        functional.log.e(identifier.fragment());
        functional.log.e(identifier);
        functional.log.e(identifier.to());
    }

    public static void main(String[] args){
        functional.log.console(true);
        functional.json.init();

        Map<String, String> query = new HashMap<>();
        query.put("key1", "value1");
        query.put("key2", "value2");
        query.put("key3", "value3");
        query.put("key4", "value4");
        query.put("key5", "value5");
        query.put("key6", "value6");
        query.put("key7", "value7");

        functional.debug.run(new Identifier(), identifier -> functional.log.e(functional.json.from(identifier)));

        functional.debug.run(new Identifier("http", "textory.io"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", "sean"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean"), ExampleIdentifier::debug);

        functional.debug.run(new Identifier("http", "textory.io", functional.collection.to.list("home", "textory", "index.html")), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, functional.collection.to.list("home", "textory", "index.html")), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", "sean", functional.collection.to.list("home", "textory", "index.html")), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean", functional.collection.to.list("home", "textory", "index.html")), ExampleIdentifier::debug);

        functional.debug.run(new Identifier("http", "textory.io", functional.collection.to.list("home", "textory", "index.html"), "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, functional.collection.to.list("home", "textory", "index.html"), "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", "sean", functional.collection.to.list("home", "textory", "index.html"), "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean", functional.collection.to.list("home", "textory", "index.html"), "fragment"), ExampleIdentifier::debug);

        functional.debug.run(new Identifier("http", "textory.io", functional.collection.to.list("home", "textory", "index.html"), query), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, functional.collection.to.list("home", "textory", "index.html"), query), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", "sean", functional.collection.to.list("home", "textory", "index.html"), query), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean", functional.collection.to.list("home", "textory", "index.html"), query), ExampleIdentifier::debug);

        functional.debug.run(new Identifier("http", "textory.io", functional.collection.to.list("home", "textory", "index.html"), query, "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, functional.collection.to.list("home", "textory", "index.html"), query, "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", "sean", functional.collection.to.list("home", "textory", "index.html"), query, "fragment"), ExampleIdentifier::debug);
        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean", functional.collection.to.list("home", "textory", "index.html"), query, "fragment"), ExampleIdentifier::debug);

        functional.debug.run(new Identifier("http", "textory.io", 8080, "sean", functional.collection.to.list("home", "textory", "index.html"), query, "fragment"), ExampleIdentifier::method);

        try {
            functional.debug.run(functional.identifier.from("https://www.google.co.kr/webhp?ie=UTF-8&rct=j&gws_rd=cr&dcr=0&ei=4n_BWqKtBMG_0gSIuYiQBQ"), ExampleIdentifier::debug);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }
}
