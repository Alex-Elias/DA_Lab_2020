
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;



public class BFS {
    boolean [][] visited;
    int [][] distance;
    int[][] maze;
    Tuple[][] predecessor;
    public BFS(int[][] maze){
        this.visited = new boolean[maze.length][maze[1].length];
        this.distance = new int  [maze.length][maze[1].length];
        this.predecessor = new Tuple[maze.length][maze[1].length];
        this.maze = maze;
    }
    
    public void runBFS(Tuple origin){
        for(int i = 0; i < this.maze.length; i++){
            for(int j = 0; j < this.maze[1].length; j++){
                this.visited[i][j]= false;
                this.distance[i][j] = 500000;
            }
            
            
        }
        this.visited[origin.x][origin.y] = true;
        this.distance[origin.x][origin.y] = 0;
        
        Queue<Tuple> q = new PriorityQueue<>();
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
    
    public ArrayList<Tuple> adjacencyList(Tuple T){
        ArrayList<Tuple> list = new ArrayList();
        if (T.x +1 < this.maze.length){
            if(this.maze[T.x + 1][T.y] ==0){
                list.add(new Tuple(T.x+1,T.y));
            }
        }
        if (T.y +1 < this.maze[1].length){
            if(this.maze[T.x][T.y +1] == 0){
                list.add(new Tuple(T.x,T.y+1));
            }
        }
        if (T.x -1 > 0){
            if (this.maze[T.x -1][T.y] == 0){
                list.add(new Tuple(T.x-1,T.y));
            }
        }
        if (T.y -1 > 0){
            if (this.maze[T.x][T.y-1] == 0){
                list.add(new Tuple(T.x,T.y-1));
            }
        }
        return list;
        
    }
}
