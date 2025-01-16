package pl.maciek.threads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(50);
                synchronizedSleep();
                synchronized (obj) {
                    obj.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Stan wątku po utworzeniu: " + thread.getState());
        thread.start();
        System.out.println("Stan wątku po uruchomieniu: " + thread.getState());
        Thread.sleep(10);
        System.out.println("Stan wątku po uśpieniu: " + thread.getState());
        synchronizedSleep();
        System.out.println("Stan wątku po zablokowaniu: " + thread.getState());
        Thread.sleep(100);
        System.out.println("Stan wątku po wywołaniu czekaj: " + thread.getState());
        synchronized (obj) {
            obj.notify();
        }
        Thread.sleep(100);
        System.out.println("Stan wątku po zakończeniu: " + thread.getState());

    }

    public static synchronized void synchronizedSleep() throws InterruptedException {
        Thread.sleep(50);
    }
}
