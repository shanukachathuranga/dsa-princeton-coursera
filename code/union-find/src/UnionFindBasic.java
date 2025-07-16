import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindBasic {
    //input - 10 4 3 3 8 6 5 9 4 2 1 8 9 5 0 7 2 6 1 1 0 6 7
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UnionFind uf = new UnionFind(N);
        int count = 0;
        while (count < N){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            uf.union(p, q);
            count++;
        }

        StdOut.println(uf.connected(4, 8));
    }



}

class UnionFind{
    private int[] id;

    public UnionFind(int N){
        this.id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    boolean connected(int p, int q){
        return id[p] == id[q];
    }

    void union(int p, int q){
        int val = id[p];
        id[p] = q;
        for(int i = 0; i < id.length; i++){
            if(id[i] == val){
                id[i] = q;
            }
        }
    }
}
