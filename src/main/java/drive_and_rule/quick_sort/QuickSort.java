package drive_and_rule.quick_sort;

import java.util.Arrays;

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
        for (int i = start; i < end; i++) {
            if (elements[i] < elements[p]) {
                swap(elements, wall, i);
                wall ++;
            }
        }
        swap(elements, wall, p); // ставим опорный элемент за стеной
        return wall;
    }
}

class Main {
    /**
     * Количество элементов, меньше переданного
     */
    private static int lessElements(int[] elements, int dot) {
        int counter = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] <= dot) {
                counter ++;
            } else {
                break;
            }
        }
        return counter;
    }

//    /**
//     * Количество элементов, которые больше переданного
//     */
//    private static int moreElements(int[] elements, int dot) {
//        int counter = 0;
//        for (int i = 0; i < elements.length; i++) {
//            if (dot >= elements[i]) {
//                counter ++;
//            } else {
//                break;
//            }
//        }
//        return counter;
//    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String[] firstLine = scanner.nextLine().split(" ");
//        Integer numIntervals = Integer.valueOf(firstLine[0]);
//        Integer numDots = Integer.valueOf(firstLine[1]);
//        int[] startIntervals = new int[numIntervals];
//        int[] stopIntervals = new int[numIntervals];
//        for (int i = 0; i < numIntervals; i++) {
//            String[] line = scanner.nextLine().split(" ");
//            startIntervals[i] = Integer.valueOf(line[0]);
//            stopIntervals[i] = Integer.valueOf(line[1]);
//        }
        int numDots = 3;
        int[] startIntervals = new int[]{0, 7};
        int[] stopIntervals = new int[]{5, 10};
        QuickSort.sort(startIntervals, 0, startIntervals.length - 1);
        QuickSort.sort(stopIntervals, 0, stopIntervals.length - 1);
//        String[] dots = scanner.nextLine().split(" ");
        String[] dots = new String[]{"1", "6", "11"};
        int[] results = new int[numDots];
        for (int i = 0; i < numDots; i++) {
            int dot = Integer.valueOf(dots[i]);
            int diff = Math.abs(lessElements(startIntervals, dot) - lessElements(stopIntervals, dot));
            results[i] = diff;
        }
        System.out.println(Arrays.toString(results)
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
}
