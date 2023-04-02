Programming Assignment 3: Autocomplete


/* *****************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that is equal to the search key.
 **************************************************************************** */
We implemented the Binary Search from the algs4 library. To start, we set the lo
equal to 0 and the hi equal to n - 1 where n is the length of the array. Then
we get the middle value of the array lo + (hi - lo) / 2, and then as long as
lo is less than or equal to hi, we go through the following steps:
1. Compare the key we're looking for to the middle value
2. If it's larger, we set the new lo equal to mid + 1
3. If it's smaller, we set the new hi equal to mid - 1
4. If they're equal and mid == lo, we just return mid
5. Otherwise, we set hi = mid
6. If there is no value found, we return -1.

/* *****************************************************************************
 *  Identify which sorting algorithm (if any) that your program uses in the
 *  Autocomplete constructor and instance methods. Choose from the following
 *  options:
 *
 *    none, selection sort, insertion sort, mergesort, quicksort, heapsort
 *
 *  If you are using an optimized implementation, such as Arrays.sort(),
 *  select the principal algorithm.
 **************************************************************************** */

Autocomplete() : According to a google search, Arrays.sort() uses dual pivot
quicksort, which is what we used in that case.

allMatches() : Same as above.

numberOfMatches() : None.

/* *****************************************************************************
 *  How many compares (in the worst case) does each of the operations in the
 *  Autocomplete data type make, as a function of both the number of terms n
 *  and the number of matching terms m? Use Big Theta notation to simplify
 *  your answers.
 *
 *  Recall that with Big Theta notation, you should discard both the
 *  leading coefficients and lower-order terms, e.g., Theta(m^2 + m log n).
 **************************************************************************** */

Autocomplete():     Theta(n log n)

allMatches():       Theta(mlogm + logn)

numberOfMatches():  Theta(log n)




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe any serious problems you encountered.                    
 **************************************************************************** */


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */
We followed the protocol on the page.
Partner 1 - Helped explain and implement the BSD class and part of the Term class.
Partner 2 - Coded with Partner 1 on Autocomplete for all of the methods.



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **************************************************************************** */

