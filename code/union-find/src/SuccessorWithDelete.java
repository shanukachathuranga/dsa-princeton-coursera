import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class SuccessorWithDelete {
    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        // swd.delete(1);
        // swd.delete(2);
        // swd.delete(4);
        // swd.delete(5);
        // swd.delete(6);
        // swd.delete(7);
        // swd.delete(8);
        // swd.delete(9);
        swd.successor(1);
        swd.successor(3);
    }
    int[] id;
    int[] left;
    public SuccessorWithDelete(int N){
        this.id = new int[N];
        this.left = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i+1;
            if(i-1 < 0){
                left[i] = i;
            }else {
                left[i] = i - 1;
            }

        }
        StdOut.println(Arrays.toString(id));
        StdOut.println(Arrays.toString(left));
    }

    public void delete(int i){
        if(i == id[i]){
            StdOut.println(i+" already deleted or not exist!");
        }else{
            if(id[i] > id.length-1){
                id[left[i]] = id[i];
                return;
            }
            id[left[i]] = id[i];
            left[id[i]] = left[i];
            id[i] = i;
        }
    }

    public void successor(int i){
        if (i == id[i] || id[i] >= id.length){
            StdOut.println("successor of "+i+" do not exist");
            return;
        }
        StdOut.println("Successor of "+i+" is: "+ id[i]);
    }

}
