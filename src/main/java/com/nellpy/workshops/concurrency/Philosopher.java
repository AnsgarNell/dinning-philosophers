package com.nellpy.workshops.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


public class Philosopher implements Runnable {

    protected static final String LEFT = "left";

    protected static final String RIGHT = "right";

    protected final Lock firstFork;

    protected final Lock secondFork;


    public Philosopher(ForkPair forkPair) {
        this.firstFork = forkPair.firstFork();
        this.secondFork = forkPair.secondFork();
    }


    protected Philosopher(Lock firstFork, Lock secondFork) {
        this.firstFork = firstFork;
        this.secondFork = secondFork;
    }


    protected String getFirstForkName() {
        return LEFT;
    }


    protected String getSecondForkName() {
        return RIGHT;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            eat();
            think();
        }
    }


    private void eat() {
        firstFork.lock();
        printMessage(getFirstForkName() + " fork picked up");
        try {
            processOtherFork();
        } finally {
            firstFork.unlock();
        }
    }


    private void processOtherFork() {
        printMessage("trying to pick up " + getSecondForkName() + " fork");
        secondFork.lock();
        try {
            printMessage("eating");
            delay();
        } finally {
            secondFork.unlock();
        }
    }


    private void think() {
        printMessage("thinking");
        delay();
    }


    private void delay() {
        try {
            int timeOut = ThreadLocalRandom.current().nextInt(100);
            TimeUnit.MILLISECONDS.sleep(timeOut);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void printMessage(String message) {
        System.out.println(Thread.currentThread().getName() + " " + message);
    }

}
