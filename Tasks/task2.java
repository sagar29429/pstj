package Tasks;
import java.util.*;

public class task2 {
    static class SensorData {
        double sum = 0;
        int count = 0;

        void add(double temp) {
            sum += temp;
            count++;
        }

        double getAverage() {
            return sum / count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int N = sc.nextInt();
        Map<String, SensorData> map = new LinkedHashMap<>();

        for (int i = 0; i < N; i++) {
            String sensorId = sc.next();
            double temp = sc.nextDouble();

            // 1. Filter temperatures > 50 & 2. Group by SensorID
            if (temp > 50) {
                map.putIfAbsent(sensorId, new SensorData());
                map.get(sensorId).add(temp);
            }
        }

        // 3 & 4. Compute average and sort descending
        map.entrySet().stream()
            .map(e -> Map.entry(e.getKey(), e.getValue().getAverage()))
            .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
            .forEach(e -> System.out.printf("%s %.1f%n", e.getKey(), e.getValue()));
    }
}