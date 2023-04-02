import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    private String s; // original string
    private CircularSuffix[] cs; // array of circular suffixes

    private class CircularSuffix implements Comparable<CircularSuffix> {
        private String s; // starting string
        private int offset; // suffix start index
        private int index; // index of the sorted array
        private int length; // length of string

        // fancier circular suffix
        public CircularSuffix(String s, int offset, int index) {
            this.s = s;
            this.length = s.length();
            this.offset = offset;
            this.index = index;
        }

        // compareTo function for comparable
        public int compareTo(CircularSuffix that) {
            int offsetOne = this.offset;
            int offsetTwo = that.offset;
            for (int i = 0; i < length; i++) {
                int charOne = s.charAt(offsetOne++); // character at this
                int charTwo = s.charAt(offsetTwo++); // characters at that
                if (charOne != charTwo) { // if characters are not the same
                    if (charOne > charTwo) return 1; // comparator functions
                    else return -1; // ^
                }
                if (offsetOne >= length) offsetOne = 0; // reset offset circularly
                if (offsetTwo >= length) offsetTwo = 0; // ^
            } // if they are the same it'll just eventually be zero
            return 0;
        }

        // toString testing
        public String toString() {
            StringBuilder last = new StringBuilder();
            String first = s.substring(0, index);
            last.append(s.substring(index, length));
            last.append(first);
            return last.toString();
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException("Null string.");
        this.s = s;
        this.cs = new CircularSuffix[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cs[i] = new CircularSuffix(s, i, i);
        }
        Arrays.sort(cs);
    }

    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i > length() - 1 || i < 0) throw new
                IllegalArgumentException("i outside range.");
        return cs[i].index;
    }

    // convert to string for testing
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CircularSuffix suffix : cs) {
            sb.append(suffix.toString()).append("\n");
        }
        return sb.toString();
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray cs = new CircularSuffixArray(s);
        StdOut.println(cs); // testing array printing function
        for (int i = 0; i < cs.length(); i++) // test length
            StdOut.println(cs.index(i)); // test index
    }
}
