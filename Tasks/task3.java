package Tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class task3 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line1 = br.readLine();

        if (line1 == null || line1.trim().isEmpty())
            return;

        int n = Integer.parseInt(line1.trim());

        int[] arr = Arrays.stream(br.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        long maxProfit = Arrays.stream(arr)
                .asLongStream()
                .boxed()
                .reduce(
                        new KadaneState(Long.MIN_VALUE, 0L),
                        (state, val) -> {
                            long newEndingHere =
                                    Math.max(val, state.maxEndingHere + val);

                            long newMaxSoFar =
                                    Math.max(state.maxSoFar, newEndingHere);

                            return new KadaneState(
                                    newMaxSoFar,
                                    newEndingHere
                            );
                        },
                        (s1, s2) -> s1
                )
                .maxSoFar;

        System.out.println(maxProfit);
    }

    private static class KadaneState {

        final long maxSoFar;
        final long maxEndingHere;

        KadaneState(long maxSoFar, long maxEndingHere) {
            this.maxSoFar = maxSoFar;
            this.maxEndingHere = maxEndingHere;
        }
    }
}