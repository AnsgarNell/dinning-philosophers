package com.nellpy.workshops.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


public class Philosopher implements Runnable {

    protected static final String LEFT = "left";

    protected static final String RIGHT = "right";

    protected final Lock firstLock;

    protected final Lock secondLock;


    public Philosopher(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }


    protected String getFirstLockName() {
        return LEFT;
    }


    protected String getSecondLockName() {
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
        firstLock.lock();
        printMessage(getFirstLockName() + " fork picked up");
        try {
            processOtherFork();
        } finally {
            firstLock.unlock();
        }
    }


    private void processOtherFork() {
        printMessage("tyring to pick up " + getSecondLockName() + " fork");
        secondLock.lock();
        try {
            printMessage("eating");
            delay();
        } finally {
            secondLock.unlock();
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
