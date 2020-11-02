public class ArrayDeque<T> {

    private int size;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {
        resize();
        T[] temp = (T[]) new Object[items.length];
        copy(temp);
        items[0] = item;
        for (int i = 1; i < items.length; i++) {
            items[i] = temp[i - 1];
        }
        size++;
    }

    public void addLast(T item) {
        resize();
        items[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    private void copy(T[] temp) {
        for (int i = 0; i < items.length && i < temp.length; i++) {
            temp[i] = items[i];
        }
    }

    private void resize() {
        if (items.length > 16 && size < items.length * 0.5) {
            while (items.length > 16 && size < items.length * 0.5) {
                T[] temp = (T[]) new Object[items.length / 2];
                copy(temp);
                items = temp;
            }
        } else if (size == items.length) {
            T[] temp = (T[]) new Object[items.length * 2];
            copy(temp);
            items = temp;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rem = items[0];
        T[] temp = (T[]) new Object[items.length];
        copy(temp);
        for (int i = 0; i < size - 1; i++) {
            items[i] = temp[i + 1];
        }
        items[size - 1] = null;
        size--;
        resize();
        return rem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rem = items[size - 1];
        items[size - 1] = null;
        size--;
        resize();
        return rem;
    }

    public T get(int index) {
        if (index > items.length) {
            return null;
        }
        return items[index];
    }
}
