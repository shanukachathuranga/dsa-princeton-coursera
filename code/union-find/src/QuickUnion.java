import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class QuickUnion {
    public static void main(String[] args) {
        //input - 10 4 3 3 8 6 5 9 4 2 1 8 9 5 0 7 2 6 1 1 0 6 7
        int N = StdIn.readInt();
        QuickUnion qu = new QuickUnion(N);
        while (!StdIn.isEmpty()){
            qu.union(StdIn.readInt(), StdIn.readInt());
        }

        StdOut.println(qu.connected(4, 8));
        StdOut.println(Arrays.toString(qu.id));

    }
    int[] id;
    public QuickUnion(int N){
        this.id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    private int root(int i){
        int root = i;
        while (root != id[root]){
            root = id[root];
        }
        return root;
    }

    public void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        id[pRoot] = qRoot;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

}


