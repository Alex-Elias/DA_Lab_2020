public class Main {
    
    public static void main(String[] args) {
        /**
         * my project finds the shortest path between two points on a weighted graph
         * the pathfinding algorithms that work are:
         * Dijkstra's
         * A* (A Star) - is slower than expected, I need to work on improving the implementation
         * Breath first search - which is not for weighted graphs so there is only 4 directions of movement
         * (does not function) Jump Point Search - I have not been able to implement it fully
         * GUI class has instructions on how to run the program
         * my project is lacking thorough test for the classes
         * 
         */

        
        GUI gui = new GUI();
        gui.launchGUI();
        
       // int[][] maze4x4 = {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
//        AStar a = new AStar(maze4x4);
//        JPS_Node start = new JPS_Node(new Tuple(0,0));
//        JPS_Node goal = new JPS_Node(new Tuple(0,3));
//        a.run_AStar(start, goal);
//        a.get_shortest_path(goal);
//        JPS jps = new JPS(maze4x4);
//        jps.run_JPS(start, goal);
//        System.out.println(jps.weight);
    }
    
}
