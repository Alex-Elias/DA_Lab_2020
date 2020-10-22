package datastructures;
/**
 * a minimum priority queue for the node data structure
 * the priority queue has the following functionality:
 * min which returns the minimum element
 * insert
 * deletemin
 * isEmpty
 * getSize
 * @author alex
 */
public class NodePriorityQueue {
    /**
     * the array where the nodes are stored
     */
    private Node[] heap;
    /**
     * the index of the last element
     */
    private int last;
    /**
     * initializes the class
     */
    public NodePriorityQueue(){
        this.heap = new Node[64];
        this.last = 0;
    }
    /**
     * returns the left child of the node in the heap
     * @param index the index of the parent
     * @return the index of the left child
     */
    private int left(int index){

        return 2 *index;
    }
    /**
     * returns index of the right child
     * @param index the index of the parent
     * @return the index of the right child of the parent
     */
    private int right(int index){

        return 2*index+1;
    }
    /**
     * returns the index of the parent of the node
     * @param index the index of the child node
     * @return the index of the parent
     */
    private int parent(int index){
        return (int) index/2;
    }
    /**
     * returns the minimum element in the queue without removing
     * @return the minimum element
     */
    public Node min(){
        return this.heap[1];
    }
    /**
     * inserts a node into the priority queue
     * @param node the node to be inserted
     * @param priority the priority of the node
     */
    public void insert(Node node, double priority){
        this.last = last +1;
        
        node.setPriority(priority);
        int index = last;
        while(index > 1 && (node.getPriority() < this.heap[this.parent(index)].getPriority())){
            this.heap[index] = this.heap[this.parent(index)];
            index = this.parent(index);
            
        }
        this.heap[index] = node;
        if(this.last > this.heap.length * 0.8){
            this.increaseSize();
        }
    }
    /**
     * deletes and returns the minimum priority element
     * @return the minimum priority node
     */
    public Node deleteMin(){
        Node element = this.heap[1];
        this.heap[1] = this.heap[this.last];
        this.heap[this.last] = null;
        this.last = this.last -1;
        this.pushDown(1);
        if(last < this.heap.length * 0.25 && this.heap.length >64){
            this.decreaseSize();
        }
        return element;
    }
    /**
     * returns whether the queue is empty or not
     * @return boolean value where the queue is empty
     */
    public boolean isEmpty(){
        if(last == 0){
            return true;
        }
        return false;
    }
    /**
     * pushes down the node in the heap until it is at the appropriate position
     * @param node the node that is pushed down
     */
    private void pushDown(int node){
        int smallerChild = node;
        int left = left(node);
        int right = right(node);
        
        if(left > this.last){
            return;
        }else if(left == last){
            smallerChild = left;
        }
        else{
            if (this.heap[left].getPriority() < this.heap[right].getPriority()){
                smallerChild = left;
            }else{
                smallerChild = right;
            }
            
        }
        if( this.heap[node].getPriority() > this.heap[smallerChild].getPriority()){
                swap(node,smallerChild);
                pushDown(smallerChild);
            }
        
    }
    /**
     * swaps the position of two elements in the heap
     * @param a first element to swap
     * @param b second element to swap
     */
    private void swap(int a, int b){
        Node temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }
    /**
     * doubles the size of the array 
     */
    private void increaseSize(){
        Node[] temp = new Node[this.heap.length * 2];
        for(int i = 1; i< this.heap.length; i++){
            temp[i]= this.heap[i];
        }
        this.heap = temp;
    }
    /**
     * halves the size of the array
     */
    private void decreaseSize(){
        Node[] temp = new Node[(int) this.heap.length/2];
        for(int i = 1; i <= temp.length/2 +1; i++){
            temp[i] = this.heap[i];
        }
        this.heap = temp;
    }
    /**
     * returns the number of elements in the priority queue
     * @return the number of elements in the priority queue
     */
    public int getSize(){
        return this.last;
    }
    
}
