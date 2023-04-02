import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Deque<Item> implements Iterable<Item> {

    private int n; // Amount of elements in deque
    private Node front; // Node representing the front of the queue
    private Node back; // Node representing the back of the queue

    private class Node {
        private Item item; // The item being stored in the node
        private Node next; // The node directly in front in the LL
        private Node prev; // The node directly behind in the LL
    }

    // construct an empty deque
    public Deque() {
        this.n = 0;
        this.front = null;
        this.back = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new
                IllegalArgumentException("Item cannot be null.");
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            back = node;
        }
        else {
            front.prev = node;
        }
        node.next = front;
        front = node;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new
                IllegalArgumentException("Item cannot be null.");
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            front = node;
        }
        else {
            back.next = node;
        }
        node.prev = back;
        back = node;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("No element in deque.");
        if (front.item == null) return removeLast();
        Item item = front.item;

        if (Objects.equals(back, front)) {
            front = null;
            back = null;
        }
        else {
            front.next.prev = null;
            front = front.next;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("No element in deque.");
        if (back.item == null) return removeFirst();
        Item item = back.item;

        if (Objects.equals(back, front)) { // If the nodes front & back are same
            front = null;
            back = null;
        }
        else {
            back.prev.next = null;
            back = back.prev;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeItereator();
    }

    private class DequeItereator implements Iterator<Item> {
        private Node current; // Private node that represents the current node

        // Create new deque iterator node starting with front
        public DequeItereator() {
            current = front;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item x = current.item;
            current = current.next;
            return x;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // one through ten deque
        Deque<Integer> oneThroughTen = new Deque<>();
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");
        for (int i = 1; i <= 10; i++) {
            oneThroughTen.addLast(i);
        }
        StdOut.println("one through ten:");
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test\
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Now should be 4 through 10:");
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Now should be 0, followed by 4 through 11:");
        oneThroughTen.addLast(11);
        oneThroughTen.addFirst(0);
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test

        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Now should be 0, followed by 4 through 8:");
        oneThroughTen.removeLast();
        oneThroughTen.removeLast();
        oneThroughTen.removeLast();
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Now should be 8");
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        oneThroughTen.removeFirst();
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Should be an empty deque:"); // Try using remove last
        oneThroughTen.removeLast();
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");

        StdOut.println("Should be an empty deque:"); // Try using remove first
        oneThroughTen.addLast(1);
        oneThroughTen.removeFirst();
        for (int x : oneThroughTen) StdOut.println(x); // Iterator test
        StdOut.println("Size: " + oneThroughTen.size());
        if (oneThroughTen.isEmpty()) StdOut.println("Empty!");
        else StdOut.println("Not empty!");
    }
}