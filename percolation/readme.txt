/* *****************************************************************************
 *  Operating system: Windows 10
 *  Compiler: Javac
 *  Text editor / IDE: IntelliJ IDEA Ultimate 2021.3.3
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II: No
 *
 *  Hours to complete assignment (optional): 4
 *
 **************************************************************************** */

Programming Assignment 1: Percolation


/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
    private static int size - The total n*n size of the square grid
    private boolean[][] grid - Square grid that would tell whether it was open
                                "true" or closed, "false"
    private int openSites - The amount of total open sites in the percolation
                            object
    private int top - an integer to represent the virtual top
    private int bottom - an integer to represent the virtual bottom
    private WeightedQuickUnionUF quf - an object of the weighted quick union
                                        to use for union and find calls
    private final int[] rowAdd = { 0, 0, -1, 1 } - what I would add and subtract
                   from around the selected space on an open call, because it
                   would have to check whether the spaces left and right to it
                   were also open
    private final int[] colAdd = { -1, 1, 0, 0 } - what I would add and subtract
                      from around the selected space on an open call, because it
                      would have to check whether the spaces up and down to it
                      were also open

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): WeightedQuickUnion was used to do the union method to "open" the spot
        which would join it with any spaces around it that were also open
isOpen(): All I did here was just return the boolean value in the 2D grid at
          that spot
isFull(): Here I just used the find function of WeightedQuickUnion to see
          whether the root of the point being checked is the samne as the root
          as the virtual top.
numberOfOpenSites(): All I did here was keep a private int counter that would
                     increase whenever open() successfully ran, and returned it
percolates(): To check whether it pecolates, I checked whether the virtual top
              and the virtual bottom have the same root. I used the find method
              from WeightedQuickUnion

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
100         0.0840
200         0.4000
400         2.767
800         24.892
1000        68.723
997         64.972
996         53.556


/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I originally was doubling n every time, but then ran 1000 to make sure, and it
was over a minute. Therefore, I tried going down slowly from 1000 to see where I
would land, and 997 was still over a minute, but 996 was not.

/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
200	        0.175
400	        0.743
800	        3.237
1600        20.137
2000        40.039
2400        69.298
2300        58.253
2350        61.158
2345        59.984

Same sort of strategy above, the highest N I get to is 2345.

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
