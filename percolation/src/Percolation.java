import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size; // size of the square grid
    private boolean[][] grid; // true = open, false = closed
    private int openSites; // amount of open sites
    private int top; // virtual top
    private int bottom; // virtual bottom
    private WeightedQuickUnionUF quf; // Start a QuickUnionUF object
    // What to add to find space behind and ahead of the current space
    private final int[] rowAdd = { 0, 0, -1, 1 };
    // What to add to find space above and below current space
    private final int[] colAdd = { -1, 1, 0, 0 };

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException(
                "n is less than or equal to zero.");
        this.size = n;
        this.top = n * n; // Set a 1D "location" for the virtual top
        this.bottom = n * n + 1; // Set 1D "location" for virtual bottom
        this.grid = new boolean[n][n]; // filled grid
        this.openSites = 0; // amount of open sites starts at 0

        // n-by-n plus virtual top + virtual bottom
        this.quf = new WeightedQuickUnionUF(n * n + 2);

        // Connect the top row to virtual top and bottom row to virtual bottom
        for (int i = 0; i < n; i++) {
            int connectPoint1 = oneDim(0, i);
            int connectPoint2 = oneDim(n - 1, i);
            quf.union(connectPoint1, top);
            quf.union(connectPoint2, bottom);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        // Check if in grid, if it's already open then break out of method
        if (row < 0 || row > size) throw new
                IllegalArgumentException("Point out of bounds.");
        if (col < 0 || col > size) throw new
                IllegalArgumentException("Point out of bounds.");
        if (isOpen(row, col)) return;

        // Open by setting to true and increase open site count
        grid[row][col] = true;
        openSites++;

        // Set first point to union equal to the one dim equivalency
        int point1 = oneDim(row, col);

        // Check if surrounding connecting spaces are open, if so, union
        for (int i = 0; i < colAdd.length; i++) {
            int rowNew = row + rowAdd[i];
            int colNew = col + colAdd[i];

            // Avoid throw exceptions by just adding a few if statements
            // to check out of bounds
            if (rowNew < 0 || colNew < 0 ||
                    rowNew >= size || colNew >= size) continue;

            // Connect the points
            if (isOpen(rowNew, colNew)) {
                int point2 = oneDim(rowNew, colNew);
                quf.union(point1, point2);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > size) throw new
                IllegalArgumentException("Point out of bounds.");
        if (col < 0 || col > size) throw new
                IllegalArgumentException("Point out of bounds.");
        return this.grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > size) throw new
                IllegalArgumentException("Point out of bounds.");
        if (col < 0 || col > size) throw new
                IllegalArgumentException("Point out of bounds.");
        if (isOpen(row, col)) {
            int point = oneDim(row, col);
            return quf.find(point) == quf.find(top);
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (numberOfOpenSites() == 0) return false;
        return quf.find(top) == quf.find(bottom);
    }

    // Convert row and column to one dimensional coordinate
    private int oneDim(int row, int col) {
        return size * row + col;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);

        // Test initial part whether closed, percolate, etc.
        if (perc.isOpen(0, 0)) StdOut.println("Open");
        else StdOut.println("Closed.");
        if (perc.isFull(0, 0)) StdOut.println("Full.");
        else StdOut.println("Not full.");
        if (perc.percolates()) StdOut.println("Percolates.");
        else StdOut.println("Does not percolate.");
        StdOut.println("Number of open sites: " + perc.numberOfOpenSites());

        // Open up a spot and check again
        perc.open(0, 0);
        if (perc.isOpen(0, 0)) StdOut.println("Open");
        else StdOut.println("Closed.");
        if (perc.isFull(0, 0)) StdOut.println("Full.");
        else StdOut.println("Not full.");
        if (perc.percolates()) StdOut.println("Percolates.");
        else StdOut.println("Does not percolate.");
        StdOut.println("Number of open sites: " + perc.numberOfOpenSites());

        // Open up a few more to allow it to percolate
        perc.open(0, 1);
        perc.open(1, 1);
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(4, 2);

        // Test out of bounds
        Percolation newperc = new Percolation(10);
        newperc.isOpen(11, 5);

        if (perc.isOpen(2, 1)) StdOut.println("Open");
        else StdOut.println("Closed.");
        if (perc.isFull(2, 1)) StdOut.println("Full.");
        else StdOut.println("Not full.");
        if (perc.percolates()) StdOut.println("Percolates.");
        else StdOut.println("Does not percolate.");
        StdOut.println("Number of open sites: " + perc.numberOfOpenSites());
    }
}

