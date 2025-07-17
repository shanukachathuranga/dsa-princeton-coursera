import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SocialNetworkConnectivity {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        SocialNetworkConnectivity quw = new SocialNetworkConnectivity(N);
        while (!StdIn.isEmpty()){
            quw.union(StdIn.readInt(), StdIn.readInt(), StdIn.readInt());
        }
    }

    int[] id;
    int[] size;

    public SocialNetworkConnectivity(int members){
        this.id = new int[members];
        this.size = new int[members];
        for(int i = 0; i < members; i++){
            id[i] = i;
            size[i] = 1;
        }

    }

    private int root(int member){
        int root = member;
        while(root != id[root]){
            id[root] = id[id[root]];   // path compression
            root = id[root];
        }
        return root;
    }

    public void union(int time, int p, int q){
        int pRoot = root(p);
        int qRoot = root(q);
        if(size[pRoot] >= size[qRoot]){
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }else {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        }
        if(checkConnectivity()){
            StdOut.println("All connected, Time: "+ time);
            StdOut.println(Arrays.toString(id));
        }
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    private boolean checkConnectivity(){
        for (int i = 1; i < id.length; i++){
            if(!connected(0, i)) {
                return false;
            }
        }
        return true;
    }

}
