public class LinkedListDeque<T> {
    private TNode first;
    private int size;

    private class TNode {
        private T item;
        private TNode next;
        private TNode prev;

        TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    // Empty list
    public LinkedListDeque() {
        first = new TNode(null, null, null);
        size = 0;
        first.next = first;
        first.prev = first;
    }

    public void addFirst(T item) {
        first.next = new TNode(item, first.next, first);
        first.next.next.prev = first.next;
        size++;
    }

    public void addLast(T item) {
        first.prev = new TNode(item, first, first.prev);
        first.prev.prev.next = first.prev;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode temp = first;
        while (temp.next != first && temp.next != null) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rem = first.next.item;
        first.next.next.prev = first;
        first.next = first.next.next;
        size--;
        return rem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rem = first.prev.item;
        first.prev.prev.next = first;
        first.prev = first.prev.prev;
        size--;
        return rem;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        TNode rem = first.next;
        for (int i = 0; i < index; i++) {
            rem = rem.next;
        }
        return rem.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(first.next, index);
    }

    private T getRecursiveHelper(TNode node, int index) {
        if (index <= 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
