import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
    private ShortestCommonAncestor sca; // SCA API
    private Digraph digraph; // digraph object
    private SeparateChainingHashST<Integer, String> idSynsets; // synsets hashtable
    private SeparateChainingHashST<String, Stack<Integer>> nounIDs; // noun hashtable


    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("File is null.");
        idSynsets = new SeparateChainingHashST<>();
        nounIDs = new SeparateChainingHashST<>();
        In syn = new In(synsets);
        In hyp = new In(hypernyms);
        createSynsets(syn);
        createHypernyms(hyp);
        sca = new ShortestCommonAncestor(digraph);
    }

    // helper method to read in and store synsets from input
    private void createSynsets(In in) {
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            String[] nouns = fields[1].split(" ");
            int id = Integer.parseInt(fields[0]);
            for (String noun : nouns) {
                Stack<Integer> stack = new Stack<>();
                if (nounIDs.contains(noun)) {
                    stack = nounIDs.get(noun);
                }
                stack.push(id);
                nounIDs.put(noun, stack);
            }
            idSynsets.put(Integer.parseInt(fields[0]), fields[1]);
        }
    }

    // helper method to read in hypernyms and build digraph from input
    private void createHypernyms(In in) {
        digraph = new Digraph(idSynsets.size());
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            for (int i = 1; i < fields.length; i++) {
                digraph.addEdge(Integer.parseInt(fields[0]),
                                Integer.parseInt(fields[i]));
            }
        }
    }

    // the set of all WordNet nouns
    public Iterable<String> nouns() {
        return nounIDs.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("Word is null.");
        return nounIDs.contains(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2))
            throw new IllegalArgumentException("Not a WordNet noun.");
        if (noun1 == null || noun2 == null) throw new
                IllegalArgumentException("Noun is Null.");
        Stack<Integer> stack1 = nounIDs.get(noun1);
        Stack<Integer> stack2 = nounIDs.get(noun2);
        int ancestor;
        if (stack1.size() == 1 && stack2.size() == 1) {
            ancestor = sca.ancestor(stack1.peek(), stack2.peek());
        }
        else {
            ancestor = sca.ancestorSubset(stack1, stack2);
        }
        return idSynsets.get(ancestor);
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        if (!isNoun(noun1) || !isNoun(noun2))
            throw new IllegalArgumentException("Not a WordNet noun.");
        if (noun1 == null || noun2 == null) throw new
                IllegalArgumentException("Noun is Null.");
        Stack<Integer> stack1 = nounIDs.get(noun1);
        Stack<Integer> stack2 = nounIDs.get(noun2);
        if (stack1.size() == 1 && stack2.size() == 1) {
            return sca.length(stack1.peek(), stack2.peek());
        }
        return sca.lengthSubset(stack1, stack2);
    }

    // unit testing (required)
    public static void main(String[] args) {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        String noun1 = "President";
        StdOut.println("Is " + noun1 + " a noun? " + wordnet.isNoun(noun1));
        String noun2 = "individual";
        String noun3 = "edible_fruit";
        StdOut.println("Shortest Common Ancestor of " + noun2 + " and " + noun3
                               + " ? " + wordnet.sca(noun2, noun3));
        String noun4 = "white_marlin";
        String noun5 = "mileage";
        StdOut.println("Distance between " + noun4 + " and " + noun5
                               + " ? " + wordnet.distance(noun4, noun5));
        StdOut.println("All nouns: " + wordnet.nouns());
    }

}