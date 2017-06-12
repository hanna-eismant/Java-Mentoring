package com.epam.cdp.javats.carracing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    public static final CyclicBarrier GATE = new CyclicBarrier(4);
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
            GATE.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        
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

