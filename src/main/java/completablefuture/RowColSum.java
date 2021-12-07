package completablefuture;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RowColSum {

    public static Sums[] sum(final int[][] matrix) {
        final Sums[] result = new Sums[matrix.length];
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

    @SneakyThrows
    public static Sums[] asyncSum(final int[][] matrix) {
        final Sums[] result = new Sums[matrix.length];
        final Map<Integer, CompletableFuture<Sums>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, calculate(matrix, i));
        }
        for (int key : map.keySet()) {
            result[key] = map.get(key).get();
        }
        return result;
    }

    private static CompletableFuture<Sums> calculate(final int[][] matrix, final int index) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums(
                Arrays.stream(matrix[index]).sum(),
                Arrays.stream(matrix).mapToInt(array -> array[index]).sum()
            );
            return sums;
        });
    }
}
