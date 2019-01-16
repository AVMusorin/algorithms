package drive_and_rule.sort_merge;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
import java.util.stream.IntStream;

public class SortMerge {
    long counter = 0;

    private int[] merge(int[] first, int[] second) {
        int[] mergedList = new int[first.length + second.length];
        int firstLen = first.length;
        int firstIndex = 0;
        int secondIndex = 0;
        int mergedIndex = 0;
        while (secondIndex < second.length || firstIndex < first.length) {
            if (first.length == firstIndex) {
                while (secondIndex < second.length) {
                    mergedList[mergedIndex] = second[secondIndex];
                    mergedIndex ++;
                    secondIndex ++;
                }
                break;
            }
            if (second.length == secondIndex) {
                while (firstIndex < first.length) {
                    mergedList[mergedIndex] = first[firstIndex];
                    mergedIndex ++;
                    firstIndex ++;
                }
                break;
            }
            if (first[firstIndex] <= second[secondIndex]) {
                mergedList[mergedIndex] = first[firstIndex];
                firstIndex ++;
                mergedIndex ++;
            } else {
                mergedList[mergedIndex] = second[secondIndex];
                this.counter += firstLen - firstIndex;
                secondIndex ++;
                mergedIndex ++;
            }
        }
        return mergedList;
    }

    int[] sortMerge(int[] elements, int length) {
        if (length <= 1) {
            return elements;
        }
        ArrayQueue<int[]> queue = new ArrayQueue<>(length);
        for (Integer e : elements) {
            queue.add(new int[]{e});
        }
        while (length != 1) {
            if (length % 2 == 0) {
                for (int i : IntStream.range(0, length / 2).toArray()) {
                    queue.add(merge(queue.remove(0), queue.remove(0)));
                }
                length = length / 2;
            } else {
                for (int i : IntStream.range(0, length / 2).toArray()) {
                    queue.add(merge(queue.remove(0), queue.remove(0)));
                }
                // переносим последний элемент в конец очереди
                queue.add(queue.remove(0));
                length = length / 2 + 1;
            }
        }
        return queue.get(0);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.valueOf(scanner.nextLine());
        int[] elements = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        SortMerge sorter = new SortMerge();
        int[] sorted = sorter.sortMerge(elements, length);
        System.out.println(sorter.counter);
    }
}
