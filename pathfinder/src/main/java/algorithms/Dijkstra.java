package algorithms;


import datastructures.Tuple;

import datastructures.Node;
import datastructures.NodeList;


import datastructures.PriorityQueue;
import datastructures.TupleList;



/**
 * An implementation of the Dijkstra's algorithm
 * the algorithm finds the shortest path between to points of a weighted graph
 * the graph must be in the form of a 2d integer array with 1's representing obstacles and 0's representing open space
 * @author alex
 */

public class Dijkstra extends Algorithm{
    double[][] distance;
    boolean[][] processed;
    int[][] maze;
    PriorityQueue queue;
    Tuple[][] predecessor;
    Node destination;
    /**
     * the class gets initialized with 2d array
     * @param maze the 2d integer array with 1's representing obstacles and 0's representing open space
     */
    public Dijkstra(int[][] maze){
        super(maze);
        this.maze = maze;
        
        
    }
    /**
     * the method that runs the Dijkstra's algorithm
     * @param origin a two integer tuple that contains the x and y values of the origin the first value represents the row number and the second value represents the number of columns starting from 0
     */
    public void runDijkstra(Tuple origin, Tuple goal){
        this.distance = new double[maze.length][maze[0].length];
        this.processed = new boolean[maze.length][maze[0].length];
        this.queue = new PriorityQueue();
        this.predecessor = new Tuple[maze.length][maze[0].length];
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = 2147483647;
                this.processed[i][j] = false;
            }
            
        }
        
            this.distance[origin.x][origin.y]= 0;
            this.predecessor[origin.x][origin.y]= origin;
            Node start = new Node(origin,0);
            start.parent = start;
            this.queue.insert(start,0);
            while(!queue.isEmpty()){
                Node next = queue.deleteMin();
                Tuple u = next.coordinates;
                if(u.x == goal.x && u.y == goal.y){
                    super.setDestination(next);
                    this.destination = next;
                    break;
                }
                if(!this.processed[u.x][u.y]){
                    this.processed[u.x][u.y] = true;
                    NodeList list = this.getNeighbors(next);
                    while(!list.isEmpty()){
                        Node edge = list.remove();
                        Tuple v = edge.coordinates;
                        if(this.distance[v.x][v.y] > this.distance[u.x][u.y] + edge.weight){
                            this.distance[v.x][v.y] = this.distance[u.x][u.y] + edge.weight;
                            
                            this.queue.insert(edge,this.distance[v.x][v.y]);
                            this.predecessor[v.x][v.y]= u;
                        }
                    }
                }
            }
    }
    
    
    
    
    /**
     * a getter method with returns the distance of the shortest path from the origin to the destination
     * @param destination a tuple with the coordinates of the desired destination, must be integer type
     * @return a double type value of the shortest path from the origin to the destination
     */
    public double getDistance(Tuple destination){
        return this.distance[destination.x][destination.y];
    }
    
}
