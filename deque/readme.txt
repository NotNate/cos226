Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
I used a linked list for deque as it was easiest to make a front and a back
using the nodes of the linked list. I chose an array for randomized queue only
because I originally tried doing a doubly linked list for randomized queue and
had it set up wonderfully but then couldn't iterate over it randomly. Arrays
are much easier to work with when it comes to randomness and iterating over
randomly.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  N  bytes

Deque:              ~  48N  bytes

For deque, it's 16 b overhead, 8 b for extra overhead for inner class, then
3 references of 8 b each, so 24 + 24 = 48, times N amount of nodes ~48N.

For randomized queues it is 16b overhead, 4b for int, then the actual array is
of objects with N references, as it is really just a collection of Items to
which we don't know the size of. So we get just an N amount of Items plus
padding and length. So N + some amount.


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
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */
