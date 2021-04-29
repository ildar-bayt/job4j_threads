package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int bound;

    private final Object monitor = this;

    @GuardedBy("monitor")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == bound) {
            System.out.println(Thread.currentThread().getName() + " wait");
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        T result;
        while (queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " wait");
            wait();
        }
        result = queue.poll();
        notifyAll();
        return result;
    }

    public synchronized int size() {
        return queue.size();
    }
}