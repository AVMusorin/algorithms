package greedy.priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HeapPriorityQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int iterations = Integer.valueOf(scanner.nextLine());
        Comparator<Integer> maxComparator = (x, y) -> (y < x) ? -1 : ((x == y) ? 0 : 1);
        PriorityQueue<Integer> heap = new PriorityQueue<>(maxComparator);
        for (int i = 0; i < iterations; i++) {
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("Insert")) {
                Integer value = Integer.valueOf(input[1]);
                heap.add(value);
            }
            if (input[0].equals("ExtractMax")) {
                System.out.println(heap.poll());
            }
        }
    }
}
