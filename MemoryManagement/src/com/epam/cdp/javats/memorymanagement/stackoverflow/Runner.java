package com.epam.cdp.javats.memorymanagement.stackoverflow;

public class Runner {

    static {
        new Runner();
    }

    {
        new Runner();
    }

    public static void main(String[] args) {
    }
}
