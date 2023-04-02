import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] terms; // array of terms
    private int firstIndex; // The first index of the prefix

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("Array is null.");
        this.terms = new Term[terms.length];
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == null)
                throw new IllegalArgumentException("Array entry is null");
            this.terms[i] = terms[i];
        }
        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix is null.");
        int size = numberOfMatches(prefix);
        int j = firstIndex;
        Term[] prefixes = new Term[size];
        for (int i = 0; i < size; i++) {
            prefixes[i] = terms[j++];
        }
        Comparator<Term> comparator = Term.byReverseWeightOrder();
        Arrays.sort(prefixes, comparator);
        return prefixes;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix is null.");
        Term pre = new Term(prefix, 0);
        int r = prefix.length();
        int start = BinarySearchDeluxe.firstIndexOf(terms, pre, Term.byPrefixOrder(r));
        this.firstIndex = start;
        int end = BinarySearchDeluxe.lastIndexOf(terms, pre, Term.byPrefixOrder(r));
        if (end < 0 || start < 0) return 0;
        return end - start + 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
