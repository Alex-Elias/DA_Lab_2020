
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * An implementation of the Dijkstra's algorithm
 * the algorithm finds the shortest path between to points of a weighted graph
 * the graph must be in the form of a 2d integer array with 1's representing obstacles and 0's representing open space
 * @author alex
 */

public class Dijkstra{
    double[][] distance;
    boolean[][] processed;
    int[][] maze;
    PriorityQueue<Node> queue;
    Tuple[][] predecessor;
    /**
     * the class gets initialized with 2d array
     * @param maze the 2d integer array with 1's representing obstacles and 0's representing open space
     */
    public Dijkstra(int[][] maze){
        this.distance = new double[maze.length][maze[0].length];
        this.processed = new boolean[maze.length][maze[0].length];
        this.maze = maze;
        this.queue = new PriorityQueue<Node>(new Node());
        this.predecessor = new Tuple[maze.length][maze[0].length];
        
    }
    /**
     * the method that runs the Dijkstra's algorithm
     * @param origin a two integer tuple that contains the x and y values of the origin the first value represents the row number and the second value represents the number of columns starting from 0
     */
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
    /**
     * a helper method to find the adjacent nodes used in the runDijkstra method
     * @param T the tuple coordinate which is used to find the adjacent nodes
     * @return an ArrayList of Class Node of the adjacent nodes
     */
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
    
    /**
     * a method which finds the path of the shortest path from the origin to the destination
     * @param destination the tuple coordinate of the desired destination of the shortest path
     * @return an ArrayList of tuple coordinates of the shortest path
     */
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
    /**
     * a getter method with returns the distance of the shortest path from the origin to the destination
     * @param destination a tuple with the coordinates of the desired destination, must be integer type
     * @return a double type value of the shortest path from the origin to the destination
     */
    public double getDistance(Tuple destination){
        return this.distance[destination.x][destination.y];
    }
    
}
