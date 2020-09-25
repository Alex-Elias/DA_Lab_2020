
import java.util.Comparator;


public class JPS_Node implements Comparator<JPS_Node>{
    Tuple coordinates;
    JPS_Node parent;
    double f,g,h;
    boolean forced;
    double weight;
    
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
    public void set_f_g_h(double f,double g,double h){
        this.f=f;
        this.g=g;
        this.h=h;
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
