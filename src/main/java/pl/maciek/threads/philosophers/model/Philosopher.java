package pl.maciek.threads.philosophers.model;

public class Philosopher extends Thread {

    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    private final ForkWithHand higherPriorityFork;
    private final ForkWithHand lowerPriorityFork;

    private record ForkWithHand(Fork fork, String hand) {
        public void pickUp() {
            fork.pickUp(hand);
        }
    }

    public Philosopher(Fork leftFork, Fork rightFork) {
        var leftForkWithHand = new ForkWithHand(leftFork, LEFT);
        var rightForkWithHand = new ForkWithHand(rightFork, RIGHT);
        if (leftFork.getPriority() > rightFork.getPriority()) {
            this.higherPriorityFork = leftForkWithHand;
            this.lowerPriorityFork = rightForkWithHand;
        } else {
            this.higherPriorityFork = rightForkWithHand;
            this.lowerPriorityFork = leftForkWithHand;
        }
    }

    private void think() throws InterruptedException {
        Thread.sleep(50);
    }

    private void pickUpForks() throws InterruptedException {
        higherPriorityFork.pickUp();
        Thread.sleep(10);
        lowerPriorityFork.pickUp();
    }

    private void eat() throws InterruptedException {
        Thread.sleep(50);
    }

    private void putDownForks() {
        higherPriorityFork.fork.putDown();
        lowerPriorityFork.fork.putDown();
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
