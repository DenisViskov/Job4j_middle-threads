package completablefuture;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RowColSumTest {

    @Test
    public void sum() {
        final int[][] matrix = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        final Sums[] expectedResult = new Sums[]{
            new Sums(6, 12),
            new Sums(15, 15),
            new Sums(24, 18)
        };
        final Sums[] calculatedResult = RowColSum.sum(matrix);
        assertArrayEquals(expectedResult, calculatedResult);
    }

    @Test
    public void asyncSum() {
        final int[][] matrix = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        final Sums[] expectedResult = new Sums[]{
            new Sums(6, 12),
            new Sums(15, 15),
            new Sums(24, 18)
        };
        final Sums[] calculatedResult = RowColSum.asyncSum(matrix);
        assertArrayEquals(expectedResult, calculatedResult);
    }
}