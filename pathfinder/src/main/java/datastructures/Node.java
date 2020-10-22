package datastructures;

/**
 * a data structure which stores a tuple and the parent node, the f value, whether the node is forced,
 * the weight of the node and the priority
 * @author alex
 */
public class Node {
    /**
     * the coordinates of the node stored in as a tuple
     */
    private Tuple coordinates;
    /**
     * the parent node
     */
    private Node parent;
    /**
     * the f value of the node
     */
    private double f;
    /**
     * the boolean value whether the node is forced or not
     */
    private boolean forced;
    /**
     * the weight of the node from the starting node
     */
    private double weight;
    /**
     * the priority of the node used in the minimum priority queue
     */
    private double priority;
    
    /**
     * initializes the node class with the tuple coordinates
     * saves the parameter to the class variable coordinates
     * @param coordinates tuple which stores the coordinates
     */
    public Node(Tuple coordinates){
        this.coordinates = coordinates;
    }
    /**
     * initializes the node class with the tuple coordinates as well as the weight of the node
     * saves the parameters to the class variables coordinates and weight
     * @param coordinates the tuple which stores the coordinates
     * @param weight the weight of the node
     */
    public Node(Tuple coordinates,double weight){
        this.coordinates=coordinates;
        this.weight=weight;
    }
    
    /**
     * sets the class variable parent
     * @param parent the node to be set to parent
     */
    public void setParent(Node parent){
        this.parent=parent;
    }
    /**
     * sets the class variable f
     * @param f the value to be set to f
     */
    public void setF(double f){
        this.f = f;
    }
    /**
     * sets the class variable forced
     * @param forced the value to be set to forced
     */
    public void setForced(boolean forced){
        this.forced = forced;
    }
    /**
     * sets the class variable weight
     * @param weight the value to be set to weight
     */
    public void setWeight(double weight){
        this.weight = weight;
    }
    /**
     * sets the class variable priority
     * @param priority the value to be set to priority
     */
    public void setPriority(double priority){
        this.priority = priority;
    }
    /**
     * returns the value of the class variable f
     * @return the value of the class variable f
     */
    public double getF(){
        return this.f;
    }
    /**
     * returns the node parent
     * @return the node parent
     */
    public Node getParent(){
        return this.parent;
    }
    /**
     * returns the value of the class variable weight
     * @return the class variable weight
     */
    public double getWeight(){
        return this.weight;
    }
    /**
     * returns the boolean value of the class variable forced
     * @return the class variable forced
     */
    public boolean isForced(){
        return this.forced;
    }
    /**
     * returns the value of the class variable priority as double data type
     * @return the variable priority as double variable type
     */
    public double getPriority(){
        return this.priority;
    }
    /**
     * returns the first coordinate of the tuple
     * @return the first coordinate of the tuple as an integer
     */
    public int getX(){
        return this.coordinates.getX();
    }
    /**
     * returns the second coordinate of the tuple
     * @return the second coordinate of the tuple as an integer
     */
    public int getY(){
        return this.coordinates.getY();
    }
    /**
     * returns the coordinate tuple
     * @return the coordinate tuple
     */
    public Tuple getCoordinates(){
        return this.coordinates;
    }
    
    
    
    
    

    
}
