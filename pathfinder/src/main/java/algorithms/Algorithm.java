package algorithms;

import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;

/**
 *The superclass to AStar, Dijkstra and JPS
 *this class includes the getNeighbors method, the heuristic and the getShortestPath method
 * @author alex
 */
public class Algorithm {
    /**
     * the 2d array which represents the maze
     */
    private int[][] maze;
    /**
     * value of root two
     */
    private double RootTwo = 1.4142135; 
    /**
     * the node of the destination, used to find the shortest path
     */
    private Node destination;
    /**
     * the distance of the shortest path from the start to the destination
     */
    private double distance;
    
    /**
     * initializes the Algorithm class
     * takes the 2d array and stores it to the maze variable
     * @param maze the 2d integer array where ones are obstacles and zeros are free spaces
     */
    public Algorithm(int[][] maze){
        this.maze = maze;
    }
    
    
    /**
     * returns a Nodelist of all the neighbors to a certain node
     * the method gets a node as a parameter and finds all the valid neighbors around the node
     * @param node the node which all the neighbors are found
     * @return Nodelist of all the valid neighbors to the node parameter
     */
    public NodeList getNeighbors(Node node){
        NodeList neighbors = new NodeList();
        //checks the upper vertical node
        if (node.getX() + 1 < this.maze.length){
            if(this.maze[node.getX() + 1][node.getY()] ==0){
                Node temp = new Node(new Tuple(node.getX() + 1,node.getY()), 1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        // checks the right node
        if (node.getY() + 1 < this.maze[0].length){
            if(this.maze[node.getX()][node.getY() + 1] == 0){
                Node temp = new Node (new Tuple(node.getX(), node.getY() + 1), 1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        // checks the bottom node
        if (node.getX() - 1 >= 0){
            if (this.maze[node.getX() - 1][node.getY()] == 0){
                Node temp = new Node (new Tuple(node.getX() - 1, node.getY()), 1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        // checks the left node
        if (node.getY() - 1 >= 0){
            if (this.maze[node.getX()][node.getY()-1] == 0){
                Node temp = new Node (new Tuple(node.getX(), node.getY() - 1), 1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        // checks the top right node
        if (node.getX() + 1 < this.maze.length && node.getY() + 1 < this.maze[0].length){
            if(this.maze[node.getX() + 1][node.getY() + 1] ==0){
                Node temp = new Node(new Tuple(node.getX() + 1, node.getY() + 1), this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        // checks the bottom right node
        if (node.getX() - 1 >= 0 && node.getY() + 1 < this.maze[0].length){
            if(this.maze[node.getX() - 1][node.getY() + 1] == 0){
                Node temp = new Node(new Tuple(node.getX() - 1, node.getY() + 1), this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        //checks the top left node
        if (node.getX() + 1 < this.maze.length && node.getY() - 1 >= 0){
            if(this.maze[node.getX() + 1][node.getY() - 1] ==0){
                Node temp = new Node(new Tuple(node.getX() + 1, node.getY() - 1), this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        //checks the bottom left node
        if (node.getX() - 1 >= 0 && node.getY() - 1 >= 0){
            if(this.maze[node.getX() - 1][node.getY() - 1] == 0){
                Node temp = new Node(new Tuple(node.getX() - 1, node.getY() - 1), this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        return neighbors;
    }
    /**
     * returns the shortest path between the start and the destination
     * finds the shortest route by running through parent nodes of the destination until the parent points to itself
     * @return Nodelist of the nodes between the start and the destination
     */
    public NodeList getShortestPath(){
        NodeList list = new NodeList();
        Node last = this.destination;
        while (last.getCoordinates() != last.getParent().getCoordinates()){
            
            list.add(last);
            last = last.getParent();
        }
        return list;
    }
    /**
     * sets the destination node
     * @param destination the destination node to be set
     */
    public void setDestination(Node destination){
        this.destination = destination;
    }
    
    /**
     * a method which finds the heuristic value between two points
     * the heuristic is the straight distance between the first and second location without any obstacle
     * 
     * @param location the node from where one wants to find the heuristic value
     * @param location2 the goal location as a Node
     * @return a double value of the heuristic value
     */
    //Theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
    public double heuristic(Node location, Node location2){
        double dx = abs(location.getX() - location2.getX());
        double dy = abs(location.getY() - location2.getY());
        double heuristic = dx + dy + (1.4142135 - 2) * min(dx, dy);
        return heuristic;
        
    }
    /**
     * sets the shortest path distance
     * @param distance the distance of the shortest path between the start and the destination
     */
    public void setDistance(double distance){
        this.distance = distance;
    }
    /**
     * returns the distance of the shortest path
     * @return the distance of the shortest path
     */
    public double getDistance(){
        return this.distance;
    }
    /**
     * returns the absolute value of the parameter
     * 
     * @param value the variable which the absolute value of it is found
     * @return the absolute value of parameter
     */
    private double abs(double value){
        if(value < 0){
            return value * -1;
        }
        return value;
    }/**
     * returns the minimum value of the two parameters
     * @param one the first of the two values that are compared
     * @param two the second of the two values that are compared
     * @return the lesser of the two parameters
     */
    private double min(double one, double two){
        if(one <= two){
            return one;
        }
        return two;
    }
    
    
}
