import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Objects;

public class BurrowsWheeler {
    private static final int R = 256;
    private static final int LG_R = 8;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        // String s = "zebra";
        String s = BinaryStdIn.readString();
        CircularSuffixArray cs = new CircularSuffixArray(s);
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (cs.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < length; i++) {
            int index = (cs.index(i) - 1);
            if (index < 0) index = length - 1;
            BinaryStdOut.write(s.charAt(index));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int x = BinaryStdIn.readInt();
        String ascii = BinaryStdIn.readString();
        // int x = 4;
        // String ascii = "rezba";
        int n = ascii.length();
        int[] next = new int[n];
        char[] t = new char[n];
        for (int i = 0; i < n; i++) t[i] = ascii.charAt(i);
        // compute frequency counts
        int[] count = new int[R + 1];
        for (int i = 0; i < n; i++)
            count[t[i] + 1]++;

        // compute cumulates
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        // move data
        for (int i = 0; i < n; i++)
            next[count[t[i]]++] = i;

        // printing to binaryout
        for (int i = 0; i < n; i++) {
            x = next[x];
            BinaryStdOut.write(t[x]);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (Objects.equals(args[0], "-")) transform();
        else if (Objects.equals(args[0], "+")) inverseTransform();
    }
}
