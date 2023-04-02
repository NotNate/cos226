Programming Assignment 6: WordNet


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */
We use two SeparateChainingHashST to store the nouns and their respective IDs,
and the IDs and their respective nouns. We realized that nouns appear more than
once so we accounted for this by creating a symbol table with a String and a
stack of integers to represent the different synset IDs the noun could have.


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */
We create a digraph and construct it by reading in the hypernyms file and adding
edges for every hypernym linked to a noun id.


/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify
 *  your answer.
 **************************************************************************** */

Description: We just checked to see for every vertex if it had an outdegree
of zero, and then increased a root counter, as this is the root definition.
This would be just linear time O(V).
However, we used a DirectedCycle object, which takes O(E + V) to construct,
but constant time to use. Thus, the full runtime is:



Order of growth of running time: O(E + V)


/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify your
 *  answers.
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description: We compute the shortest common ancestor by interating through all the
vertices, and checking if it is an ancestor of v. If it is, then we check if it is
a common ancestor by checking if it is also ancestor of w. We then update and
keep track of the common ancestor that has the shortest distance from v and w.


                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                O(E + V)            O(E + V)

ancestor()              O(E + V)            O(E + V)

lengthSubset()          O((a*b) (E + V))   O((a*b) (E + V))

ancestorSubset()       O((a*b) (E + V))    O((a*b) (E + V))



/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
Partner 1 implemented wordnet and other miscellaneous methods.
Parter 2 helped unit tetst and implement ancestors algorithm.



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */
