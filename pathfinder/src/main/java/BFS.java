/**
 * An implementation of the breadth first search algorithm
 * the algorithm finds the shortest path between two points on two dimensional array
 * the two dimensional array must contain only 0's and 1's
 * the 1's represent obstacles where the algorithm must go around 
 * 
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class BFS {
    
    boolean [][] visited;
    int [][] distance;
    int[][] maze;
    Tuple[][] predecessor;
    ArrayList<Tuple> path;
    
    /**
     * initializes this class
     * @param maze 2d array, must be integer type and only consists of 1's and 0's
     */
    public BFS(int[][] maze){
        
        this.visited = new boolean[maze.length][maze[0].length];
        this.distance = new int  [maze.length][maze[0].length];
        this.predecessor = new Tuple[maze.length][maze[0].length];
        this.maze = maze;
    }
    /**
     * Runs the breadth first search algorithm 
     * @param origin the coordinates of the starting location of the BFS algorithm
     *               must be integer type, the first element is the row number and the second element is the column number
     */
    public void runBFS(Tuple origin){
        for(int i = 0; i < this.maze.length; i++){
            for(int j = 0; j < this.maze[0].length; j++){
                this.visited[i][j]= false;
                this.distance[i][j] = 2147483647;
            }
            
            
        }
        this.visited[origin.x][origin.y] = true;
        this.distance[origin.x][origin.y] = 0;
        
        Queue<Tuple> q = new LinkedList<>();
        q.add(origin);
        while(!q.isEmpty()){
            Tuple v = q.poll();
            for (Tuple s: adjacencyList(v)){
                if (!this.visited[s.x][s.y]){
                    this.visited[s.x][s.y] = true;
                    this.distance[s.x][s.y] = distance[v.x][v.y] +1;
                    this.predecessor[s.x][s.y] = v;
                    q.add(s);
                }
            }
        }
    }
    /**
     * creates an adjacencyList used in the runBFS method
     * @param T the coordinate tuple
     * @return an ArrayList of type Tuple of all the available nodes from coordinates T
     */
    public ArrayList<Tuple> adjacencyList(Tuple T){
        ArrayList<Tuple> list = new ArrayList();
        if (T.x +1 < this.maze.length){
            if(this.maze[T.x + 1][T.y] ==0){
                list.add(new Tuple(T.x+1,T.y));
            }
        }
        if (T.y +1 < this.maze[0].length){
            if(this.maze[T.x][T.y +1] == 0){
                list.add(new Tuple(T.x,T.y+1));
            }
        }
        if (T.x -1 >= 0){
            if (this.maze[T.x -1][T.y] == 0){
                list.add(new Tuple(T.x-1,T.y));
            }
        }
        if (T.y -1 >= 0){
            if (this.maze[T.x][T.y-1] == 0){
                list.add(new Tuple(T.x,T.y-1));
            }
        }
        return list;
        
    }
    /**
     * finds the route of the shortest path and saves it to the class variable Path
     * @param end the coordinate tuple of the desired destination
     */
    public void shortestpath(Tuple end){
        Tuple current_node = end;
        Queue<Tuple> Q = new LinkedList<>();
        ArrayList<Tuple> Path = new ArrayList<>();
        while(this.distance[this.predecessor[current_node.x][current_node.y].x][this.predecessor[current_node.x][current_node.y].y] != 0){
            Q.add(current_node);
            current_node = this.predecessor[current_node.x][current_node.y];
        }
        while(!Q.isEmpty()){
            Path.add(Q.poll());
        }
        this.path = Path;
    }
    /**
     * a getter class
     * @return the distance matrix
     */
    public int [][] getDistanceMatrix(){
        return this.distance;
    }
    /**
     * a getter class which returns the distance from the origin as an integer
     * @param destination the desired destination 
     * @return the distance from the origin to the destination as an integer
     */
    public int getDistance(Tuple destination){
        return this.distance[destination.x][destination.y];
    }
    
}
