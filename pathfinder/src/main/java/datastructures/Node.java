package datastructures;


import java.util.Comparator;
/**
 * a class which stores the coordinates and weight of a Node
 * The coordinates are stored in an integer tuple of length two
 * the weight is a floating point number.
 * this class has a compare override method which compares weight 
 * this class has an equal override which compares the coordinates and weight
 * @author alex
 */
public class Node implements Comparator<Node>{
    public Tuple coordinate;
    public double weight;
    
    public Node(){
        
    };
    /**
     * the initializing method for this class
     * @param coordinate a integer tuple of length two with the first value representing the row number and the second the column number
     * @param weight the weight of the node stored as a floating point number
     */
    public Node(Tuple coordinate, double weight){
        this.coordinate=coordinate;
        this.weight=weight;
    }
    
    @Override
    public int compare(Node node1, Node node2){
        if(node1.weight> node2.weight){
            return 1;
        }
        if(node1.weight < node2.weight){
            return -1;
        }
        return 0;
    }
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if(!(o instanceof Node)){
            return false;
        }
        Node n = (Node) o;
        return this.coordinate == n.coordinate && Math.abs(n.weight-this.weight) < 0.0001;
    }
            
    @Override
    public String toString(){
        return "" + this.coordinate + ", Weight = " + this.weight;
    }
}
