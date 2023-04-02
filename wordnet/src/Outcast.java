import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private WordNet wn; // wordnet object

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        wn = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDistance = 0;
        String outcast = null;
        for (String noun1 : nouns) {
            int dist = 0;
            for (String noun2 : nouns) {
                dist += wn.distance(noun1, noun2);
            }
            if (dist >= maxDistance) {
                maxDistance = dist;
                outcast = noun1;
            }
        }
        return outcast;
    }

    // test client (see below)
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            String outcasts = outcast.outcast(nouns);
            StdOut.println(args[t] + ": " + outcasts);
        }
    }

}