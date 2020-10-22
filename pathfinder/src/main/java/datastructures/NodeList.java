package datastructures;

/**
 * a list for node data structure
 * this data structure has following functionality:
 * adding to the list
 * removing last element from the list
 * isEmpty method
 * length method
 * @author alex
 */
public class NodeList {
    /**
     * node array that stores the individual nodes
     */
    private Node[] list;
    /**
     * the index of the last element in the array
     */
    private int last;
    /**
     * initializes the class with a starting length of 30
     */
    public NodeList(){
        this.list = new Node[30];
        this.last = 0;
    }
    /**
     * adds a node to the list
     * adds a node to the end of the list and increases the index by one
     * @param node the node to be added to the list
     */
    public void add(Node node){
        this.last++;
        this.list[last]=node;
        if (this.last > this.list.length *0.8){
            this.increaseSize();
        }
    }
    /**
     * returns if the list is empty
     * @return a boolean value whether the list is empty or not
     */
    public boolean isEmpty(){
        if(this.last == 0){
            return true;
        }
        return false;
    }
    /**
     * removes the last element in the list
     * @return the last element that was removed
     */
    public Node remove(){
        if(this.last == 0){
            return null;
        }
        Node r = this.list[this.last];
        this.list[this.last] = null;
        this.last--;
        if(this.last < this.list.length *0.25 && this.list.length > 120){
            this.decreaseSize();
        }
        return r;
    }
    /**
     * increases the size of the array when the index is greater than half the length of the array
     */
    private void increaseSize(){
        Node[] temp = new Node[this.list.length * 2];
        for(int i = 1; i< this.list.length; i++){
            temp[i]= this.list[i];
        }
        this.list = temp;
    }
    /**
     * decreases the size of the array when the index is lesser than than a quarter of the length of the array
     */
    private void decreaseSize(){
        Node[] temp = new Node[(int) this.list.length/2];
        for(int i = 1; i <= temp.length/2 +1; i++){
            temp[i] = this.list[i];
        }
        this.list = temp;
    }
    /**
     * returns the number of elements in the list
     * @return the length of the list
     */
    public int length(){
        return last;
    }
    
    
}
