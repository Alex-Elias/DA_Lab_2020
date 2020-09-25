
import java.util.PriorityQueue;


/**
 * DOES NOT FUNCTION YET!
 * has null point exception errors
 * implementation of the Jump Point Search algorithm
 * @author alex
 */
public class JPS {

    int[][] maze;
    JPS_Node start;
    JPS_Node goal;
    PriorityQueue<JPS_Node> queue;
    double[][] distance;
    JPS_Node[][] predecessor;
    double weight;
    
    
    public JPS(int[][] maze){
        this.maze = maze;
        this.queue= new PriorityQueue<JPS_Node>(new JPS_Node());
        this.predecessor = new JPS_Node[maze.length][maze[0].length];
        this.distance = new double[maze.length][maze[0].length];
        
    }
    public void run_JPS( JPS_Node start, JPS_Node goal){
        start.f=0;
        start.parent=start;
        this.queue.add(start);
        this.predecessor[start.coordinates.x][start.coordinates.y] = start;
        
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = 2147483647;
                
                
            }
            
        }
        this.distance[start.coordinates.x][start.coordinates.y] = 0;
        
        while(!this.queue.isEmpty()){
            
            
            JPS_Node current = this.queue.poll();
            
            
            if(current.coordinates.x == goal.coordinates.x && current.coordinates.y == goal.coordinates.y){
                this.weight = current.weight;
                break;
            }
            JPS_Node[] next = get_successors(current,start,goal);
            for(int i = 0; i< next.length; i++){
                if(next[i]== null){
                    continue;
                }
                next[i].f = next[i].weight + heuristic(next[i],goal);
                this.queue.add(next[i]);
            }


            
            
        }
        
        
    }
    public double heuristic(JPS_Node location, JPS_Node location2){
        double dx = Math.abs(location.coordinates.x - location2.coordinates.x);
        double dy = Math.abs(location.coordinates.y - location2.coordinates.y);
        
        return dx+dy +(1.4142135 - 2) * Math.min(dx, dy);
        
    }
    
    public JPS_Node[] get_successors(JPS_Node node, JPS_Node start, JPS_Node goal){
        JPS_Node[] successors = new JPS_Node[8];
        JPS_Node[] neighbors = get_neighbors_pruned(node);
        for(int i = 0; i < neighbors.length; i++){
            if(neighbors[i]== null){
                continue;
            }
            JPS_Node jump = jump(node, get_direction(neighbors[i]),start,goal);
            if(jump != null){
                //need to implement distance and heuristics here or possibly in the jump method
            }
            successors[i]= jump;
            if(jump!= null){
                this.predecessor[jump.coordinates.x][jump.coordinates.y] = node;

            }
        }
        return successors;
    }
    public JPS_Node jump(JPS_Node node, int direction, JPS_Node start, JPS_Node goal){
        
        JPS_Node n = step(node,direction);
        
        
        if(n == null){
            return null;
        }
        if(n.coordinates.x == goal.coordinates.x && n.coordinates.y == goal.coordinates.y){
            return n;
        }
        JPS_Node[] neighbors = get_neighbors_pruned(n);
        for(int i = 0; i< neighbors.length;i++){
            if(neighbors[i]== null){
                continue;
            }
            if(neighbors[i].forced){
                return n;
            }
        }
        if (direction%2==1){
            switch (direction) {
                case 3:
                    if (jump(n,2,start,goal) !=null){
                        return n;
                    }   if (jump(n,4,start,goal) !=null){
                        return n;
                    }   break;
                case 1:
                    if (jump(n,2,start,goal) !=null){
                        return n;
                    }   if (jump(n,8,start,goal) !=null){
                        return n;
                    }   break;
                case 7:
                    if (jump(n,6,start,goal) !=null){
                        return n;
                    }   if (jump(n,8,start,goal) !=null){
                        return n;
                    }   break;
                case 5:
                    if (jump(n,4,start,goal) !=null){
                        return n;
                    }   if (jump(n,6,start,goal) !=null){
                        return n;
                    }   break;
                default:
                    break;
            }
        }
        
        
        return jump(n,direction,start,goal);
    }
    public JPS_Node step(JPS_Node node, int direction){
        JPS_Node temp = get_neighbors(node)[direction];
        if(direction %2 ==0){
            temp.weight = node.weight +1;
        }else{
            temp.weight = node.weight + 1.4142135;
        }
        return temp;
    }
    
    public JPS_Node[] get_neighbors(JPS_Node node){
        JPS_Node[] neighbors = new JPS_Node[9];
        if (node.coordinates.x +1 < this.maze.length){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y] ==0){
                neighbors[6] = (new JPS_Node(new Tuple(node.coordinates.x+1,node.coordinates.y)));
                neighbors[6].parent = node;
            }
        }
        if (node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x][node.coordinates.y +1] == 0){
                neighbors[4] = (new JPS_Node (new Tuple(node.coordinates.x,node.coordinates.y+1)));
                neighbors[4].parent = node;
            }
        }
        if (node.coordinates.x -1 >= 0){
            if (this.maze[node.coordinates.x -1][node.coordinates.y] == 0){
                neighbors[2]=(new JPS_Node (new Tuple(node.coordinates.x-1,node.coordinates.y)));
                neighbors[2].parent = node;
            }
        }
        if (node.coordinates.y -1 >= 0){
            if (this.maze[node.coordinates.x][node.coordinates.y-1] == 0){
                neighbors[8]=(new JPS_Node (new Tuple(node.coordinates.x,node.coordinates.y-1)));
                neighbors[8].parent = node;
            }
        }
        if (node.coordinates.x +1 < this.maze.length && node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y+1] ==0){
                neighbors[5]=(new JPS_Node(new Tuple(node.coordinates.x+1,node.coordinates.y+1)));
                neighbors[5].parent = node;
            }
        }
        if (node.coordinates.x -1 >=0 && node.coordinates.y +1 < this.maze[0].length){
            if(this.maze[node.coordinates.x - 1][node.coordinates.y+1] ==0){
                neighbors[3]=(new JPS_Node(new Tuple(node.coordinates.x-1,node.coordinates.y+1)));
                neighbors[3].parent = node;
            }
        }
        if (node.coordinates.x +1 < this.maze.length && node.coordinates.y -1 >=0){
            if(this.maze[node.coordinates.x + 1][node.coordinates.y-1] ==0){
                neighbors[1]=(new JPS_Node(new Tuple(node.coordinates.x+1,node.coordinates.y-1)));
                neighbors[1].parent = node;
            }
        }
        if (node.coordinates.x -1 >=0 && node.coordinates.y-1 >=0){
            if(this.maze[node.coordinates.x - 1][node.coordinates.y-1] ==0){
                neighbors[7]=(new JPS_Node(new Tuple(node.coordinates.x-1,node.coordinates.y-1)));
                neighbors[7].parent = node;
            }
        }
        return neighbors;
    }
    public JPS_Node[] get_neighbors_pruned(JPS_Node node){
        
        JPS_Node[] neighbors = get_neighbors(node);
        JPS_Node[] pruned = new JPS_Node[5];
        int direction = get_direction(node);
        if(direction == 0){
            return neighbors;
        }
        if (direction%2 ==0){
            if (direction == 4){
                if(neighbors[2] == null){
                    pruned[1] = neighbors[3];
                    pruned[1].forced=true;
                }
                if(neighbors[6] == null){
                    pruned[2] = neighbors[5];
                    pruned[2].forced=true;
                }
                pruned[3] = neighbors[4];
            }
            if (direction == 6){
                if(neighbors[8] == null){
                    pruned[1] = neighbors[7];
                    pruned[1].forced=true;
                }
                if(neighbors[4] == null){
                    pruned[2] = neighbors[5];
                    pruned[2].forced=true;
                }
                pruned[3] = neighbors[6];
            }
            if (direction == 8){
                if(neighbors[2] == null){
                    pruned[1] = neighbors[1];
                    pruned[1].forced=true;
                }
                if(neighbors[6] == null){
                    pruned[2] = neighbors[7];
                    pruned[2].forced=true;
                }
                pruned[3] = neighbors[8];
            }
            if (direction == 2){
                if(neighbors[8] == null){
                    pruned[1] = neighbors[1];
                    pruned[1].forced=true;
                }
                if(neighbors[4] == null){
                    pruned[2] = neighbors[3];
                    pruned[2].forced=true;
                }
                pruned[3] = neighbors[6];
            }
        }else{
            if(direction==3){
                if(neighbors[6]==null){
                    pruned[0]= neighbors[5];
                    pruned[0].forced=true;
                }
                if(neighbors[8]==null){
                    pruned[1]=neighbors[1];
                    pruned[1].forced=true;
                }
                pruned[2]=neighbors[2];
                pruned[3]=neighbors[3];
                pruned[4]=neighbors[4];
            }
            if(direction==7){
                if(neighbors[2]==null){
                    pruned[0]= neighbors[1];
                    pruned[0].forced=true;
                }
                if(neighbors[4]==null){
                    pruned[1]=neighbors[5];
                    pruned[1].forced=true;
                }
                pruned[2]=neighbors[6];
                pruned[3]=neighbors[7];
                pruned[4]=neighbors[8];
            }
            if(direction==5){
                if(neighbors[2]==null){
                    pruned[0]= neighbors[3];
                    pruned[0].forced=true;
                }
                if(neighbors[8]==null){
                    pruned[1]=neighbors[7];
                    pruned[1].forced=true;
                }
                pruned[2]=neighbors[6];
                pruned[3]=neighbors[5];
                pruned[4]=neighbors[4];
            }
            if(direction==1){
                if(neighbors[6]==null){
                    pruned[0]= neighbors[7];
                    pruned[0].forced=true;
                }
                if(neighbors[4]==null){
                    pruned[1]=neighbors[3];
                    pruned[1].forced=true;
                }
                pruned[2]=neighbors[8];
                pruned[3]=neighbors[1];
                pruned[4]=neighbors[2];
            }
        }
        return pruned;
        
    }
    
    
    
    
    public int get_direction(JPS_Node node){
        int x = node.coordinates.x -node.parent.coordinates.x;
        int y = node.coordinates.y -node.parent.coordinates.y;
        
        if(x == 1){
            if(y==-1){
                return 7;
            }
            if(y==0){
                return 6;
            }
            if (y==1){
                return 5;
            }
        }
        if(x == -1){
            if(y==-1){
                return 1;
            }
            if(y==0){
                return 2;
            }
            if (y==1){
                return 3;
            }
        }
        if(x==0){
            if(y==1){
                return 4;
            }
            if(y==-1){
                return 8;
            }
        }
        return 0;
    }


    
}
