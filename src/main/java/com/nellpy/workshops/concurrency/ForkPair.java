package com.nellpy.workshops.concurrency;

import java.util.concurrent.locks.Lock;


public record ForkPair(Lock firstFork, Lock secondFork) {

}
