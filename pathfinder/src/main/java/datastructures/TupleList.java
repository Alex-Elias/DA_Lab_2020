package datastructures;

/**
 *
 * @author alex
 */
public class TupleList {
    private Tuple[] list;
    private int last;
    
    public TupleList(){
        this.list = new Tuple[30];
        this.last = 0;
    }
    public void add(Tuple tuple){
        this.last++;
        this.list[last]=tuple;
        if (this.last > this.list.length *0.8){
            this.increaseSize();
        }
    }
    private void increaseSize(){
        Tuple[] temp = new Tuple[this.list.length * 2];
        for(int i = 1; i< this.list.length; i++){
            temp[i]= this.list[i];
        }
        this.list = temp;
    }
    public Tuple remove(){
        if(this.last == 0){
            return null;
        }
        Tuple r = this.list[this.last];
        this.last--;
        if(this.last < this.list.length *0.25 && this.list.length > 120){
            this.decreaseSize();
        }
        return r;
    }
    private void decreaseSize(){
        Tuple[] temp = new Tuple[(int) this.list.length/2];
        for(int i = 1; i <= temp.length/2 +1; i++){
            temp[i] = this.list[i];
        }
        this.list = temp;
    }
    public boolean isEmpty(){
        if(this.last == 0){
            return true;
        }
        return false;
    }
    
}
