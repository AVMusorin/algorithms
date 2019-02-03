package drive_and_rule.quick_sort;

import java.util.Arrays;
import java.util.Scanner;

class QuickSort {
    static void sort(int[] elements, int start, int end) {
        if ((end - start) > 0) {
            int part = partition(elements, start, end);
            sort(elements, start, part - 1);
            sort(elements, part + 1, end);
        }
    }

    private static void swap(int[] elements, int l, int r) {
        int tmp = elements[l];
        elements[l] = elements[r];
        elements[r] = tmp;
    }

    private static int partition(int[] elements, int start, int end) {
        int p = end; // опорный элемент
        int wall = start; // разделяет элементы на те, что меньше опорного и те, что больше опорного
        int wallEqual = start;
        for (int i = start; i < end; i++) {
            if (elements[i] < elements[p]) {
                swap(elements, wall, i);
                wall ++;
            }
            if (elements[i] == elements[p]) {
//                swap(elements, wallEqual, i);
                wallEqual ++;
            }
        }
        swap(elements, wall, p); // ставим опорный элемент за стеной
        return Math.max(wall, wallEqual);
    }
}

class Main {
    /**
     * Количество элементов, меньше переданного
     */
    private static int lessElements(int[] elements, int dot, String strategy) {
        int counter = 0;
        int low = 0, middle;
        int length = elements.length - 1;
        while (low <= length) {
            middle = (low + length) / 2;
            if (elements[middle] < dot) {
                counter = Math.max(middle + 1, counter);
                low = middle + 1;
            } else if (elements[middle] == dot && strategy.equals("start")) {
                low = middle + 1;
                counter = Math.max(middle + 1, counter);
            } else {
                // нужно, чтобы смотреть на последний элемент и не уходитьв бесконечный цикл
                if (low < length) {
                    length = middle;
                } else {
                    break;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        Integer numIntervals = Integer.valueOf(firstLine[0]);
        Integer numDots = Integer.valueOf(firstLine[1]);
        int[] startIntervals = new int[numIntervals];
        int[] stopIntervals = new int[numIntervals];
        for (int i = 0; i < numIntervals; i++) {
            String[] line = scanner.nextLine().split(" ");
            startIntervals[i] = Integer.valueOf(line[0]);
            stopIntervals[i] = Integer.valueOf(line[1]);
        }
        QuickSort.sort(startIntervals, 0, startIntervals.length - 1);
        QuickSort.sort(stopIntervals, 0, stopIntervals.length - 1);
        String[] dots = scanner.nextLine().split(" ");
        int[] results = new int[numDots];
        for (int i = 0; i < numDots; i++) {
            int dot = Integer.valueOf(dots[i]);
            int diff = Math.abs(lessElements(startIntervals, dot, "start")
                    - lessElements(stopIntervals, dot, "stop"));
            results[i] = diff;
        }
        System.out.println(Arrays.toString(results)
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
}
