
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;



public class Dijkstra{
    double[][] distance;
    boolean[][] processed;
    int[][] maze;
    PriorityQueue<Node> queue;
    Tuple[][] predecessor;
    
    public Dijkstra(int[][] maze){
        this.distance = new double[maze.length][maze[0].length];
        this.processed = new boolean[maze.length][maze[0].length];
        this.maze = maze;
        this.queue = new PriorityQueue<Node>(new Node());
        this.predecessor = new Tuple[maze.length][maze[0].length];
        
    }
    
    public void runDijkstra(Tuple origin){
        for (int i = 0; i< this.maze.length; i++){
            for (int j = 0; j<this.maze[0].length; j++){
                this.distance[i][j] = 2147483647;
                this.processed[i][j] = false;
            }
            
        }
        
            this.distance[origin.x][origin.y]= 0;
            this.predecessor[origin.x][origin.y]= origin;
            this.queue.add(new Node(origin,0));
            while(!queue.isEmpty()){
                Node next = queue.poll();
                Tuple u = next.coordinate;
                if(!this.processed[u.x][u.y]){
                    this.processed[u.x][u.y] = true;
                    for(Node edge: this.adjacencyList(u)){
                        Tuple v = edge.coordinate;
                        if(this.distance[v.x][v.y] > this.distance[u.x][u.y] + edge.weight){
                            this.distance[v.x][v.y] = this.distance[u.x][u.y] + edge.weight;
                            this.queue.add(new Node(v,this.distance[v.x][v.y]));
                            this.predecessor[v.x][v.y]= u;
                        }
                    }
                }
            }
    }
    
    public ArrayList<Node> adjacencyList(Tuple T){
        ArrayList<Node> list = new ArrayList();
        if (T.x +1 < this.maze.length){
            if(this.maze[T.x + 1][T.y] ==0){
                list.add(new Node(new Tuple(T.x+1,T.y),1));
            }
        }
        if (T.y +1 < this.maze[0].length){
            if(this.maze[T.x][T.y +1] == 0){
                list.add(new Node (new Tuple(T.x,T.y+1),1));
            }
        }
        if (T.x -1 >= 0){
            if (this.maze[T.x -1][T.y] == 0){
                list.add(new Node (new Tuple(T.x-1,T.y),1));
            }
        }
        if (T.y -1 >= 0){
            if (this.maze[T.x][T.y-1] == 0){
                list.add(new Node (new Tuple(T.x,T.y-1),1));
            }
        }
        if (T.x +1 < this.maze.length && T.y +1 < this.maze[0].length){
            if(this.maze[T.x + 1][T.y+1] ==0){
                list.add(new Node(new Tuple(T.x+1,T.y+1),1.4142135));
            }
        }
        if (T.x -1 >=0 && T.y +1 < this.maze[0].length){
            if(this.maze[T.x - 1][T.y+1] ==0){
                list.add(new Node(new Tuple(T.x-1,T.y+1),1.4142135));
            }
        }
        if (T.x +1 < this.maze.length && T.y -1 >=0){
            if(this.maze[T.x + 1][T.y-1] ==0){
                list.add(new Node(new Tuple(T.x+1,T.y-1),1.4142135));
            }
        }
        if (T.x -1 >=0 && T.y-1 >=0){
            if(this.maze[T.x - 1][T.y-1] ==0){
                list.add(new Node(new Tuple(T.x-1,T.y-1),1.4142135));
            }
        }
        return list;
        
        
    }
    public ArrayList<Tuple> getShortestPath(Tuple destination){
        ArrayList<Tuple> shortestPath_list = new ArrayList<>();
        Tuple last = destination;
        while(this.predecessor[last.x][last.y] != last){
            shortestPath_list.add(last);
            last = this.predecessor[last.x][last.y];
        }
        shortestPath_list.add(last);
        return shortestPath_list;
    }
    public double getDistance(Tuple destination){
        return this.distance[destination.x][destination.y];
    }
    
}
