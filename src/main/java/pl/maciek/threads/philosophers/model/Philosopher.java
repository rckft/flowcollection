package pl.maciek.threads.philosophers.model;

public class Philosopher extends Thread {

    private final String LEFT = "left";
    private final String RIGHT = "right";

    private int id;
    private Fork leftFork;
    private Fork rightFork;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        Thread.sleep(50);
    }

    private void pickUpForks() throws InterruptedException {
        leftFork.pickUp(LEFT);
        Thread.sleep(10);
        rightFork.pickUp(RIGHT);
    }

    private void eat() throws InterruptedException {
        Thread.sleep(50);
    }

    private void putDownForks() {
        leftFork.putDown();
        rightFork.putDown();
    }

    @Override
    public void run() {
        while (true) try {
            think();
            pickUpForks();
            eat();
            putDownForks();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
