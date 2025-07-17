import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int gridSize;
    private int trials;
    private double[] xData;
    private double xBar = -1;
    private double s = -1;
    private double confLo;
    private double confHi;

    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <=0){
            throw new IllegalArgumentException();
        }
        this.xData = new double[trials];
        this.gridSize = n;
        this.trials = trials;



        int row;
        int col;

        for(int i = 0; i < trials; i++){
            Percolation pc = new Percolation(n);
            while (!pc.percolates()){
                row = StdRandom.uniformInt(1, n+1);
                col = StdRandom.uniformInt(1, n+1);
                // StdOut.println(row+", "+col);
                pc.open(row, col);
            }
            // StdOut.println("------------------------------------------------"+i);
            xData[i] = (double) pc.numberOfOpenSites() /(n*n);
        }

    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());
    }

    public double mean(){
        xBar = StdStats.mean(xData);
        return xBar;
    }

    public double stddev(){
        s = StdStats.stddev(xData);
        return s;
    }

    public double confidenceLo(){
        if(xBar == -1){
            this.mean();
        }
        if(s == -1){
            this.stddev();
        }
        confLo = xBar - (1.96*s/Math.sqrt(trials));
        return confLo;
    }

    public double confidenceHi(){
        if(xBar == -1){
            this.mean();
        }
        if(s == -1){
            this.stddev();
        }
        confHi = xBar + (1.96*s/Math.sqrt(trials));
        return confHi;
    }

}
