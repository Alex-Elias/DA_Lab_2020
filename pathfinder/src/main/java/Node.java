
import java.util.Comparator;

public class Node implements Comparator<Node>{
    Tuple coordinate;
    double weight;
    
    public Node(){
        
    };
    
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
