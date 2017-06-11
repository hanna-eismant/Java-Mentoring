package com.epam.cdp.javats.carracing;

public class Car implements Runnable {
    private static final long MAX_DISTANCE = 10000;
    private long friction;
    private long distance;
    private String name;

    public Car(String name, long friction) {
        this.name = name;
        this.friction = friction;
    }

    @Override
    public void run() {
        try {
            while (distance < MAX_DISTANCE) {
                Thread.sleep(friction);
                distance += 100;
                System.out.println(name + " " + distance);
            }
        } catch (InterruptedException e) {
            System.err.println("                Disqualify " + name);
        }
    }

    public String getName() {
        return name;
    }
}

