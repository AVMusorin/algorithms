package binary_tree;

import com.sun.istack.internal.NotNull;

class Node<T extends Comparable<T>> {
    private T element;

    private Node<T> left;
    private Node<T> right;

    public T getElement() {
        return element;
    }

    public Node(T element) {
        this.element = element;
    }

    public void setElement(@NotNull T element) {
        this.element = element;
    }

    public Node<T> getLeft() {
        return left;
    }

    void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        String result = "Root: " + this.getElement() + "\n";

        if (this.left != null) {
            result += " Left: " + left.toString() + "\n";
        } else {
            result += " Left is empty \n";
        }

        if (this.right != null) {
            result += " Right: " + right.toString() + "\n";
        } else {
            result += " Right is empty \n";
        }
        return result;
    }
}
