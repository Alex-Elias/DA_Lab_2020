package datastructures;


import datastructures.Tuple;


/**
 * 
 * @author alex
 */
public class Node {
    private Tuple coordinates;
    private Node parent;
    private double f;
    private boolean forced;
    private double weight;
    private double priority;
        
    public Node(Tuple coordinates){
        this.coordinates = coordinates;
    }
    public Node(Tuple coordinates,double weight){
        this.coordinates=coordinates;
        this.weight=weight;
    }
    public Node(){
        
    }
    
    public void setParent(Node parent){
        this.parent=parent;
    }
    public void setF(double f){
        this.f = f;
    }
    
    public void setForced(boolean forced){
        this.forced = forced;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public void setPriority(double priority){
        this.priority = priority;
    }
    
    public double getF(){
        return this.f;
    }
    public Node getParent(){
        return this.parent;
    }
    
    public double getWeight(){
        return this.weight;
    }
    public boolean isForced(){
        return this.forced;
    }
    public double getPriority(){
        return this.priority;
    }
    public int getX(){
        return this.coordinates.getX();
    }
    public int getY(){
        return this.coordinates.getY();
    }
    public Tuple getCoordinates(){
        return this.coordinates;
    }
    
    
    
    
    

    
}
