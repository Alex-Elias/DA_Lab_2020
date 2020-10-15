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
    
    private Tuple mazeStart = new Tuple(1,1);
    private Tuple mazeFinish = new Tuple(511,511);
    Mergesort mergesort = new Mergesort();
    
    
    public PerformanceTesting(){
        this.maze1 = m1.returnArray();
        this.maze8 = m8.returnArray();
        this.maze32 = m32.returnArray();
    }
    
    public void runPerformanceTests(){
//        this.testsM1();
//        this.testM8();
//        this.testM32();
        this.testAll();
        
    }
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
    private long[] runAstar(int[][] maze){
        long[] aStarM1 = new long[101];
        AStar Astar = new AStar(maze);
        for(int i = 0; i< 101; i++){
            
            long start = System.nanoTime();
            Astar.run_AStar(new Node(this.mazeStart), new Node(this.mazeFinish));
            long finish = System.nanoTime();
            aStarM1[i] = finish-start;
        }
        return aStarM1;
    }
    private long[] runJPS(int[][] maze){
       long[] jpsM1 = new long[101];
       JPS jps = new JPS(maze);
        for(int i = 0; i< 101; i++){
            
            long start = System.nanoTime();
            jps.run_JPS(new Node(this.mazeStart), new Node(this.mazeFinish));
            long finish = System.nanoTime();
            jpsM1[i] = finish-start;
        }
        return jpsM1; 
    }
    private void testsM1(){
        long[] dijk = this.runDijkstra(this.maze1);
        long[] astar = this.runAstar(this.maze1);
        long[] jps = this.runJPS(this.maze1);
        dijk = this.mergesort.runMergesort(dijk);
        astar = this.mergesort.runMergesort(astar);
        jps = this.mergesort.runMergesort(jps);
        System.out.println("Dijkstra maze one pixel median time in ms: " + dijk[50]/1000000);
        System.out.println("A Star maze one pixel median time in ms: " + astar[50]/1000000);
        System.out.println("Jump Point Search one pixel median time in ms: " + jps[50]/1000000);
        
        
    }
    private void testM8(){
        long[] dijk = this.runDijkstra(this.maze8);
        long[] astar = this.runAstar(this.maze8);
        long[] jps = this.runJPS(this.maze8);
        dijk = this.mergesort.runMergesort(dijk);
        astar = this.mergesort.runMergesort(astar);
        jps = this.mergesort.runMergesort(jps);
        System.out.println("Dijkstra maze eight pixel median time in ms: " + dijk[50]/1000000);
        System.out.println("A Star maze eight pixel median time in ms: " + astar[50]/1000000);
        System.out.println("Jump Point Search eight pixel median time in ms: " + jps[50]/1000000);
    }
    private void testM32(){
        long[] dijk = this.runDijkstra(this.maze32);
        long[] astar = this.runAstar(this.maze32);
        long[] jps = this.runJPS(this.maze32);
        dijk = this.mergesort.runMergesort(dijk);
        astar = this.mergesort.runMergesort(astar);
        jps = this.mergesort.runMergesort(jps);
        System.out.println("Dijkstra maze 32 pixel median time in ms: " + dijk[50]/1000000);
        System.out.println("A Star maze 32 pixel median time in ms: " + astar[50]/1000000);
        System.out.println("Jump Point Search 32 pixel median time in ms: " + jps[50]/1000000);
    }
    
    private void testAll(){
        long[] dijk1 = this.runDijkstra(this.maze1);
        long[] astar1 = this.runAstar(this.maze1);
        long[] jps1 = this.runJPS(this.maze1);
        dijk1 = this.mergesort.runMergesort(dijk1);
        astar1 = this.mergesort.runMergesort(astar1);
        jps1 = this.mergesort.runMergesort(jps1);
        long[] dijk8 = this.runDijkstra(this.maze8);
        long[] astar8 = this.runAstar(this.maze8);
        long[] jps8 = this.runJPS(this.maze8);
        dijk8 = this.mergesort.runMergesort(dijk8);
        astar8 = this.mergesort.runMergesort(astar8);
        jps8 = this.mergesort.runMergesort(jps8);
        long[] dijk32 = this.runDijkstra(this.maze32);
        long[] astar32 = this.runAstar(this.maze32);
        long[] jps32 = this.runJPS(this.maze32);
        dijk32 = this.mergesort.runMergesort(dijk32);
        astar32 = this.mergesort.runMergesort(astar32);
        jps32 = this.mergesort.runMergesort(jps32);
        System.out.println("Results in ms:");
        System.out.println("              one pixel:          eight pixels:            32 pixels:");
        System.out.println("dijkstra:     " + dijk1[50]/1000000 + "                  " + dijk8[50]/1000000 + "                       " + dijk32[50]/1000000);
        System.out.println("A Star:       " + astar1[50]/1000000 + "                  " + astar8[50]/1000000 + "                       " + astar32[50]/1000000);
        System.out.println("JPS:          " + jps1[50]/1000000 + "                  " + jps8[50]/1000000 + "                       " + jps32[50]/1000000);
    }
    
    
}
