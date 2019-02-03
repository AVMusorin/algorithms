package dynamic_programming;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        int[] elements = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        int[] state = new int[n];
        for (int i = 0; i < n; i++) {
            state[i] = 1;
            for (int j = 0; j < i; j++) {
                if (elements[i] % elements[j] == 0 && state[j] + 1 > state[i]) {
                    state[i] = state[i] + 1;
                }
            }
        }
        System.out.println(Arrays.stream(state).max().getAsInt());
    }
}
