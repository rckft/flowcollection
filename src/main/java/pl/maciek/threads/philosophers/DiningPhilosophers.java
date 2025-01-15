package pl.maciek.threads.philosophers;

import pl.maciek.threads.philosophers.model.Fork;
import pl.maciek.threads.philosophers.model.Philosopher;

import java.util.List;

public class DiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        var philosophers = List.of(
                new Philosopher(1, fork1, fork2),
                new Philosopher(2, fork2, fork3),
                new Philosopher(3, fork3, fork4),
                new Philosopher(4, fork4, fork5),
                new Philosopher(5, fork5, fork1)
        );

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

    }
}
