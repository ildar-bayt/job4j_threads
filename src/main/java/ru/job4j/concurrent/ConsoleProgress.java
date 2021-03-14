package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] symbols = {"\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\rLoading ... " + symbols[index]);
                index = (index + 1) % symbols.length;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.print("\rLoaded");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
