package com.nellpy.workshops.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    private static final int NUMBER_OF_PHILOSOPHERS = 5;


    static void main() {
        List<Lock> forks = new ArrayList<>(NUMBER_OF_PHILOSOPHERS);
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks.add(new ReentrantLock());
        }
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            ForkPair forkPair = new ForkPair(forks.get(i), forks.get((i + 1) % NUMBER_OF_PHILOSOPHERS));
            Philosopher philosopher = getPhilosopher(i, forkPair);
            createThread(philosopher, i);
        }
    }


    private static Philosopher getPhilosopher(int i, ForkPair forkPair) {
        if (i == 0) {
            return new SwitchedPhilosopher(forkPair);
        } else {
            return new Philosopher(forkPair);
        }
    }


    private static void createThread(Philosopher philosopher, int i) {
        Thread thread = Thread.ofPlatform()
                .unstarted(philosopher);
        thread.setName("Philosopher " + (i + 1));
        thread.start();
    }

}
