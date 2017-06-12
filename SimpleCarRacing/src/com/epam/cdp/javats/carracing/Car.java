package com.epam.cdp.javats.carracing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static final long MAX_DISTANCE = 10000;
    private long friction;
    private long distance;
    private String name;
    private CyclicBarrier gate;

    public Car(String name, long friction, CyclicBarrier gate) {
        this.name = name;
        this.friction = friction;
        this.gate = gate;
    }

    @Override
    public void run() {

        try {
            gate.await();
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

