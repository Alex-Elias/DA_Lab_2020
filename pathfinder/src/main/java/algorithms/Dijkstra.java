package algorithms;


import datastructures.Tuple;

import datastructures.Node;
import datastructures.NodeList;


import datastructures.NodePriorityQueue;



/**
 * An implementation of the Dijkstra's algorithm
 * the algorithm finds the shortest path between two points of a weighted graph
 * the graph must be in the form of a 2d integer array with ones representing obstacles and zeros representing open space
 * @author alex
 */

public class Dijkstra extends Algorithm{
    /**
     * the array which the current distance between start and a certain node on the graph
     */
    double[][] distance;
    /**
     * the array which stores if the current node has been processed or not
     */
    boolean[][] processed;
    
    /**
     * the minimum priority queue which is used in the algorithm
     */
    NodePriorityQueue queue;
    /**
     * the height of the maze
     */
    private int mazeHeight;
    /**
     * the length of the maze
     */
    private int mazeLength;
    
    
    
    /**
     * the class gets initialized with 2d array
     * takes a 2d array as an input as saves it to the maze variable
     * the 2d array initializes the superclass Algorithm
     * @param maze the 2d integer array with 1's representing obstacles and 0's representing open space
     */
    public Dijkstra(int[][] maze){
        super(maze);
        this.mazeHeight = maze.length;
        this.mazeLength = maze[0].length;
        
        
        
    }
    /**
     * the method that runs the Dijkstra's algorithm
     * the method finds the shortest path between the starting node and the destination node
     * @param origin a two integer tuple that contains the x and y values of the origin
     * @param the goal tuple containing the x and y values of the destination
     */
    public void runDijkstra(Tuple origin, Tuple goal){
        this.distance = new double[mazeHeight][mazeLength];
        this.processed = new boolean[mazeHeight][mazeLength];
        this.queue = new NodePriorityQueue();
        for (int i = 0; i < this.mazeHeight; i++){
            for (int j = 0; j < this.mazeLength; j++){
                this.distance[i][j] = Double.MAX_VALUE;
                this.processed[i][j] = false;
            }
            
        }
        
            this.distance[origin.getX()][origin.getY()] = 0;
            Node start = new Node(origin,0);
            start.setParent(start);
            this.queue.insert(start, 0);
            while (!queue.isEmpty()){
                Node current = queue.deleteMin();
                
                if(current.getX() == goal.getX() && current.getY() == goal.getY()){
                    super.setDestination(current);
                    super.setDistance(this.distance[current.getX()][current.getY()]);
                    
                    break;
                }
                if (!this.processed[current.getX()][current.getY()]){
                    this.processed[current.getX()][current.getY()] = true;
                    NodeList list = this.getNeighbors(current);
                    while (!list.isEmpty()){
                        Node next = list.remove();
                        
                        if(this.distance[next.getX()][next.getY()] > this.distance[current.getX()][current.getY()] + next.getWeight()){
                            this.distance[next.getX()][next.getY()] = this.distance[current.getX()][current.getY()] + next.getWeight();
                            
                            this.queue.insert(next, this.distance[next.getX()][next.getY()]);
                        }
                    }
                }
            }
    }
    
    
    
    
    
    
    
}
