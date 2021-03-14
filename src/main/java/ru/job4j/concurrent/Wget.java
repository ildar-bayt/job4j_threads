package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        for (int percent = 0; percent <= 100 || !Thread.currentThread().isInterrupted(); percent++) {
                            System.out.print("\rLoading : " + percent + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
        thread.join();
    }
}
