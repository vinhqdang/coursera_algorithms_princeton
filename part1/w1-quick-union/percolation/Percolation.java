import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
	private WeightedQuickUnionUF sites;
	private boolean [] opened;
	private int N;

    public Percolation(int N)  {          // create N-by-N grid, with all sites blocked
         if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
         }
        this.N = N;
        sites = new WeightedQuickUnionUF(N * N + 2);
        opened = new boolean [N * N];
        Arrays.fill (opened, false);
    }

	private int getSiteID(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new java.lang.IndexOutOfBoundsException ();
		}
		else return (i - 1) * N + j;	//site id counts from 1
	}

   	public void open(int i, int j)  {        // open site (row i, column j) if it is not open already

   		int site_id = getSiteID(i, j);
   		opened [site_id - 1] = true;

   		// merges with neighbors site
   		// up
   		if (i > 1) {
   			if (isOpen(i - 1, j)) {
   				sites.union (site_id, getSiteID(i - 1, j));
   			}
   		}

   		// down
   		if (i < N) {
   			if (isOpen(i + 1, j)) {
   				sites.union (site_id, getSiteID (i + 1, j));
   			}
   		}

   		// left
   		if (j > 1) {
   			if (isOpen (i, j - 1)) {
   				sites.union (site_id, getSiteID (i, j - 1));
   			}
   		}

   		// right
   		if (j < N) {
   			if (isOpen (i, j + 1)) {
   				sites.union (site_id, getSiteID (i, j + 1));
   			}
   		}

   		// connect to virtual top site
   		if (i == 1) {
   			sites.union (site_id, 0);
   		}

   		// connect to virtual bottom site
   		if (i == N) {
   			sites.union (site_id, N * N + 1);
   		}
   	}

   	public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
   		int site_id = getSiteID (i, j);
   		return opened [site_id - 1];
   	}

   	public boolean isFull(int i, int j) {     // is site (row i, column j) full?
   		int site_id = getSiteID (i, j);
   		return sites.connected (site_id, 0);
   	}

   	public boolean percolates() {         // does the system percolate?
   		return sites.connected (0, N * N + 1);
   	}

   	public static void main(String[] args) {  // test client (optional)
        Percolation p = new Percolation (100);
        int j = 0;

        while (p.percolates () == false) {
            j++;
            while (true) {
                int row = StdRandom.uniform (1, 100 + 1);
                int col = StdRandom.uniform (1, 100 + 1);
                if (p.isOpen (row, col) == false) {
                    p.open (row, col);
                    break;
                }
            }
        }
        System.out.println (j);
    }
}