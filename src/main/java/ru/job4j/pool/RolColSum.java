package ru.job4j.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            for (int j = 0; j < matrix[i].length; j++) {
                sums[i].rowSum += matrix[i][j];
                sums[i].colSum += matrix[j][i];
            }
        }

        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];

        List<CompletableFuture<Integer>> columns = new ArrayList<>();
        List<CompletableFuture<Integer>> rows = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            rows.add(getSumRows(matrix, i));
            columns.add(getSumColumn(matrix, i));
        }

        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            sums[i].rowSum = rows.get(i).get();
            sums[i].colSum = columns.get(i).get();
        }

        return sums;
    }

    private static CompletableFuture<Integer> getSumRows(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[index][i];
            }
            return sum;
        });
    }

    private static CompletableFuture<Integer> getSumColumn(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][index];
            }
            return sum;
        });
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }
}
