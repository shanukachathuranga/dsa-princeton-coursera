import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionFindCanonical {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UnionFindCanonical ufc = new UnionFindCanonical(N);
        while (!StdIn.isEmpty()){
            ufc.union(StdIn.readInt(), StdIn.readInt());
        }
        ufc.find(1);
        ufc.find(0);
        ufc.find(5);
    }

    int[] id;
    int[] size;
    int[] max;

    public UnionFindCanonical(int N){
        this.id = new int[N];
        this.size = new int[N];
        this.max = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    private int root(int i){
        int root = i;
        while(root != id[root]){
            id[root] = id[id[root]];
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
            if(max[pRoot] <= max[qRoot]){
                max[pRoot] = max[qRoot];
            }
        }else{
            id[pRoot] = qRoot;
            size[qRoot] = size[pRoot];
            if(max[qRoot] <= max[pRoot]){
                max[qRoot] = max[pRoot];
            }
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void find(int i){
        StdOut.println("max of "+i+" is: "+max[root(i)]);
    }

}
