package com.epam.cdp.javats.classloading;

import org.apache.log4j.Logger;

public class SomeImplementation {

    private static final  Logger LOGGER = Logger.getLogger(SomeImplementation.class);

    static {
        LOGGER.info("SomeImplementation class is loaded");
    }

    public static void greeting(final String name) {
        LOGGER.info("Hello, " + name + ". How are you? :)");
    }
}
