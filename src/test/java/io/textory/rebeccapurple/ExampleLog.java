package io.textory.rebeccapurple;

import rebeccapurple.log.TYPE;

public class ExampleLog {

    public static void main(String[] args) {
        rebeccapurple.log.types(TYPE.NONE);
        rebeccapurple.log.types(TYPE.ALL);

        rebeccapurple.log.type.disable(TYPE.ERROR);
        rebeccapurple.log.type.disable(TYPE.WARNING);
        rebeccapurple.log.type.disable(TYPE.CAUTION);
        rebeccapurple.log.type.disable(TYPE.NOTICE);
        rebeccapurple.log.type.disable(TYPE.INFORMATION);
        rebeccapurple.log.type.disable(TYPE.DEBUG);
        rebeccapurple.log.type.disable(TYPE.VERBOSE);
        rebeccapurple.log.type.disable(TYPE.FLOW);
        rebeccapurple.log.type.disable(TYPE.USER);
        rebeccapurple.log.type.enable(TYPE.ERROR);
        rebeccapurple.log.type.enable(TYPE.WARNING);
        rebeccapurple.log.type.enable(TYPE.CAUTION);
        rebeccapurple.log.type.enable(TYPE.NOTICE);
        rebeccapurple.log.type.enable(TYPE.INFORMATION);
        rebeccapurple.log.type.enable(TYPE.DEBUG);
        rebeccapurple.log.type.enable(TYPE.VERBOSE);
        rebeccapurple.log.type.enable(TYPE.FLOW);
        rebeccapurple.log.type.enable(TYPE.USER);
    }
}
