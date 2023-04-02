import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key,
                                         Comparator<Key> comparator) {
        if (a == null || comparator == null || key == null)
            throw new IllegalArgumentException("Invalid argument.");
        /* @citation Adapted from:
        https://algs4.cs.princeton.edu/11model/BinarySearch.java.html
         * princeton/cs/algs4/BinarySearch.java. Accessed 9/30/2022. */
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else if (mid == lo) return mid;
            else hi = mid;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || comparator == null || key == null)
            throw new IllegalArgumentException("Invalid argument.");
         /* @citation Adapted from:
        https://algs4.cs.princeton.edu/11model/BinarySearch.java.html
         * princeton/cs/algs4/BinarySearch.java. Accessed 9/30/2022. */
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo + 1) / 2;
            int compare = comparator.compare(key, a[mid]);
            if (compare < 0) hi = mid - 1;
            else if (compare > 0) lo = mid + 1;
            else if (mid == hi) return mid;
            else lo = mid;
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        String[] a = {
                "AAAA", "AAAA", "AABB", "AABB", "ABAB",
                "ABBA", "ABBA", "BAAA", "BABA", "BABA",
                "BABB", "BBAA", "BBAB", "BBBA", "BBBB"
        };
        String query = "BABA";
        int lastIndex = BinarySearchDeluxe.
                lastIndexOf(a, query, String.CASE_INSENSITIVE_ORDER);
        int firstIndex = BinarySearchDeluxe.
                firstIndexOf(a, query, String.CASE_INSENSITIVE_ORDER);
        StdOut.println("Last index: " + lastIndex);
        StdOut.println("First index: " + firstIndex);
    }
}
