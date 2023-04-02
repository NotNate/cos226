import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n; // Elements in the queue
    private Item[] queue; // Array of Items
    private final int STARTING_VALUE = 8; // Starting value for random queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.n = 0;
        // Start array at size 8
        this.queue = (Item[]) new Object[STARTING_VALUE];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item is null");
        // Resize if needed to 2n
        if (n == queue.length) resize(n * 2);
        queue[n] = item; // Set the nth position to be the item
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("No element in queue.");
        // Get random number between [0, n)
        int random = StdRandom.uniformInt(0, n);
        Item item = queue[random]; // Set item to return as that position
        queue[random] = queue[n - 1]; // Swap the last element with random pos.
        queue[n - 1] = null; // Set the n - 1 position to null for resize setup

        // Resize array if needed
        if (n <= queue.length / 4) resize(queue.length / 2);

        n--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("No element in queue.");
        // Get random number between [0, n)
        int random = StdRandom.uniformInt(0, n);
        return queue[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i; // Just an iterator variable
        private Item[] copy; // Array that will be the copy of the queue array

        public RandomizedQueueIterator() {
            i = 0;
            copy = (Item[]) new Object[n]; // Create copy of size n
            for (int j = 0; j < n; j++) {
                copy[j] = queue[j]; // Set copy equal to queue
            }
            StdRandom.shuffle(copy); // Randomize the copy array
        }

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy[i++]; // Return the item at the i-th position
        }
    }

    /* @citation Adapted from:
    https://edstem.org/us/courses/27701/lessons/43921/slides/255766
     * resize.java. Accessed 9/25/2022. */
    private void resize(int newCapacity) {
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < n; i++)
            temp[i] = queue[i];
        queue = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 15; i++) {
            rq.enqueue(i); // Enqueue 1 through 15
        }

        StdOut.println(rq.sample());

        // Iterator test
        for (int x : rq) StdOut.println(x); // Print out the entire queue
        StdOut.println("Size: " + rq.size());
        // Test if it is empty.
        if (rq.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        for (int i = 0; i < 8; i++) {
            int rand = rq.dequeue(); // Dequeue a bunch
            StdOut.println(rand);
        }
        StdOut.println("Size: " + rq.size());
        // Test if it is empty.
        if (rq.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        for (int i = 0; i < 5; i++) {
            int rand = rq.dequeue();
            StdOut.println(rand);
        }
        StdOut.println("Size: " + rq.size());
        // Test if it is empty.
        if (rq.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        for (int i = 0; i < 5; i++) {
            StdOut.println("Sample of what's left: " + rq.sample());
        }
        // Test if it is empty.
        if (rq.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        rq.dequeue();
        rq.dequeue();

        // Test if it is empty.
        if (rq.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");
    }
}
