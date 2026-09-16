
package wekklytasks.week1;

import java.io.*;
import java.util.*;


interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd() {
        return (int a) -> a % 2 != 0;
    }

    public PerformOperation isPrime() {
        return (int a) -> {
            if (a < 2) {
                return false;
            }

            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {
                    return false;
                }
            }

            return true;
        };
    }

    public PerformOperation isPalindrome() {
        return (int a) -> {
            String str = Integer.toString(a);
            String reversed = new StringBuilder(str).reverse().toString();
            return str.equals(reversed);
        };
    }
}

public class session1_javalambda {
    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            PerformOperation op;
            boolean result;
            String ans;

            if (ch == 1) {
                op = ob.isOdd();
                result = MyMath.checker(op, num);
                ans = result ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                result = MyMath.checker(op, num);
                ans = result ? "PRIME" : "COMPOSITE";
            } else {
                op = ob.isPalindrome();
                result = MyMath.checker(op, num);
                ans = result ? "PALINDROME" : "NOT PALINDROME";
            }

            System.out.println(ans);
        }
    }
}