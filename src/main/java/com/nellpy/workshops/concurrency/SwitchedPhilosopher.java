package com.nellpy.workshops.concurrency;


public class SwitchedPhilosopher extends Philosopher {


    public SwitchedPhilosopher(ForkPair forkPair) {
        super(forkPair.secondFork(), forkPair.firstFork());
    }


    @Override
    protected String getFirstForkName() {
        return RIGHT;
    }


    @Override
    protected String getSecondForkName() {
        return LEFT;
    }
}
