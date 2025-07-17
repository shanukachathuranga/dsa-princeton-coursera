import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private Block[] grid;
    private int openSites = 0;
    int size;

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation pl = new Percolation(n);
        while (!StdIn.isEmpty()){
            pl.open(StdIn.readInt(), StdIn.readInt());
        }
        StdOut.println("Open sites: "+ pl.openSites);
        StdOut.println(pl.percolates() ? "percolates" : "do not percolates");
    }

    public Percolation(int n){
        this.size = n;
        this.grid = new Block[n*n+2];  //create nxn size block array
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if(i == 1){
                    grid[getBlockIndex(i, j)] = new Block(i, j, 0);
                }else if(i == n){
                    grid[getBlockIndex(i, j)] = new Block(i, j, (n*n)+1);
                } else {
                    grid[getBlockIndex(i, j)] = new Block(i, j, getBlockIndex(i, j));
                }
            }
        }
        // upper root block
        grid[0] = new Block(0, 0, 0);
        grid[0].size = n;
        // bottom root block
        grid[(n*n)+1] = new Block(n+1, 1, getBlockIndex(n+1,1));
        grid[(n*n)+1].size = n;
    }

    public void open(int row, int col){
        if (isFull(row, col)){
            grid[getBlockIndex(row, col)].fill = 0;
            openSites++;

            // check up
            if(row-1 >= 1 && isOpen(row-1, col)){
                union(getBlockIndex(row, col), getBlockIndex(row-1, col));
            }

            // check down
            if(row+1 <= size && isOpen(row+1, col)){
                union(getBlockIndex(row, col), getBlockIndex(row+1, col));
            }

            // check left
            if(col-1 >= 0 && isOpen(row, col-1)){
                union(getBlockIndex(row, col), getBlockIndex(row, col-1));
            }

            // check left
            if(col+1 <= size && isOpen(row, col+1)){
                union(getBlockIndex(row, col), getBlockIndex(row, col+1));
            }

        }

    }

    public boolean isOpen(int row, int col){
        return grid[getBlockIndex(row, col)].fill == 0;
    }

    public boolean isFull(int row, int col){
        return grid[getBlockIndex(row, col)].fill == 1;
    }

    public int numberOfOpenSites(){
        return openSites;
    }

    public boolean percolates(){
        if(size == 1 && isOpen(1, 1)){
            return true;
        }
        return root(0) == root(size*size+1);
    }

    //get the index of the block given the coordinates
    private int getBlockIndex(int row, int col){
        return ((row-1)*size) + col;
    }

    // get the coordinates of the block given the index
    private int[] getBlockLocation(int index){
        int[] coordinates = new int[2];
        int row;
        int col;
        int divisor = index/this.size;
        int modulus = index%this.size;
        if(modulus == 0){
            row = divisor;
            col = size;
        }else{
            row = divisor+1;
            col = modulus;
        }
        coordinates[0] = row;
        coordinates[1] = col;
        return coordinates;

    }

    // return the index of the root of the union tree
    private int root(int index){
        int root = index;
        while (root != grid[root].parent){
            grid[root].parent = grid[grid[root].parent].parent; // path compression
            root = grid[root].parent;
        }
        return root;
    }

    private void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        if(grid[pRoot].size >= grid[qRoot].size){
            grid[qRoot].parent = pRoot;
            grid[pRoot].size += grid[qRoot].size;
        }else {
            grid[pRoot].parent = qRoot;
            grid[qRoot].size += grid[pRoot].size;
        }
    }


}

class Block{
    int row;
    int col;
    int size;
    int fill; // block -> 1, open -> 0;
    int parent;
    public Block(int row, int col, int parent){
        this.row = row;
        this.col = col;
        this.parent = parent;
        this.size = 0;
        this.fill = 1;
    }
}



