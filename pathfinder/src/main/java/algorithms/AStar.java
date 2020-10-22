package algorithms;


import datastructures.Node;
import datastructures.Tuple;
import datastructures.NodeList;

import datastructures.NodePriorityQueue;
/**
 * A Star algorithm to find the shortest path in a weighted graph
 * basically Dijkstra's but has a heuristic function
 * returns the shortest path and distance of the path
 * @author alex
 */
public class AStar extends Algorithm{
    
    /**
     * the array which stores the current distance from the starting node to another node
     */
    public double[][] distance;
    /**
     * the minimum priority queue that is used in A* search algorithm
     */
    NodePriorityQueue queue;
    /**
     * the maximum value for the double data type which is used to initialize the distance variable
     */
    private double Inf = Double.MAX_VALUE;
    /**
     * value of root two
     */
    private double RootTwo = 1.4142135;
    /**
     * the height of the maze
     */
    private int mazeHeight;
    /**
     * the length of the maze
     */
    private int mazeLength;
    
    
    boolean[][] visited;
    /**
     * initializes the A Star class
     * takes a 2d array as an input as saves it to the maze variable
     * @param maze the 2d integer array where ones are obstacles and zeros are free spaces
     */
    public AStar(int[][] maze){
        super(maze);
        this.mazeHeight = maze.length;
        this.mazeLength = maze[0].length;
        
        
        
    }
    /**
     * runs the A Star algorithm 
     * 
     * takes the starting node and the goal node as inputs then finds the shortest path between them 
     * @param start the starting node
     * @param goal the goal node
     */
    public void runAStar(Node start, Node goal){
        this.distance = new double[mazeHeight][mazeLength];
        this.queue = new NodePriorityQueue();
        
        
        this.visited= new boolean[mazeHeight][mazeLength];
        
        start.setF(0);
        start.setParent(start); //sets its parent to itself 
        this.queue.insert(start,start.getF()); // adds the start node to the queue with the variable F as the priority 
        
        
        for (int i = 0; i < this.mazeHeight; i++){
            for (int j = 0; j < this.mazeLength; j++){
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
                    double newWeight = this.distance[current.getX()][current.getY()] + next.getWeight();

                    if (newWeight < this.distance[next.getX()][next.getY()]){
                        this.distance[next.getX()][next.getY()]= newWeight;
                        double priority = newWeight + heuristic(next,goal);
                        next.setF(priority);
                        this.queue.insert(next,next.getF());
                        
                    }

                }
            }
        }
    }
    
    
    
    
    
    
    
    
}
