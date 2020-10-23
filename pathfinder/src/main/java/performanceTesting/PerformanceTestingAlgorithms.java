package performanceTesting;
import algorithms.*;
import filereader.Filereader;
import datastructures.*;
import java.util.Arrays;
import java.util.Random;

/**
 * performance testing class for Dijkstra, A* and JPS algorithms
 * the class test the run times for the different algorithms
 * printing the class will print the average time, median and standard deviation in milliseconds
 * @author alex
 */
public class PerformanceTestingAlgorithms {
   /**
     * the maze that has a one pixel corridor width
     */ 
    private final Filereader m1 = new Filereader("Mazes/maze512-1-0.map");
    /**
     * the maze with an eight pixel corridor width
     */
    private final Filereader m8 = new Filereader("Mazes/maze512-8-0.map");
    /**
     * the maze with a thirty two pixel corridor width
     */
    private final Filereader m32 = new Filereader("Mazes/maze512-32-0.map");
    
    /**
     * the 2d array of the one pixel corridor maze
     */
    private int[][] maze1;
    /**
     * the 2d array of the eight pixel corridor maze
     */
    private int[][] maze8;
    /**
     * the 2d array of the thirty two pixel corridor maze
     */
    private int[][] maze32;
    
    /**
     * the tuple with the start of the maze coordinates
     */
    private final Tuple mazeStart = new Tuple(1,1);
    /**
     * the tuple with the end of the maze coordinates
     */
    private final Tuple mazeFinish = new Tuple(511,511);
    
    
    /**
     * Dijkstra's algorithm average run time array
     */
    private final double[] dijkPathfindingAvg = new double[3];
    /**
     * A* average run time array
     */
    private final double[] astarPathfindingAvg = new double[3];
    /**
     * JPS average run time array
     */
    private final double[] jpsPathfindingAvg = new double[3];
    /**
     * Dijkstra's algorithm standard deviation run time array
     */
    private final double[] dijkPathfindingStd = new double[3];
    /**
     * A* standard deviation run time array
     */
    private final double[] astarPathfindingStd = new double[3];
    /**
     * JPS deviation run time array
     */
    private final double[] jpsPathfindingStd = new double[3];
    /**
     * Dijkstra's algorithm median run time array
     */
    private final double[] dijkPathfindingMedian = new double[3];
    /**
     * A* median run time array
     */
    private final double[] astarPathfindingMedian = new double[3];
    /**
     * JPS median run time array
     */
    private final double[] jpsPathfindingMedian = new double[3];
    /**
     * initializes the class by storing the different mazes to the class variables maze1, maze8 and maze32
     */
    public PerformanceTestingAlgorithms(){
        this.maze1 = m1.returnArray();
        this.maze8 = m8.returnArray();
        this.maze32 = m32.returnArray();
    }
    /**
     * runs the performance tests for the different algorithms
     */
    public void runPerformanceTests(){

        testAllAlgorithms();
        
        
    }
    
   /**
    * runs Dijkstra's algorithm 101 times on a certain maze between the start and end points and returns a array with all the run times
    * @param maze the maze that Dijkstra's algorithm runs on 
    * @return the array with all the run times 
    */
    private long[] runDijkstra(int[][] maze){
        long[] dijkstraM1 = new long[101];
        Dijkstra dijk = new Dijkstra(maze);
        for(int i = 0; i< 101; i++){
            
            long start = System.nanoTime();
            dijk.runDijkstra(this.mazeStart, this.mazeFinish);
            long finish = System.nanoTime();
            dijkstraM1[i] = finish-start;
        }
        return dijkstraM1;
    }
    /**
     * runs A* 101 times on a certain maze between the start and end points and returns a array with all the run times
     * @param maze the maze that A* runs on
     * @return the array with all the run times
     */
    private long[] runAstar(int[][] maze){
        long[] aStarM1 = new long[101];
        AStar Astar = new AStar(maze);
        for(int i = 0; i< 101; i++){
            
            long start = System.nanoTime();
            Astar.runAStar(new Node(this.mazeStart), new Node(this.mazeFinish));
            long finish = System.nanoTime();
            aStarM1[i] = finish-start;
        }
        return aStarM1;
    }
    /**
     * runs JPS 101 times on a certain maze between the start and end points and returns a array with all the run times
     * @param maze the maze that JPS runs on
     * @return the array with all the run times
     */
    private long[] runJPS(int[][] maze){
       long[] jpsM1 = new long[101];
       JPS jps = new JPS(maze);
        for(int i = 0; i< 101; i++){
            
            long start = System.nanoTime();
            jps.runJPS(new Node(this.mazeStart), new Node(this.mazeFinish));
            long finish = System.nanoTime();
            jpsM1[i] = finish-start;
        }
        return jpsM1; 
    }
    
    /**
     * tests all the algorithms and stores the different statistics to the correct array
     */
    private void testAllAlgorithms(){
        long[] dijk1 = this.runDijkstra(this.maze1);
        long[] astar1 = this.runAstar(this.maze1);
        long[] jps1 = this.runJPS(this.maze1);
        Arrays.sort(dijk1);
        Arrays.sort(astar1);
        Arrays.sort(jps1);
        
        this.dijkPathfindingMedian[0] = dijk1[50] / 1000000;
        this.astarPathfindingMedian[0] = astar1[50] / 1000000;
        this.jpsPathfindingMedian[0] = jps1[50] / 1000000;
        
        this.dijkPathfindingAvg[0] = getAverage(dijk1);
        this.astarPathfindingAvg[0] = getAverage(astar1);
        this.jpsPathfindingAvg[0] = getAverage(jps1);
        
        this.dijkPathfindingStd[0] = getStd(dijk1, this.dijkPathfindingAvg[0]);
        this.astarPathfindingStd[0] = getStd(astar1, this.astarPathfindingAvg[0]);
        this.jpsPathfindingStd[0] = getStd(jps1,this.jpsPathfindingAvg[0]);
        
        long[] dijk8 = this.runDijkstra(this.maze8);
        long[] astar8 = this.runAstar(this.maze8);
        long[] jps8 = this.runJPS(this.maze8);
        Arrays.sort(dijk8);
        Arrays.sort(astar8);
        Arrays.sort(jps8);
        
        this.dijkPathfindingMedian[1] = dijk8[50] / 1000000;
        this.astarPathfindingMedian[1] = astar8[50] / 1000000;
        this.jpsPathfindingMedian[1] = jps8[50] / 1000000;
        
        this.dijkPathfindingAvg[1] = getAverage(dijk8);
        this.astarPathfindingAvg[1] = getAverage(astar8);
        this.jpsPathfindingAvg[1] = getAverage(jps8);
        
        this.dijkPathfindingStd[1] = getStd(dijk8, this.dijkPathfindingAvg[1]);
        this.astarPathfindingStd[1] = getStd(astar8, this.astarPathfindingAvg[1]);
        this.jpsPathfindingStd[1] = getStd(jps8,this.jpsPathfindingAvg[1]);
        
        long[] dijk32 = this.runDijkstra(this.maze32);
        long[] astar32 = this.runAstar(this.maze32);
        long[] jps32 = this.runJPS(this.maze32);
        
        Arrays.sort(dijk32);
        Arrays.sort(astar32);
        Arrays.sort(jps32);
        
        this.dijkPathfindingMedian[2] = dijk32[50] / 1000000;
        this.astarPathfindingMedian[2] = astar32[50] / 1000000;
        this.jpsPathfindingMedian[2] = jps32[50] / 1000000;
        
        this.dijkPathfindingAvg[2] = getAverage(dijk32);
        this.astarPathfindingAvg[2] = getAverage(astar32);
        this.jpsPathfindingAvg[2] = getAverage(jps32);
        
        this.dijkPathfindingStd[2] = getStd(dijk32, this.dijkPathfindingAvg[2]);
        this.astarPathfindingStd[2] = getStd(astar32, this.astarPathfindingAvg[2]);
        this.jpsPathfindingStd[2] = getStd(jps32, this.jpsPathfindingAvg[2]);
        
        
    }
    /**
     * toString override which returns the stats as a string
     * @return the string with all the stats
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n Dijkstra's algorithm shortest path times:\n");
        appendResults(sb, this.dijkPathfindingAvg, this.dijkPathfindingMedian, this.dijkPathfindingStd);
        
        sb.append("\n A* search algorithm shortest path times:\n");
        appendResults(sb, this.astarPathfindingAvg, this.astarPathfindingMedian, this.astarPathfindingStd);
        
        sb.append("\n Jump point search shortest path times:\n");
        appendResults(sb, this.jpsPathfindingAvg, this.jpsPathfindingMedian, this.jpsPathfindingStd);
        
        return sb.toString();
    }
    
    /**
     * appends the results to the string builder and returns the newly appended string builder
     * @param sb the string builder which is appended to
     * @param array the average run time array
     * @param median the median run time array
     * @param std the standard deviation run time array
     */
    private void appendResults(StringBuilder sb, double[] array, double[] median, double[] std){
        sb.append("one pixel width corridor:");
        sb.append(" Avg:" + array[0] / 1000000 + "ms, Median: " + median[0] + "ms, std: " + std[0] / 1000000 + "ms\n");
        sb.append("eight pixel width corridor:");
        sb.append("Avg: " + array[1] / 1000000 + "ms, Median: " + median[1] + "ms, std: " + std[1] / 1000000 + "ms\n");
        sb.append("32 pixel width corridor:");
        sb.append("Avg: " + array[2] / 1000000 + "ms, Median: " + median[2] + "ms, std: " + std[2] / 1000000 + "ms\n");
        
        sb.append("\n");
    }
    /**
     * returns the standard deviation of a certain array
     * @param times the array which the std will be found
     * @param mean the mean of the values of the array
     * @return the standard deviation of the values
     */
    private double getStd(long[] times, double mean){
        double s = 0;
        for (long time : times){
            s += Math.pow(time - mean , 2);
        }
        
        return Math.sqrt(s / (times.length - 1));
    }
    /**
     * returns the average value of an array
     * @param times the array which the average is found
     * @return the average value of the array
     */
    private double getAverage(long[] times){
        double s = 0;
        for (long time : times){
            s += time;
        }
        return s / times.length;
    }
    
}
