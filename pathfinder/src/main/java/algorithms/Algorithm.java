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
    
    public Algorithm(int[][] maze){
        this.maze = maze;
    }
    
    
    
    public NodeList getNeighbors(Node node){
        NodeList neighbors = new NodeList();
        if (node.coordinates.x +1 < this.maze.length){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y] ==0){
                Node temp = new Node(new Tuple(node.coordinates.x+1,node.coordinates.y),1);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x][node.coordinates.y +1] == 0){
                Node temp = new Node (new Tuple(node.coordinates.x,node.coordinates.y+1),1);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.x -1 >= 0){
            if (this.maze[node.coordinates.x -1][node.coordinates.y] == 0){
                Node temp = new Node (new Tuple(node.coordinates.x-1,node.coordinates.y),1);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.y -1 >= 0){
            if (this.maze[node.coordinates.x][node.coordinates.y-1] == 0){
                Node temp = new Node (new Tuple(node.coordinates.x,node.coordinates.y-1),1);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.x +1 < this.maze.length && node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y+1] ==0){
                Node temp = new Node(new Tuple(node.coordinates.x+1,node.coordinates.y+1),this.RootTwo);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.x -1 >=0 && node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x - 1][node.coordinates.y+1] ==0){
                Node temp = new Node(new Tuple(node.coordinates.x-1,node.coordinates.y+1),this.RootTwo);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.x +1 < this.maze.length && node.coordinates.y -1 >=0){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y-1] ==0){
                Node temp = new Node(new Tuple(node.coordinates.x+1,node.coordinates.y-1),this.RootTwo);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        if (node.coordinates.x -1 >=0 && node.coordinates.y-1 >=0){
            if(this.maze[node.coordinates.x - 1][node.coordinates.y-1] ==0){
                Node temp = new Node(new Tuple(node.coordinates.x-1,node.coordinates.y-1),this.RootTwo);
                temp.parent = node;
                neighbors.add(temp);
                
            }
        }
        return neighbors;
    }
    
    public NodeList getShortestPath(){
        NodeList list = new NodeList();
        Node last = this.destination;
        while(last.coordinates != last.parent.coordinates){
            list.add(last);
            last = last.parent;
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
        double dx = Math.abs(location.coordinates.x - location2.coordinates.x);
        double dy = Math.abs(location.coordinates.y - location2.coordinates.y);
        
        return dx+dy +(1.4142135 - 2) * Math.min(dx, dy);
        
    }
    
}
