
import GUI.GUI;
import datastructures.Node;
import datastructures.PriorityQueue;
import datastructures.Tuple;
import datastructures.*;
import performanceTesting.Mergesort;
import performanceTesting.PerformanceTesting;

public class Main {
    
    public static void main(String[] args) {
        /**
         * my project finds the shortest path between two points on a weighted graph
         * Breath first search - which is not for weighted graphs so there is only 4 directions of movement
         * GUI class has instructions on how to run the program
         * my project is lacking thorough test for the classes
         * 
         */

        
        GUI gui = new GUI();
        gui.launchGUI();
//        long[] test = {1,2,3,4,3,2,1};
//        Mergesort M = new Mergesort();
//        long[] res = M.runMergesort(test);
//        for(int i = 0; i <res.length; i++){
//            System.out.println(res[i]);
//        }

//        PerformanceTesting t = new PerformanceTesting();
//        t.runPerformanceTests();
            
        
        
//        PriorityQueue q = new PriorityQueue();
//        double p = 15;
//        for(int i = 1; i<= 10; i++){
//            q.insert(new JPS_Node(new Tuple(0,i)), p-i);
//        }
//        while(!q.isEmpty()){
//            System.out.println(q.deleteMin().coordinates);
//        }
        
////        int[][] maze4x4 = {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
//        int[][] maze5x5 = {{0,0,0,1,0},{0,1,1,1,0},{0,0,0,0,0},{0,1,1,1,0},{0,0,0,0,0}};
////////        AStar a = new AStar(maze4x4);
//        JPS_Node start = new JPS_Node(new Tuple(0,4));
//        JPS_Node goal = new JPS_Node(new Tuple(0,2));
////////        a.run_AStar(start, goal);
////////        a.get_shortest_path(goal);
//        JPS jps = new JPS(maze5x5);
//        jps.run_JPS(start, goal);
//        System.out.println(jps.weight);
//        JPS_Node last = jps.goal;
//        while(last.parent.coordinates != last.coordinates){
//            System.out.println(last.coordinates);
//            last = last.parent;
//        }
    }
    
}
