package drive_and_rule.binary_search;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BinarySearch {
    private static Integer binarySearch(List<Integer> elements, Integer element, Integer length) {
        Integer low = 0, middle;
        while (low < length) {
            middle = (low + length) / 2;
            if (elements.get(middle).equals(element)) {
                return middle + 1;
            } else {
                if (element > elements.get(middle)) {
                    low = middle + 1;
                } else {
                    length = middle;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstLine = Arrays.stream(scanner.nextLine()
                .split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> secondLine = Arrays.stream(scanner.nextLine()
                .split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> elements = firstLine.subList(1, firstLine.get(0) + 1);
        List<Integer> searchElements = secondLine.subList(1, secondLine.get(0) + 1);
        System.out.println(String.join(" ", searchElements.stream()
                .map(e -> String.valueOf(binarySearch(elements, e, firstLine.get(0))))
                .collect(Collectors.toList())));
    }
}
