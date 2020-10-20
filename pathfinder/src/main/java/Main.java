
import GUI.GUI;
import datastructures.Node;
import datastructures.NodePriorityQueue;
import datastructures.Tuple;
import datastructures.*;
import filereader.Filereader;
import performanceTesting.Mergesort;
import performanceTesting.PerformanceTestingAlgorithms;
import performanceTesting.PerformanceTestingDatastructures;

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



//        
//      uncomment to run the performance tests
//      it is recomended to comment the gui so it does not run
//        PerformanceTestingAlgorithms pta = new PerformanceTestingAlgorithms();
//        pta.runPerformanceTests();
//        System.out.println(pta);
//        PerformanceTestingDatastructures ptd = new PerformanceTestingDatastructures();
//        ptd.runPerformanceTesting();
//        System.out.println(ptd);
            
        
        

    }
    
}
