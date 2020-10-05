package datastructures;

public class PriorityQueue {
    private Node[] heap;
    private int last;
    
    public PriorityQueue(){
        this.heap = new Node[64];
        this.last = 0;
    }
    private int left(int p){
        if(2*p > last){
            return 0;
        }
        return 2 *p;
    }
    private int right(int p){
        if(2*p+1 > last){
            return 0;
        }
        return 2*p+1;
    }
    private int parent(int p){
        return Math.floorDiv(p, 2);
    }
    
    public Node min(){
        return this.heap[1];
    }
    
    public void insert(Node node, double priority){
        this.last = last +1;
        node.priority = priority;
        int p = last;
        while(p>1 && (node.priority < this.heap[this.parent(p)].priority)){
            this.heap[p] = this.heap[this.parent(p)];
            p = this.parent(p);
            
        }
        this.heap[p] = node;
        if(this.last > this.heap.length * 0.8){
            this.increaseSize();
        }
    }
    
    public Node deleteMin(){
        Node element = this.heap[1];
        this.heap[1] = this.heap[this.last];
        this.last = this.last -1;
        this.pushDown(1);
        if(last < this.heap.length * 0.25 && this.heap.length >64){
            this.decreaseSize();
        }
        return element;
    }
    public boolean isEmpty(){
        if(last == 0){
            return true;
        }
        return false;
    }
    private void pushDown(int node){
        int smallerChild = 0;
        int left = left(node);
        int right = right(node);
        if(left == 0 || right == 0){
            return;
        }
        if(left > this.last){
            return;
        }else if(left == last){
            smallerChild = left;
        }
        else{
            if (this.heap[left].priority < this.heap[right].priority){
                smallerChild = left;
            }else{
                smallerChild = right;
            }
            if( this.heap[node].priority > this.heap[smallerChild].priority){
                swap(node,smallerChild);
                pushDown(smallerChild);
            }
        }
        
    }
    
    private void swap(int a, int b){
        Node temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }
    private void increaseSize(){
        Node[] temp = new Node[this.heap.length * 2];
        for(int i = 1; i< this.heap.length; i++){
            temp[i]= this.heap[i];
        }
        this.heap = temp;
    }
    private void decreaseSize(){
        Node[] temp = new Node[Math.floorDiv(this.heap.length, 2)];
        for(int i = 1; i <= temp.length/2 +1; i++){
            temp[i] = this.heap[i];
        }
        this.heap = temp;
    }
    
}
