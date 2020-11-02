import java.util.*;

public class ArraySet <T> implements Iterable<T> {
    // by implementing Iterable<T>, it allows for enhanced for loops
    // T - generic type variable
    // replace it with actual type arguments
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    // returns true if this map contains a mapping for the specified key
    public boolean contains(T x) {
        for(int i = 0; i < size(); i++) {
            if (items[i] == null && x == null) {
                return true;
            }
            if (x.equals(items[i])) { // CANNOT do x == items[i]
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        if (contains(x)) {
            return;
        }
        items[size()] = x;
        size += 1;
    }

    public int size() {
        return size;
    }

    // returns an iterator (a.k.a. seer) into ME
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int position;
        public ArraySetIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size();
        }

        public T next() {
            T returnItem = items[position];
            position += 1;
            return returnItem;
        }
    }

    /*
    @Override
    public String toString() {
        // {3, 4, 5} curly braces instead of brackets
        String returnString = "{";
        int location = 0;
        for (T item: items) {
            if (item != null) {
                if (location != size() - 1) {
                    returnString += item.toString() + ", ";
                } else {
                    returnString += item.toString();
                }
                location += 1;
            }
        }
        return returnString + "}";
    }
    */

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size() - 1; i += 1) {
            returnSB.append(items[i].toString() + ", ");
        }
        returnSB.append(items[size() - 1].toString() + "}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        // s.add(null);  Successfully throws the exception
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse")); // true
        System.out.println(s.size()); // three

        Set<String> s2 = new HashSet<>();
        s2.add(null);
        System.out.println(s2.contains(null)); // true

        Set<Integer> javaset = new HashSet<>();
        javaset.add(5);
        javaset.add(23);
        javaset.add(42);
        for (int i : javaset) {
            System.out.print(i + "  "); // 5  23  42
        }
        System.out.println();
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        Iterator<Integer> seer = javaset.iterator();
        while (seer.hasNext()) {
            System.out.print(seer.next() + "  "); // 5  23  42
        }
        System.out.println();
        // Checks for an enhanced for loop:
        // A. Does the Set interface have an iterator() method?
        // B. Does the Set interface have next() / hasNext() methods?
        // C. Does the Iterator interface have an iterator() method?
        // D. Does the Iterator interface have next() / hasNext() methods?

        Iterator<Integer> aseer = aset.iterator();
        while (aseer.hasNext()) {
            int i = aseer.next();
            System.out.print(i + "  "); // 5  23  42
        }
        System.out.println();

        for (int i : aset) {
            System.out.print(i + "  "); // 5  23  42
        }
        System.out.println();
        System.out.println(aset); // {5, 23, 42}

        // .equals() method
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);
        System.out.println(aset.equals(aset2)); // true
    }
}
