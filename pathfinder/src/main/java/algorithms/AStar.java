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
        
        
        this.visited= new boolean[maze.length][maze[0].length];
        
        start.setF(0);
        start.setParent(start);
        this.queue.insert(start,start.getF());
        
        
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = this.Inf;
                this.visited[i][j] = false;
                
            }
            
        }
        this.distance[start.getX()][start.getY()] = 0;
        

        
        while(!this.queue.isEmpty()){
            
            
            Node current = this.queue.deleteMin();
            if(!this.visited[current.getX()][current.getY()]){
                this.visited[current.getX()][current.getY()] = true;
            
                if(current.getX() == goal.getX() && current.getY() == goal.getY()){
                    super.setDestination(current);
                    super.setDistance(this.distance[current.getX()][current.getY()]);
                    break;
                }
            
            
                NodeList list = getNeighbors(current);
                while(!list.isEmpty()){
                    Node next = list.remove();
                    double new_weight = this.distance[current.getX()][current.getY()] + next.getWeight();

                    if (new_weight < this.distance[next.getX()][next.getY()]){
                        this.distance[next.getX()][next.getY()]= new_weight;
                        double priority = new_weight + heuristic(next,goal);
                        next.setF(priority);
                        this.queue.insert(next,next.getF());
                        
                    }

                }
            }
        }
    }
    
    
    
    
    
    
    
    
}
