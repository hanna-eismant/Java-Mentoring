package com.epam.cdp.javats.classloading;

public class SomeImplementation implements Greeting {
    public void greeting(final String name) {
        System.out.println("One: hi, " + name);
    }
}
