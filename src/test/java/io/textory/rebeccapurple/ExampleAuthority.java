package io.textory.rebeccapurple;

import rebeccapurple.Authority;

public class ExampleAuthority {
    public static void main(String[] args){
        functional.log.console(true);
        functional.json.init();

        functional.debug.run(new Authority(), authority -> functional.log.e(functional.json.from(authority)));
        functional.debug.run(new Authority("textory.io"), authority -> functional.log.e(functional.json.from(authority)));
        functional.debug.run(new Authority("textory.io", 8080), authority -> functional.log.e(functional.json.from(authority)));
        functional.debug.run(new Authority("textory.io", 8080, "sean"), authority -> functional.log.e(functional.json.from(authority)));
        functional.debug.run(new Authority("textory.io", "sean"), authority -> functional.log.e(functional.json.from(authority)));

        functional.debug.run(new Authority("textory.io", 8080, "sean"), authority -> functional.log.e(authority.to()));

        functional.debug.run(new Authority("textory.io", 8080, "sean"), authority -> functional.log.e(authority.host()));
        functional.debug.run(new Authority("textory.io", 8080, "sean"), authority -> functional.log.e(authority.port()));
        functional.debug.run(new Authority("textory.io", 8080, "sean"), authority -> functional.log.e(authority.user()));

        functional.debug.run(new Authority(), authority -> {
            functional.log.e(authority.to());
            authority.host("textory.io");
            functional.log.e(authority.to());
            authority.port(8080);
            functional.log.e(authority.to());
            authority.user("sean");
            functional.log.e(authority.to());
        });

    }
}
