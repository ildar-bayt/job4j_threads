package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from + 1 <= 10) {
            return linearSearch(from, to);
        }
        int mid = (from + to) / 2;

        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, object, from, mid);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, object, mid + 1, to);

        leftSearch.fork();
        rightSearch.fork();

        Integer left = leftSearch.join();
        Integer right = rightSearch.join();

        return getNumber(left, right);
    }

    private Integer getNumber(Integer left, Integer right) {
        if (left == null) {
            return right;
        }
        return left;
    }

    private Integer linearSearch(int from, int to) {
        for (int i = from; i <= to; i++) {
            if ((array[i] != null && array[i].equals(object)) || (array[i] == null && array[i] == object)) {
                return i;
            }
        }
        return null;
    }

    public Integer find() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, object, from, to));
    }
}