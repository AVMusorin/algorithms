package greedy.huffman;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Node {
    String element;
    int weight;
    String code;

    Node left = null;
    Node right = null;

    Node(String element, int weight) {
        this.element = element;
        this.weight = weight;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }



    public String getCode(String element) {
        String leftCode = recursiveGetCode(element, left, "");
        String rightCode = recursiveGetCode(element, right, "");
        return getCodeNode(leftCode, rightCode);
    }

    private String getCodeNode(String leftCode, String rightCode) {
        if (leftCode != null) {
            return leftCode;
        } else if (rightCode != null) {
            return rightCode;
        } else {
            return null;
        }
    }

    private String recursiveGetCode(String element, Node node, String code) {
        if (isLeaf(node) && node.element.equals(element)) {
            return code + node.code;
        }
        if (!isLeaf(node)) {
            String leftCode = recursiveGetCode(element, node.left, code + node.code);
            String rightCode = recursiveGetCode(element, node.right, code + node.code);
            return getCodeNode(leftCode, rightCode);
        }
        return null;
    }

    @Override
    public String toString() {
        return element + " " + weight;
    }
}

public class HuffmanCode {
    public static List<Node> initQueueElements(List<String> letters) {
        Map<String, Long> grouped = letters.stream().collect(Collectors
                .groupingBy(Function.identity(), Collectors.counting()));
        return grouped.entrySet().stream()
                .map(letter -> new Node(letter.getKey(), letter.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public static List<Node> sortQueue(List<Node> nods) {
        return nods.stream()
                .sorted(Comparator.comparingInt(f -> f.weight))
                .collect(Collectors.toList());
    }

    public static List<Node> recursiveCreateTree(List<Node> queue) {
        if (queue.size() == 1) {
            return queue;
        }

        Node leftElement = queue.get(0);
        leftElement.code = "0";
        Node rightElement = queue.get(1);
        rightElement.code = "1";
        queue = queue.subList(2, queue.size());
        Node newNode = new Node(null, leftElement.weight + rightElement.weight);
        newNode.left = leftElement;
        newNode.right = rightElement;
        queue.add(newNode);
        return recursiveCreateTree(sortQueue(queue));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> elements = new HashMap<>();
        List<String> letters = Arrays.asList(scanner.next().split(""));
        String decodedInput = String.join("", letters);
        List<Node> initQueue = initQueueElements(letters);
        if (initQueue.size() > 1) {
            Node tree = recursiveCreateTree(sortQueue(initQueue)).get(0);
            for (String letter: letters) {
                String code = tree.getCode(letter);
                elements.put(letter, code);
                decodedInput = decodedInput.replace(letter, code);
            }

        } else {
            elements.put(initQueue.get(0).element, "0");
            decodedInput = decodedInput.replace(initQueue.get(0).element, "0");
        }

        System.out.println(elements.size() + " " + decodedInput.length());
        elements.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(decodedInput);
    }
}

