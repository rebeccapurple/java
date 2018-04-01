package io.textory.rebeccapurple;

import rebeccapurple.log.Type;

public class ExampleLog {

    public static void main(String[] args) {
        functional.log.types(Type.NONE);
        functional.log.types(Type.ALL);

        functional.log.type.disable(Type.ERROR);
        functional.log.type.disable(Type.WARNING);
        functional.log.type.disable(Type.CAUTION);
        functional.log.type.disable(Type.NOTICE);
        functional.log.type.disable(Type.INFORMATION);
        functional.log.type.disable(Type.DEBUG);
        functional.log.type.disable(Type.VERBOSE);
        functional.log.type.disable(Type.FLOW);
        functional.log.type.disable(Type.USER);
        functional.log.type.enable(Type.ERROR);
        functional.log.type.enable(Type.WARNING);
        functional.log.type.enable(Type.CAUTION);
        functional.log.type.enable(Type.NOTICE);
        functional.log.type.enable(Type.INFORMATION);
        functional.log.type.enable(Type.DEBUG);
        functional.log.type.enable(Type.VERBOSE);
        functional.log.type.enable(Type.FLOW);
        functional.log.type.enable(Type.USER);
    }
}
