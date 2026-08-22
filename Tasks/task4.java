import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task4 {
    public static void main(String[] args) throws IOException {
        // Fast I/O for handling input sizes up to 10^6 characters
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String T = reader.readLine();
        String P = reader.readLine();

        if (T == null || P == null || P.length() > T.length()) {
            return;
        }

        // Search for occurrences using KMP
        List<Integer> matches = kmpSearch(T, P);

        // Print results
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append(matches.get(i));
            if (i < matches.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    private static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        // Step 1: Precompute the Longest Prefix Suffix (LPS) array
        int[] lps = computeLPSArray(pattern);

        int i = 0; // index for text T
        int j = 0; // index for pattern P

        // Step 2: Match pattern against text
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // Found pattern at index i - j
                result.add(i - j);
                j = lps[j - 1]; // Reset j using LPS table to find overlapping matches
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                // Mismatch after j matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;

        lps[0] = 0; // lps[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}