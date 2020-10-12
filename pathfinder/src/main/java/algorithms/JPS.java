package algorithms;


import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;
import datastructures.PriorityQueue;


/**
 *  
 * implementation of the Jump Point Search.
 * JPS finds the shortest path between two nodes on an evenly weighted graph.
 * JPS is an optimization of the A Star algorithm, by pruning certain nodes and creating jump points, JPS reduces A Star's running time. 
 * 
 * @author alex
 */
public class JPS extends Algorithm{

    public int[][] maze;
    public Node start;
    public Node goal;
    PriorityQueue queue;
    
    Node[][] predecessor;
    public int[][] jump_point;
    public double weight;
    boolean[][] visited;
    
    private final double RootTwo = 1.4142135;
    
    /**
     * Initializes JPS
     * 
     * @param maze a 2d array with ones as obstacles and zeros as open spaces
     */
    public JPS(int[][] maze){
        super(maze);
        this.maze = maze;
        
        
    }
    /**
     * Runs JPS to find the shortest path between the start and goal node.
     * @param start a node which stores the position of the start
     * @param goal a node which stores the position of the goal
     */
    public void run_JPS( Node start, Node goal){
        this.queue= new PriorityQueue();
        this.predecessor = new Node[maze.length][maze[0].length];
        
        this.jump_point = new int[maze.length][maze[0].length];
        this.visited = new boolean[maze.length][maze[0].length];
        start.f=0;
        start.parent=start;
        this.queue.insert(start,start.f);
        this.predecessor[start.coordinates.x][start.coordinates.y] = start;
        

        
        while(!this.queue.isEmpty()){
            
            
            Node current = this.queue.deleteMin();
            if(this.visited[current.coordinates.x][current.coordinates.y]){
               continue; 
            }
            this.visited[current.coordinates.x][current.coordinates.y] = true;
            
            
            if(current.coordinates.x == goal.coordinates.x && current.coordinates.y == goal.coordinates.y){
                this.weight = current.weight;
                this.goal = current;
                super.setDestination(this.goal);
                break;
            }
            NodeList successor = get_successors(current,start,goal);
            while(!successor.isEmpty()){
                Node next = successor.remove();
                if(next== null){
                    continue;
                }
                this.jump_point[next.coordinates.x][next.coordinates.y] = 1;
                next.f = next.weight + heuristic(next,goal);
                this.queue.insert(next,next.f);
            }


            
            
        }
        
        
    }
    /**
     * gets a tuple and returns a node with the tuples coordinates if the coordinates point to a valid place on the maze, otherwise returns null
     * @param T the tuple with the coordinates
     * @return a node with the coordinates of the tuple if the coordinates point to a valid place otherwise returns null
     */
    public Node get_valid_place(Tuple T){
        if(T.x< 0 || T.y < 0){
            return null;
        }
        if(T.x>=this.maze.length || T.y >= this.maze[0].length){
            return null;
        }
        if(this.maze[T.x][T.y] == 1){
            return null;
        }
        return new Node(T);
    }
    public boolean is_valid(int x, int y){
        if(x< 0 || y < 0){
            return false;
        }
        if(x>=this.maze.length || y >= this.maze[0].length){
            return false;
        }
        if(this.maze[x][y] == 1){
            return false;
        }
        return true;
    }
    

    /**
     * finds a list of successors to the current node
     * @param node the current node
     * @param start the start node
     * @param goal the goal node
     * @return the list of successors to the current node
     */
    public NodeList get_successors(Node node, Node start, Node goal){
        NodeList successors = new NodeList();
        NodeList neighbors = get_neighbor_pruned(node);
        while(!neighbors.isEmpty()){
            Node neighbor = neighbors.remove();
            
            if(neighbor== null){
                continue;
            }
            Node jump = jump(node, get_directions(neighbor),start,goal);
            if(jump != null){
                //need to implement distance and heuristics here or possibly in the jump method
            }
            successors.add(jump);
            if(jump!= null){
                this.predecessor[jump.coordinates.x][jump.coordinates.y] = node;

            }
        }
        return successors;
    }
    /**
     * finds the jump points from the current node
     * @param node the current node
     * @param direction the direction to find the next jump point
     * @param start the starting node 
     * @param goal the goal node
     * @return the jump point as a node if found otherwise returns null
     */
    public Node jump(Node node, Tuple direction, Node start, Node goal){
        
        Tuple next = new Tuple(node.coordinates.x + direction.x, node.coordinates.y + direction.y);
        Node n = get_valid_place(next);
        
        if(n == null){
            return null;
        }
        n.parent = node;
        if(direction.x !=0 && direction.y !=0){
            n.weight = n.parent.weight + RootTwo;
        }else{
            n.weight = n.parent.weight + 1;
        }
        
        if(n.coordinates.x == goal.coordinates.x && n.coordinates.y == goal.coordinates.y){
            this.goal = n;
            return n;
        }
        NodeList neighbors = get_neighbor_pruned(n);
        
        while(!neighbors.isEmpty()){
            Node neighbor = neighbors.remove();
            if(neighbor== null){
                continue;
            }
            if(neighbor.forced){
                return n;
            }
        }
        if(direction.x !=0 && direction.y !=0){
            if( jump(n, new Tuple(direction.x,0),start,goal) != null || jump(n, new Tuple(0,direction.y),start,goal) != null){
                return n;
            } 
        }
        
        
        
        
        
        return jump(n,direction,start,goal);
    }
    
    
    
    /**
     * finds the pruned list of the neighbors of a certain node
     * @param node
     * @return a list of nodes of the pruned neighbors
     */
    public NodeList get_neighbor_pruned(Node node){
        int dx = node.coordinates.x - node.parent.coordinates.x;
        int dy = node.coordinates.y - node.parent.coordinates.y;
        NodeList pruned = new NodeList();
        if(dx==0 && dy == 0){
            return getNeighbors(node);
        }
        
        if(dx == 0){
            if (is_valid(node.coordinates.x, node.coordinates.y + dy)){
                if(!is_valid(node.coordinates.x +1, node.coordinates.y)){
                    Node forced = get_valid_place(new Tuple(node.coordinates.x +1, node.coordinates.y + dy));
                    if(forced != null){
                        forced.parent = node;
                        forced.forced =true;
                        pruned.add(forced);
                    }
                }
                if(!is_valid(node.coordinates.x -1, node.coordinates.y)){
                    Node forced = get_valid_place(new Tuple(node.coordinates.x -1, node.coordinates.y + dy));
                    if(forced != null){
                        forced.parent = node;
                        forced.forced=true;
                        pruned.add(forced);
                    }
                }
                Node forced = get_valid_place(new Tuple(node.coordinates.x, node.coordinates.y + dy));
                forced.parent = node;
                pruned.add(forced);
            }
        }
        if( dy==0){
            if (is_valid(node.coordinates.x + dx, node.coordinates.y)){
                if(!is_valid(node.coordinates.x, node.coordinates.y + 1)){
                    Node forced = get_valid_place(new Tuple(node.coordinates.x +dx, node.coordinates.y + 1));
                    if(forced != null){
                        forced.parent = node;
                        forced.forced =true;
                        pruned.add(forced);
                    }
                }
                if(!is_valid(node.coordinates.x, node.coordinates.y - 1)){
                    Node forced = get_valid_place(new Tuple(node.coordinates.x +dx, node.coordinates.y -1));
                    if(forced != null){
                        forced.parent = node;
                        forced.forced=true;
                        pruned.add(forced);
                    }
                }
                Node forced = get_valid_place(new Tuple(node.coordinates.x + dx, node.coordinates.y ));
                forced.parent = node;
                pruned.add(forced);
            }
        }
        if(dx != 0 && dy != 0){
            if(!is_valid(node.coordinates.x, node.coordinates.y + (dy * -1))){
                Node forced = get_valid_place(new Tuple(node.coordinates.x + dx, node.coordinates.y + (dy*-1)));
                if(forced !=null){
                    forced.parent = node;
                    forced.forced=true;
                    pruned.add(forced);
                }
            }
            if(!is_valid(node.coordinates.x + (dx*-1),node.coordinates.y)){
                Node forced = get_valid_place(new Tuple(node.coordinates.x + (dx*-1), node.coordinates.y + dy));
                if(forced != null){
                    forced.parent=node;
                    forced.forced=true;
                    pruned.add(forced);
                }
            }
            Node temp = get_valid_place(new Tuple(node.coordinates.x+dx, node.coordinates.y+dy));
            if(temp!=null){
            temp.parent=node;
            pruned.add(temp);
            }
            
            Node temp1 = get_valid_place(new Tuple(node.coordinates.x, node.coordinates.y+dy));
            if(temp1!=null){
            temp1.parent=node; 
            pruned.add(temp1);
            }
            
            Node temp2 = get_valid_place(new Tuple(node.coordinates.x+dx, node.coordinates.y));
            if(temp2!=null){
            temp2.parent=node; 
            pruned.add(temp2);
            }
            
        }
        return pruned;
        
    }
    
    
    
    /**
     * finds the direction from the parent to the node
     * @param node 
     * @return the direction as a tuple
     */
    public Tuple get_directions(Node node){
        int x = node.coordinates.x -node.parent.coordinates.x;
        int y = node.coordinates.y -node.parent.coordinates.y;
        return new Tuple(x,y);
    }
    
    


    
}
