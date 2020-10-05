package datastructures;


import datastructures.Tuple;
import java.util.Comparator;

/**
 * another node class
 * I will combine both to make a common class.
 * @author alex
 */
public class JPS_Node implements Comparator<JPS_Node>{
    public Tuple coordinates;
    public JPS_Node parent;
    public double f;
    public boolean forced;
    public double weight;
    public double priority;
        
    public JPS_Node(Tuple coordinates){
        this.coordinates = coordinates;
    }
    public JPS_Node(Tuple coordinates,double weight){
        this.coordinates=coordinates;
        this.weight=weight;
    }
    public JPS_Node(){
        
    }
    
    public void set_parent(JPS_Node parent){
        this.parent=parent;
    }
    
    
    @Override
    public int compare(JPS_Node node, JPS_Node node2){
        if(node.f> node2.f){
            return 1;
        }
        if(node.f < node2.f){
            return -1;
        }
        return 0;
    }

    
}
