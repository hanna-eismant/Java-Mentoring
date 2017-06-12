package com.epam.cdp.javats.carracing;

import static java.lang.Thread.State.TERMINATED;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Runner {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier gate = new CyclicBarrier(4);

        final Car mitsubishi = new Car("Mitsubishi", 100, gate);
        final Car toyota = new Car("Toyota", 100, gate);
        final Car bmv = new Car("BMV", 100, gate);

        final Thread bmvThread = new Thread(bmv);
        bmvThread.setName(bmv.getName());
        final Thread toyotaThread = new Thread(toyota);
        toyotaThread.setName(toyota.getName());
        final Thread mitsubishiThread = new Thread(mitsubishi);
        mitsubishiThread.setName(mitsubishi.getName());

        bmvThread.start();
        toyotaThread.start();
        mitsubishiThread.start();

        gate.await();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toyotaThread.interrupt();
        final List<Thread> racing = Arrays.asList(bmvThread, mitsubishiThread);

        check: while (true) {
            for (Thread thread : racing) {
                if (TERMINATED == thread.getState()) {
                    System.err.println("                Winner is " + thread.getName());
                    break check;
                }
            }
        }
    }
}
