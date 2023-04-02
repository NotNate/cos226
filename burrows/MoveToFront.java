import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Objects;

public class MoveToFront {
    private static final int R = 256; // number of ascii characters
    private static char[] ASCII; // array of ASCII characters
    private static int[] charIndex; // ascii indexes

    // build the arrays of the private variables
    private static void buildArrays() {
        ASCII = new char[R];
        charIndex = new int[R];
        for (int i = 0; i < R; i++) {
            ASCII[i] = (char) i;
            charIndex[i] = i;
        }
    }

    // move char c to the front of the array
    private static void moveToFront(char c) {
        int spot = charIndex[c];
        for (int i = spot; i > 0; i--) {
            ASCII[i] = ASCII[i - 1]; // move chars over to the right
            charIndex[ASCII[i]] = i; // adjust index to be it's new spot
        }
        // Reassign the first value in ASCII to zero
        // and then reassign it's index to 0
        ASCII[0] = c;
        charIndex[c] = 0;
    }

    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        buildArrays();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write((byte) charIndex[c]);
            moveToFront(c);
        }
        BinaryStdOut.close();

    }

    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {
        buildArrays();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char asciiChar = ASCII[c];
            BinaryStdOut.write((byte) asciiChar);
            moveToFront(asciiChar);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (Objects.equals(args[0], "-")) encode();
        else if (Objects.equals(args[0], "+")) decode();
    }
}
