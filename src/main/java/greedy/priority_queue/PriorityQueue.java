package greedy.priority_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface ChildFunc {
    /**
     * Находит дочерний элемент кучи
     *
     * @param index - индекс родителя
     * @param q     - куча
     * @return Дочерний элемент найденный по определенному алгоритму
     */
    ElementTuple func(int index, List<Integer> q);
}

class ElementTuple {
    Integer value;

    int index;

    private boolean empty;

    ElementTuple() {
        empty = true;
    }

    ElementTuple(Integer value, int index) {
        this.value = value;
        this.index = index;
        this.empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }
}

class Heap {
    private static ChildFunc leftChildLambda = (int i, List<Integer> q) ->
            new ElementTuple(q.get(i * 2 + 1), i * 2 + 1);
    private static ChildFunc rightChildLambda = (int i, List<Integer> q) ->
            new ElementTuple(q.get(i * 2 + 2), i * 2 + 2);
    private List<Integer> queue = new ArrayList<>();

    private Integer getHead() {
        if (!queue.isEmpty()) {
            return queue.get(0);
        } else {
            throw new UnsupportedOperationException("Can't get head element");
        }
    }

    public void insert(Integer value) {
        queue.add(value);
        if (queue.size() > 1) {
            queue = recursiveShiftUp(value, queue.size() - 1);
        }
    }

    private ElementTuple getParent(int index) {
        // четное
        int parentIndex;
        if (index % 2 == 0) {
            parentIndex = Math.round((index - 1) / 2 * 10) / 10;
        } else {
            parentIndex = Math.round(index / 2 * 10) / 10;
        }
        return new ElementTuple(queue.get(parentIndex), parentIndex);
    }

    private ElementTuple getChild(ChildFunc lambda, int index) {
        try {
            return lambda.func(index, queue);
        } catch (IndexOutOfBoundsException e) {
            return new ElementTuple();
        }
    }

    /**
     * Минимальный потомок для идекса
     *
     * @param index переданный индекс
     * @return
     */
    private ElementTuple getMaxChild(int index) {
        ElementTuple leftChild = getChild(leftChildLambda, index);
        ElementTuple rightChild = getChild(rightChildLambda, index);
        if (rightChild.isEmpty() && leftChild.isEmpty()) {
            return leftChild;
        } else if (rightChild.isEmpty()) {
            return leftChild;
        } else if (leftChild.isEmpty()) {
            return rightChild;
        } else {
            if (leftChild.value <= rightChild.value) {
                return rightChild;
            } else {
                return leftChild;
            }
        }
    }

    private void shiftValues(int parentIndex, int childIndex) {
        Integer parentValue = queue.remove(parentIndex);
        Integer childValue = queue.remove(childIndex - 1);
        queue.add(parentIndex, childValue);
        queue.add(childIndex, parentValue);
    }

    private List<Integer> recursiveShiftUp(Integer value, int index) {
        // проверяем родителей
        ElementTuple parent = getParent(index);
        if (parent.value >= value) {
            // все ок, свойство кучи соблюдено
            return queue;
        } else {
            // если значение в потомке меньше значения в родителе, то поменять местами
            shiftValues(parent.index, index);
            return recursiveShiftUp(value, parent.index);
        }
    }

    private List<Integer> recursiveShiftDown(Integer value, int index) {
        ElementTuple minChild = getMaxChild(index);
        if (minChild.isEmpty()) {
            return queue;
        }
        if (value >= minChild.value) {
            // все ок, свойство кучи соблюдено
            return queue;
        } else {
            shiftValues(index, minChild.index);
            return recursiveShiftDown(value, minChild.index);
        }
    }

    public Integer extractMax() {
        Integer head = getHead();
        if (queue.size() <= 1) {
            queue.remove(0);
            return head;
        } else {
            Integer lastValue = queue.get(queue.size() - 1);
            queue.remove(0);
            queue.add(0, lastValue);
            queue.remove(queue.size() - 1);
            queue = recursiveShiftDown(lastValue, 0);
            return head;
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

public class PriorityQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int iterations = Integer.valueOf(scanner.nextLine());
        Heap heap = new Heap();
        heap.insert(200);
        heap.insert(10);
        heap.insert(5);
        heap.insert(500);
        System.out.println(heap);
        System.out.println(heap.extractMax());
        System.out.println(heap);
        System.out.println(heap.extractMax());
        System.out.println(heap);
        System.out.println(heap.extractMax());
        System.out.println(heap);
        System.out.println(heap.extractMax());
        System.out.println(heap);

//        for (int i = 0; i < iterations; i++) {
//            String[] input = scanner.nextLine().split(" ");
//            if (input[0].equals("Insert")) {
//                Integer value = Integer.valueOf(input[1]);
//                heap.insert(value);
//            }
//            if (input[0].equals("ExtractMax")) {
//                System.out.println(heap.extractMax());
//            }
//        }
    }
}
