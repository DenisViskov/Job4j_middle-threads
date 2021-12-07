package completablefuture;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RowColSum {

    public static Sums[] sum(final int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            final int currIndex = i;
            Sums sums = new Sums(
                Arrays.stream(matrix[i]).sum(),
                Arrays.stream(matrix).mapToInt(array -> array[currIndex]).sum()
            );
            result[currIndex] = sums;
        }
        return result;
    }

    public static Sums[] asyncSum(final int[][] matrix) {
        return null;
    }
}
