public class Main {
    
    public static void main(String[] args) {
        // TODO code application logic here

//        Filereader f = new Filereader("/home/alex/Downloads/maze512-32-0.map");
//        GUI gui = new GUI();
//        gui.launchGUI();
        
        int[][] maze4x4 = {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
//        AStar a = new AStar(maze4x4);
        JPS_Node start = new JPS_Node(new Tuple(0,0));
        JPS_Node goal = new JPS_Node(new Tuple(0,3));
//        a.run_AStar(start, goal);
//        a.get_shortest_path(goal);
        JPS jps = new JPS(maze4x4);
        jps.run_JPS(start, goal);
        System.out.println(jps.weight);
    }
    
}
