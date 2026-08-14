package Tasks;
import java.util.*;

public class task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] salaries = new int[n];

        for (int i = 0; i < n; i++) {
            salaries[i] = sc.nextInt();
        }

        Arrays.stream(salaries)
              .map(salary -> salary + salary / 10)
              .forEach(salary -> System.out.print(salary + " "));
    }
}