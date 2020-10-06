package performanceTesting;
import algorithms.*;
import filereader.Filereader;
import datastructures.*;

/**
 *
 * @author alex
 */
public class PerformanceTesting {
   
    private Filereader m1 = new Filereader("Mazes/maze512-1-0.map");
    private Filereader m8 = new Filereader("Mazes/maze512-8-0.map");
    private Filereader m32 = new Filereader("Mazes/maze512-32-0.map");
    private Filereader r10 = new Filereader("Mazes/random512-10-0.map");
    private Filereader r40 = new Filereader("Mazes/random512-40-7.map");
    
    private int[][] maze1;
    private int[][] maze8;
    private int[][] maze32;
    private int[][] random10;
    private int[][] random40;
    
    private Tuple maze1Start = new Tuple(1,1);
    private Tuple maze1Finish = new Tuple(511,511);
    Mergesort mergesort = new Mergesort();
    
    
    public PerformanceTesting(){
        this.maze1 = m1.returnArray();
        this.maze8 = m8.returnArray();
        this.maze32 = m32.returnArray();
    }
    
    public void runPerformanceTests(){
        this.testsM1();
        
    }
    private long[] runDijkstraM1(){
        long[] dijkstraM1 = new long[11];
        for(int i = 0; i< 11; i++){
            Dijkstra dijk = new Dijkstra(this.maze1);
            long start = System.nanoTime();
            dijk.runDijkstra(this.maze1Start, this.maze1Finish);
            long finish = System.nanoTime();
            dijkstraM1[i] = finish-start;
        }
        return dijkstraM1;
    }
    private long[] runAstarM1(){
        long[] aStarM1 = new long[11];
        for(int i = 0; i< 11; i++){
            AStar Astar = new AStar(this.maze1);
            long start = System.nanoTime();
            Astar.run_AStar(new Node(this.maze1Start), new Node(this.maze1Finish));
            long finish = System.nanoTime();
            aStarM1[i] = finish-start;
        }
        return aStarM1;
    }
    private long[] runJPSm1(){
       long[] jpsM1 = new long[11];
        for(int i = 0; i< 11; i++){
            JPS jps = new JPS(this.maze1);
            long start = System.nanoTime();
            jps.run_JPS(new Node(this.maze1Start), new Node(this.maze1Finish));
            long finish = System.nanoTime();
            jpsM1[i] = finish-start;
        }
        return jpsM1; 
    }
    private void testsM1(){
        long[] dijk = this.runJPSm1();
        long[] astar = this.runAstarM1();
        long[] jps = this.runJPSm1();
        dijk = this.mergesort.runMergesort(dijk);
        astar = this.mergesort.runMergesort(astar);
        jps = this.mergesort.runMergesort(jps);
        System.out.println("Dijkstra maze one pixel median time in ms: " + dijk[5]/1000000);
        System.out.println("A Star maze one pixel median time in ms: " + astar[5]/1000000);
        System.out.println("Jump Point Search one pixel median time in ms: " + jps[5]/1000000);
        
        
    }
    
    
}
