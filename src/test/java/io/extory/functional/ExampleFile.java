package io.extory.functional;

import rebeccapurple.Listener;

public class ExampleFile {
    public static void run(String in, Listener<String> operator){
        operator.on(in);
    }

    public static void check(String prefix, String suffix) {
        functional.log.e(prefix + ":" + suffix);
    }
    public static void main(String[] args){
        functional.log.console(true);
        functional.json.init();

        run(".hello", (in)->check(functional.file.prefix(in), functional.file.suffix(in)));
        run("world.jpg", (in)->check(functional.file.prefix(in), functional.file.suffix(in)));
        run("hello.world.jpg", (in)->check(functional.file.prefix(in), functional.file.suffix(in)));
        run(".hello.world.jpg", (in)->check(functional.file.prefix(in), functional.file.suffix(in)));
    }
}
