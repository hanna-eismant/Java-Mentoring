package com.epam.cdp.javats.deadlock;

public class Runner {

    public static void main(String[] args) {
        final String resourceOne = "Resource One";
        final String resourceTwo = "Resource Two";

        Thread threadOne = new Thread(() -> {
            synchronized (resourceOne) {
                System.out.println("Thread 1: locked resource 1");
                try { Thread.sleep(5_000);} catch (Exception e) {}
                synchronized (resourceTwo) {
                    System.out.println("Thread 1: locked resource 2");
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            synchronized (resourceTwo) {
                System.out.println("Thread 2: locked resource 2");
                try { Thread.sleep(5_000);} catch (Exception e) {}
                synchronized (resourceOne) {
                    System.out.println("Thread 2: locked resource 1");
                }
            }
        });


        threadOne.start();
        threadTwo.start();
    }
}
