package algorithms;

import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;

/**
 *
 * @author alex
 */
public class Algorithm {
    private int[][] maze;
    private double RootTwo = 1.4142135; 
    private Node destination;
    private double distance;
    
    public Algorithm(int[][] maze){
        this.maze = maze;
    }
    
    
    
    public NodeList getNeighbors(Node node){
        NodeList neighbors = new NodeList();
        if (node.getX() +1 < this.maze.length){
            if(this.maze[node.getX() + 1][node.getY()] ==0){
                Node temp = new Node(new Tuple(node.getX()+1,node.getY()),1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getY() +1 < this.maze[0].length){
            if(this.maze[node.getX()][node.getY() +1] == 0){
                Node temp = new Node (new Tuple(node.getX(),node.getY()+1),1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getX() -1 >= 0){
            if (this.maze[node.getX() -1][node.getY()] == 0){
                Node temp = new Node (new Tuple(node.getX()-1,node.getY()),1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getY() -1 >= 0){
            if (this.maze[node.getX()][node.getY()-1] == 0){
                Node temp = new Node (new Tuple(node.getX(),node.getY()-1),1);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getX() +1 < this.maze.length && node.getY() +1 < this.maze[0].length){
            if(this.maze[node.getX() + 1][node.getY()+1] ==0){
                Node temp = new Node(new Tuple(node.getX()+1,node.getY()+1),this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getX() -1 >=0 && node.getY() +1 < this.maze[0].length){
            if(this.maze[node.getX() - 1][node.getY()+1] ==0){
                Node temp = new Node(new Tuple(node.getX()-1,node.getY()+1),this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getX() +1 < this.maze.length && node.getY() -1 >=0){
            if(this.maze[node.getX() + 1][node.getY()-1] ==0){
                Node temp = new Node(new Tuple(node.getX()+1,node.getY()-1),this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        if (node.getX() -1 >=0 && node.getY()-1 >=0){
            if(this.maze[node.getX() - 1][node.getY()-1] ==0){
                Node temp = new Node(new Tuple(node.getX()-1,node.getY()-1),this.RootTwo);
                temp.setParent(node);
                neighbors.add(temp);
                
            }
        }
        return neighbors;
    }
    
    public NodeList getShortestPath(){
        NodeList list = new NodeList();
        Node last = this.destination;
        while(last.getCoordinates() != last.getParent().getCoordinates()){
            
            list.add(last);
            last = last.getParent();
        }
        return list;
    }
    
    public void setDestination(Node destination){
        this.destination = destination;
    }
    
    /**
     * a method which finds the heuristic value between two points
     * @param location the node from where one wants to find the heuristic value
     * @param location2 the goal location as a JPS_Node
     * @return a double value of the heuristic value
     */
    //Theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
    public double heuristic(Node location, Node location2){
        double dx = abs(location.getX() - location2.getX());
        double dy = abs(location.getY() - location2.getY());
        
        return dx+dy +(1.4142135 - 2) * min(dx, dy);
        
    }
    public void setDistance(double distance){
        this.distance=distance;
    }
    
    public double getDistance(){
        return this.distance;
    }
    
    private double abs(double value){
        if(value < 0){
            return value *-1;
        }
        return value;
    }
    private double min(double one, double two){
        if(one <= two){
            return one;
        }
        return two;
    }
    
    
}
