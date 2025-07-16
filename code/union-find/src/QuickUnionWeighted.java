import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class QuickUnionWeighted {
    public static void main(String[] args) {
        // 10 4 3 3 8 6 5 9 4 2 1 8 9 5 0 7 2 6 1 1 0 6 7
        int N = StdIn.readInt();
        QuickUnionWeighted quw = new QuickUnionWeighted(N);
        while (!StdIn.isEmpty()){
            quw.union(StdIn.readInt(), StdIn.readInt());
        }
        StdOut.println(quw.connected(4, 8));
        StdOut.println(Arrays.toString(quw.index));
        StdOut.println(Arrays.toString(quw.id));
        StdOut.println(Arrays.toString(quw.size));
    }
    int[] index;
    int[] id;
    int[] size;
    public QuickUnionWeighted(int N){
        this.id = new int[N];
        this.index = new int[N];
        this.size = new int[N];
        for(int i = 0; i < N; i++){
            this.index[i] = i;
            this.id[i] = i;
            this.size[i] = 1;
        }
    }

    private int root(int i){
        int root = i;
        while(root != id[root]){
            id[root] = id[id[root]];   // path compression
            root = id[root];
        }
        return root;
    }

    public void union(int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        if(size[pRoot] >= size[qRoot]){
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

}
