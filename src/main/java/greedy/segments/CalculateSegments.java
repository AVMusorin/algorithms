package greedy.segments;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Segment {
    public Integer start;
    public Integer stop;
    Segment(Integer start, Integer stop) {
        this.start = start;
        this.stop = stop;
    }
    public String toString() {
        return start + ":" + stop;
    }
}

class CalculateSegments {
    private static List<Segment> sortLines(List<Segment> lines) {
        return lines.stream()
                .sorted(Comparator.comparing(line -> line.stop))
                .collect(Collectors.toList());
    }

    private static List<String> сommonPoints(List<Segment> lines) {
        List<String> common = new ArrayList<>();
        Segment current = lines.get(0);
        for (Integer i = 1; i < lines.size(); i++) {
            // когда прервется последовательность меньших начал отрезков
            if (current.stop < lines.get(i).start) {
                common.add(current.stop.toString());
                current = lines.get(i);
                if (i + 1 >= lines.size()) {
                    common.add(current.stop.toString());
                }
                continue;
            }
            if (i + 1 >= lines.size()) {
                common.add(current.stop.toString());
            }
        }
        return common;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numLines = scanner.nextInt();
        List<String> result;
        List<Segment> lines = new ArrayList<>();
        for (Integer i = 1; i <= numLines; i++) {
            lines.add(new Segment(scanner.nextInt(), scanner.nextInt()));
        }

        if (lines.size() > 1) {
            result = сommonPoints(sortLines(lines));
        } else {
            result = new ArrayList<String>();
            result.add(lines.get(0).stop.toString());
        }
        System.out.println(result.size());
        System.out.println(String.join(" ", result));
    }
}
