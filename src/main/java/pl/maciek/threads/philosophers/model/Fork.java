package pl.maciek.threads.philosophers.model;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private int id;
    private final ReentrantLock lock = new ReentrantLock();

    public Fork(int id) {
        this.id = id;
    }

    public void pickUp(String side) {
        lock.lock();
        System.out.println("Fork " + id + " " + "is picked up as " + side);
    }

    public void putDown() {
        lock.unlock();
        System.out.println("Fork " + id + " " + " is put down");
    }

}
