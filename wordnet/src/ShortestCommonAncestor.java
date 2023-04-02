import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ShortestCommonAncestor {
    private Digraph rdag; // rooted digraph

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        if (G == null) throw new IllegalArgumentException("Digraph is null.");
        rdag = new Digraph(G);
        if (!isRooted(rdag)) throw new IllegalArgumentException("Not a Rooted DAG.");
    }

    // If it is acyclic and has a root, it is rooted
    private boolean isRooted(Digraph G) {
        DirectedCycle dc = new DirectedCycle(G);
        return !dc.hasCycle() && hasRoot(G);
    }

    // Check to see if digraph contains a root
    private boolean hasRoot(Digraph G) {
        int roots = 0;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i) == 0) { // delete indegree?
                roots++;
            }
        }
        return roots == 1;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        return scaAndLength(v, w)[1];
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) { // need to consider if v or w is a ancestor
        return scaAndLength(v, w)[0];
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int lengthSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null)
            throw new IllegalArgumentException("Subset is null.");
        for (Integer v : subsetA)
            if (v == null) throw new
                    IllegalArgumentException("Element in subset is null.");
        for (Integer v : subsetB)
            if (v == null) throw new
                    IllegalArgumentException("Element in subset is null.");
        return scaAndLength(subsetA, subsetB)[1];
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestorSubset(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null)
            throw new IllegalArgumentException("Subset is null.");
        for (Integer v : subsetA)
            if (v == null)
                throw new IllegalArgumentException("Element in subset is null.");
        for (Integer v : subsetB)
            if (v == null)
                throw new IllegalArgumentException("Element in subset is null.");
        return scaAndLength(subsetA, subsetB)[0];
    }

    // return the shortest common ancestor and the lengths of v and w from it
    private int[] scaAndLength(int v, int w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(rdag, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(rdag, w);
        int minDistance = Integer.MAX_VALUE;
        int sca = 0;
        for (int i = 0; i < rdag.V(); i++) {
            if (bfsv.hasPathTo(i)) { // if i is ancestor of v
                if (bfsw.hasPathTo(i)) { // if i is ancestor of w
                    // then it is a common ancestor
                    int dist = (bfsv.distTo(i) + bfsw.distTo(i));
                    if (minDistance > dist) {
                        sca = i;
                        minDistance = dist;
                    }
                }
            }
        }
        int[] sal = { sca, minDistance };
        return sal;
    }

    // return the shortest common ancestor and the lengths of v and w from it
    // iterable version
    private int[] scaAndLength(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(rdag, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(rdag, w);
        int minDistance = Integer.MAX_VALUE;
        int sca = 0;
        for (int i = 0; i < rdag.V(); i++) {
            if (bfsv.hasPathTo(i)) { // if i is ancestor of v
                if (bfsw.hasPathTo(i)) { // if i is ancestor of w
                    // then it is a common ancestor
                    int dist = (bfsv.distTo(i) + bfsw.distTo(i));
                    if (minDistance > dist) {
                        sca = i;
                        minDistance = dist;
                    }
                }
            }
        }
        int[] sal = { sca, minDistance };
        return sal;
    }

    // unit testing (required)
    public static void main(String[] args) {
        In in = new In("digraph10.txt");
        int v = Integer.parseInt(args[1]);
        int w = Integer.parseInt(args[2]);
        Stack<Integer> s1 = new Stack<Integer>();
        s1.push(0);
        s1.push(1);
        s1.push(2);
        Stack<Integer> s2 = new Stack<Integer>();
        s2.push(3);
        s2.push(4);
        s2.push(5);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        // int v = StdIn.readInt();
        // int w = StdIn.readInt();
        int length = sca.length(v, w);
        int ancestor = sca.ancestor(v, w);
        int subLength = sca.ancestorSubset(s1, s2);
        int subAncestor = sca.lengthSubset(s1, s2);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        StdOut.printf("subset length = %d, subset ancestor = %d\n",
                      subLength, subAncestor);
    }

}