package pl.maciek.threads.philosophers.model;

import java.util.stream.Stream;

import static java.util.Comparator.*;

public class Philosopher extends Thread {

    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        Thread.sleep(50);
    }

    private void pickUpForks() throws InterruptedException {
        var forks = Stream.of(leftFork, rightFork).sorted(comparingInt(Fork::getPriority)).toList();
        forks.get(0).pickUp(LEFT);
        Thread.sleep(10);
        forks.get(1).pickUp(RIGHT);
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
