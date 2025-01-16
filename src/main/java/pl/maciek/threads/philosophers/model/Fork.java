package pl.maciek.threads.philosophers.model;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private final int id;
    private final int priority;

    private final ReentrantLock lock = new ReentrantLock();

    public Fork(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public void pickUp(String side) {
        lock.lock();
        System.out.println("Fork " + id + " " + "is picked up as " + side);
    }

    public void putDown() {
        lock.unlock();
        System.out.println("Fork " + id + " " + " is put down");
    }

    public int getPriority() {
        return priority;
    }
}
