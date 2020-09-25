
import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * A Star algorithm to find the shortest path in a weighted graph
 * basically Dijkstra's but has a heuristic function
 * @author alex
 */
public class AStar {

    int[][] maze;
    double[][] distance;
    PriorityQueue<JPS_Node> queue;
    JPS_Node[][] predecessor;
    
    //  test
    boolean[][] visited;
    /**
     * initialized the A Star class
     * @param maze the 2d integer array where ones are obstacles and zeros are free spaces
     */
    public AStar(int[][] maze){
        this.distance = new double[maze.length][maze[0].length];
        this.maze=maze;
        this.queue = new PriorityQueue<JPS_Node>(new JPS_Node());
        this.predecessor = new JPS_Node[maze.length][maze[0].length];
        
        //test
        this.visited= new boolean[maze.length][maze[0].length];
        
    }
    /**
     * runs the A Star algorithm 
     * @param start the starting node
     * @param goal the goal node
     */
    public void run_AStar(JPS_Node start, JPS_Node goal){
        start.f=0;
        this.queue.add(start);
        this.predecessor[start.coordinates.x][start.coordinates.y] = start;
        
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = 2147483647;
                this.visited[i][j] = false;
                
            }
            
        }
        this.distance[start.coordinates.x][start.coordinates.y] = 0;
        

        
        while(!this.queue.isEmpty()){
            
            
            JPS_Node current = this.queue.poll();
            if(!this.visited[current.coordinates.x][current.coordinates.y]){
                this.visited[current.coordinates.x][current.coordinates.y] = true;
            
                if(current.coordinates.x == goal.coordinates.x && current.coordinates.y == goal.coordinates.y){
                    break;
                }
            
            
            
                for(JPS_Node next : adjacencyList(current.coordinates)){
                    double new_weight = this.distance[current.coordinates.x][current.coordinates.y] + next.weight;

                    if (new_weight < this.distance[next.coordinates.x][next.coordinates.y]){
                        this.distance[next.coordinates.x][next.coordinates.y]= new_weight;
                        double priority = new_weight + heuristic(next,goal);
                        next.f = priority;
                        this.queue.add(next);
                        this.predecessor[next.coordinates.x][next.coordinates.y] = current;
                    }

                }
            }
        }
    }
    
    /**
     * finds the shortest path and returns a list of nodes in the shortest path
     * @param goal the goal node
     * @return list of nodes
     */
    public ArrayList<JPS_Node> get_shortest_path(JPS_Node goal){
        ArrayList<JPS_Node> shortest_path_list = new ArrayList<>();
        JPS_Node last = goal;
        while(this.predecessor[last.coordinates.x][last.coordinates.y].coordinates != last.coordinates){
            shortest_path_list.add(last);
            last = this.predecessor[last.coordinates.x][last.coordinates.y];
        }
        shortest_path_list.add(last);
        return shortest_path_list;
    }
    
    /**
     * a method which finds the heuristic value between two points
     * @param location the node from where one wants to find the heuristic value
     * @param location2 the goal location as a JPS_Node
     * @return a double value of the heuristic value
     */
    //Theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
    public double heuristic(JPS_Node location, JPS_Node location2){
        double dx = Math.abs(location.coordinates.x - location2.coordinates.x);
        double dy = Math.abs(location.coordinates.y - location2.coordinates.y);
        
        return dx+dy +(1.4142135 - 2) * Math.min(dx, dy);
        
    }
    /**
     * the method finds all the available neighbors from the location T
     * @param T the tuple of coordinates where one wants to find the available spaces
     * @return a list of available spaces as JPS_Nodes
     */
    public ArrayList<JPS_Node> adjacencyList(Tuple T){
        ArrayList<JPS_Node> list = new ArrayList();
        if (T.x +1 < this.maze.length){
            if(this.maze[T.x + 1][T.y] ==0){
                list.add(new JPS_Node(new Tuple(T.x+1,T.y),1));
            }
        }
        if (T.y +1 < this.maze[0].length){
            if(this.maze[T.x][T.y +1] == 0){
                list.add(new JPS_Node (new Tuple(T.x,T.y+1),1));
            }
        }
        if (T.x -1 >= 0){
            if (this.maze[T.x -1][T.y] == 0){
                list.add(new JPS_Node (new Tuple(T.x-1,T.y),1));
            }
        }
        if (T.y -1 >= 0){
            if (this.maze[T.x][T.y-1] == 0){
                list.add(new JPS_Node (new Tuple(T.x,T.y-1),1));
            }
        }
        if (T.x +1 < this.maze.length && T.y +1 < this.maze[0].length){
            if(this.maze[T.x + 1][T.y+1] ==0){
                list.add(new JPS_Node(new Tuple(T.x+1,T.y+1),1.4142135));
            }
        }
        if (T.x -1 >=0 && T.y +1 < this.maze[0].length){
            if(this.maze[T.x - 1][T.y+1] ==0){
                list.add(new JPS_Node(new Tuple(T.x-1,T.y+1),1.4142135));
            }
        }
        if (T.x +1 < this.maze.length && T.y -1 >=0){
            if(this.maze[T.x + 1][T.y-1] ==0){
                list.add(new JPS_Node(new Tuple(T.x+1,T.y-1),1.4142135));
            }
        }
        if (T.x -1 >=0 && T.y-1 >=0){
            if(this.maze[T.x - 1][T.y-1] ==0){
                list.add(new JPS_Node(new Tuple(T.x-1,T.y-1),1.4142135));
            }
        }
        return list;
        
        
    }
    
}
