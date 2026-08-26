package com.nellpy.workshops.concurrency;


public class SwitchedPhilosopher extends Philosopher {


    public SwitchedPhilosopher(ForkPair forkPair) {
        super(forkPair.secondFork(), forkPair.firstFork());
    }


    @Override
    protected String getFirstLockName() {
        return RIGHT;
    }


    @Override
    protected String getSecondLockName() {
        return LEFT;
    }
}
