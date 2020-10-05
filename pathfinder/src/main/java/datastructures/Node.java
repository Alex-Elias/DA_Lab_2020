package datastructures;


import datastructures.Tuple;


/**
 * 
 * @author alex
 */
public class Node {
    public Tuple coordinates;
    public Node parent;
    public double f;
    public boolean forced;
    public double weight;
    public double priority;
        
    public Node(Tuple coordinates){
        this.coordinates = coordinates;
    }
    public Node(Tuple coordinates,double weight){
        this.coordinates=coordinates;
        this.weight=weight;
    }
    public Node(){
        
    }
    
    public void set_parent(Node parent){
        this.parent=parent;
    }
    
    
    

    
}
