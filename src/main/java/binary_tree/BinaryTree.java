package binary_tree;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

/**
 * В правую ветку помещаются меньшие значения, а в левую большие
 * Comparable
 * Integer a = 5
 * Integer b = 4
 * a.compareTo(b) = 1
 * @param <T>
 */
class BinaryTree<T extends Comparable<T>> {
    private static final Logger log = Logger.getLogger("BinaryTree");
    private Node<T> root;

    public BinaryTree(T root) {
        this.root = new Node<>(root);
    }

    Node<T> getRoot() {
        return this.root;
    }

    public void add(T element) {
        this.root = addRecursive(this.root, element);
    }

    public void remove(T element) {
        this.root = removeRecursive(this.root, element);
    }

    public boolean contains(T element) {
        return containsRecursive(this.root, element);
    }

    private boolean containsRecursive(Node<T> root, @NotNull T element) {
        if (root == null) return false;
        if (root.getElement() == element) {
            return true;
        } else if (root.getElement().compareTo(element) < 0){
            return containsRecursive(root.getLeft(), element);
        } else {
            return containsRecursive(root.getRight(), element);
        }
    }

    private Node<T> removeRecursive(Node<T> root, @NotNull T element) {
        if (root == null) return null;
        T rootElement = root.getElement();
        if (rootElement.compareTo(element) == 0) {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            } else if (root.getRight() == null && root.getLeft() != null) {
                return root.getLeft();
            } else if (root.getLeft() == null && root.getRight() != null) {
                return root.getRight();
            } else {
                T smallestElement = findSmallestElement(root.getRight());
                root.setElement(smallestElement);
                root.setLeft(removeRecursive(root.getRight(), element));
                return root;
            }
        } else if (rootElement.compareTo(element) < 0) {
            root.setRight(removeRecursive(root.getRight(), element));
            return root;
        } else {
            root.setLeft(removeRecursive(root.getLeft(), element));
            return root;
        }
    }

    private T findSmallestElement(Node<T> node) {
        return node.getRight() == null ? node.getElement() :
                findSmallestElement(node.getRight());
    }

    private Node<T> addRecursive(Node<T> root, @NotNull T element) {
        if (root == null) {
            return new Node<>(element);
        }
        T rootElement = root.getElement();
        if (rootElement.compareTo(element) < 0) {
            log.debug("Go to left with - " + element.toString());
            root.setLeft(addRecursive(root.getLeft(), element));
        } else if (rootElement.compareTo(element) > 0) {
            log.debug("Go to right with - " + element.toString());
            root.setRight(addRecursive(root.getRight(), element));
        } else {
            return root;
        }
        log.debug("Write element - " + element.toString());
        return root;
    }
}
