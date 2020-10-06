package datastructures;

/**
 *
 * @author alex
 */
public class NodeList {
    private Node[] list;
    private int last;
    
    public NodeList(){
        this.list = new Node[30];
        this.last = 0;
    }
    
    public void add(Node node){
        this.last++;
        this.list[last]=node;
        if (this.last > this.list.length *0.8){
            this.increaseSize();
        }
    }
    public boolean isEmpty(){
        if(this.last == 0){
            return true;
        }
        return false;
    }
    
    public Node remove(){
        if(this.last == 0){
            return null;
        }
        Node r = this.list[this.last];
        this.last--;
        if(this.last < this.list.length *0.25 && this.list.length > 120){
            this.decreaseSize();
        }
        return r;
    }
    
    private void increaseSize(){
        Node[] temp = new Node[this.list.length * 2];
        for(int i = 1; i< this.list.length; i++){
            temp[i]= this.list[i];
        }
        this.list = temp;
    }
    
    private void decreaseSize(){
        Node[] temp = new Node[(int) this.list.length/2];
        for(int i = 1; i <= temp.length/2 +1; i++){
            temp[i] = this.list[i];
        }
        this.list = temp;
    }
}
