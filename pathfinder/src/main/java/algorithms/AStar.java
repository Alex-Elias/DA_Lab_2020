package algorithms;


import datastructures.Node;
import datastructures.Tuple;
import datastructures.NodeList;

import datastructures.PriorityQueue;
/**
 * A Star algorithm to find the shortest path in a weighted graph
 * basically Dijkstra's but has a heuristic function
 * @author alex
 */
public class AStar extends Algorithm{

    int[][] maze;
    public double[][] distance;
    PriorityQueue queue;
    public Node[][] predecessor;
    private double Inf = 2147483647;
    private double RootTwo = 1.4142135;
    
    
    //  test
    boolean[][] visited;
    /**
     * initialized the A Star class
     * @param maze the 2d integer array where ones are obstacles and zeros are free spaces
     */
    public AStar(int[][] maze){
        super(maze);
        this.maze=maze;
        
        
    }
    /**
     * runs the A Star algorithm 
     * @param start the starting node
     * @param goal the goal node
     */
    public void run_AStar(Node start, Node goal){
        this.distance = new double[maze.length][maze[0].length];
        this.queue = new PriorityQueue();
        this.predecessor = new Node[maze.length][maze[0].length];
        
        this.visited= new boolean[maze.length][maze[0].length];
        start.f=0;
        start.parent = start;
        this.queue.insert(start,start.f);
        this.predecessor[start.coordinates.x][start.coordinates.y] = start;
        
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = this.Inf;
                this.visited[i][j] = false;
                
            }
            
        }
        this.distance[start.coordinates.x][start.coordinates.y] = 0;
        

        
        while(!this.queue.isEmpty()){
            
            
            Node current = this.queue.deleteMin();
            if(!this.visited[current.coordinates.x][current.coordinates.y]){
                this.visited[current.coordinates.x][current.coordinates.y] = true;
            
                if(current.coordinates.x == goal.coordinates.x && current.coordinates.y == goal.coordinates.y){
                    super.setDestination(current);
                    break;
                }
            
            
                NodeList list = getNeighbors(current);
                while(!list.isEmpty()){
                    Node next = list.remove();
                    double new_weight = this.distance[current.coordinates.x][current.coordinates.y] + next.weight;

                    if (new_weight < this.distance[next.coordinates.x][next.coordinates.y]){
                        this.distance[next.coordinates.x][next.coordinates.y]= new_weight;
                        double priority = new_weight + heuristic(next,goal);
                        next.f = priority;
                        this.queue.insert(next,next.f);
                        this.predecessor[next.coordinates.x][next.coordinates.y] = current;
                    }

                }
            }
        }
    }
    
    
    
    
    
    
    
    
}
