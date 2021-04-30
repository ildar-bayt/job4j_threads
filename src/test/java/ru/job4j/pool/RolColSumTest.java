package ru.job4j.pool;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {

    private final int[][] matrix = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}
    };

    @Test
    public void whenSequenceRun() {
        RolColSum.Sums[] array = RolColSum.sum(matrix);

        Object[] result1 = Arrays.stream(
                array).map(RolColSum.Sums::getColSum).toArray();
        Object[] result2 = Arrays.stream(
                array).map(RolColSum.Sums::getRowSum).toArray();

        assertThat(result1, is(new int[]{4, 8, 12, 16}));
        assertThat(result2, is(new int[]{10, 10, 10, 10}));
    }

    @Test
    public void whenAsyncRun() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] array = RolColSum.asyncSum(matrix);

        Object[] result1 = Arrays.stream(
                array).map(RolColSum.Sums::getColSum).toArray();
        Object[] result2 = Arrays.stream(
                array).map(RolColSum.Sums::getRowSum).toArray();

        assertThat(result1, is(new int[]{4, 8, 12, 16}));
        assertThat(result2, is(new int[]{10, 10, 10, 10}));
    }
}