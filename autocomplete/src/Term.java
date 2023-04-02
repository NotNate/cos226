import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query; // query string
    private long weight; // associated integer weight

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null || weight < 0) throw new
                IllegalArgumentException("Invalid arguments.");
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term term1, Term term2) {
            return Long.compare(term2.weight, term1.weight);
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }

    private static class PrefixOrder implements Comparator<Term> {
        private int r; // length of prefix

        // prefix constructor
        public PrefixOrder(int r) {
            if (r < 0) throw new
                    IllegalArgumentException("r cannot be negative.");
            this.r = r;
        }

        public int compare(Term term1, Term term2) {
            int t1 = term1.query.length();
            int t2 = term2.query.length();

            // Block of if statements prevents substring from calling
            // more than the string actually contains
            if (t1 < r && t2 < r) {
                return term1.query.substring(0, t1)
                                  .compareTo(term2.query.substring(0, t2));
            }
            else if (t1 < r) {
                return term1.query.substring(0, t1)
                                  .compareTo(term2.query.substring(0, r));
            }
            else if (t2 < r) {
                return term1.query.substring(0, r)
                                  .compareTo(term2.query.substring(0, t2));
            }
            else return term1.query.substring(0, r)
                                   .compareTo(term2.query.substring(0, r));
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Term termone = new Term("One", 10);
        Term termtwo = new Term("Two", 20);
        Term termthree = new Term("Three", 15);
        Term termfour = new Term("Four", 5);
        Term termfive = new Term("Five", 50);
        Term[] array = { termone, termtwo, termthree, termfour, termfive };
        // Testing the printing
        StdOut.println("Regular order:");
        for (int i = 0; i < array.length; i++) {
            StdOut.println(array[i]);
        }
        // Sort it by reverse weight
        Arrays.sort(array, byReverseWeightOrder());
        StdOut.println("Reverse weight order:");
        for (int i = 0; i < array.length; i++) {
            StdOut.println(array[i]);
        }
        // Sort it by prefix
        Arrays.sort(array, byPrefixOrder(3));
        StdOut.println("By prefix order r = 3:");
        for (int i = 0; i < array.length; i++) {
            StdOut.println(array[i]);
        }
        StdOut.println("Should be nonzero (Compare 1 to 2): "
                               + array[1].compareTo(array[2]));
        StdOut.println("Should be 0 (Compare 1 to 1):"
                               + " " + array[1].compareTo(array[1]));
    }
}

