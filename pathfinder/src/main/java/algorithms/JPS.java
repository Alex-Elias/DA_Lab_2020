package algorithms;


import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;
import datastructures.NodePriorityQueue;


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
    NodePriorityQueue queue;
    
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
    public void runJPS( Node start, Node goal){
        this.queue= new NodePriorityQueue();
        this.predecessor = new Node[maze.length][maze[0].length];
        
        this.jump_point = new int[maze.length][maze[0].length];
        this.visited = new boolean[maze.length][maze[0].length];
        start.setF(0);
        start.setParent(start);
        this.queue.insert(start,start.getF());
        this.predecessor[start.getX()][start.getY()] = start;
        

        
        while(!this.queue.isEmpty()){
            
            
            Node current = this.queue.deleteMin();
            if(this.visited[current.getX()][current.getY()]){
               continue; 
            }
            this.visited[current.getX()][current.getY()] = true;
            
            
            if(current.getX() == goal.getX() && current.getY() == goal.getY()){
                this.weight = current.getWeight();
                this.goal = current;
                super.setDestination(this.goal);
                super.setDistance(current.getWeight());
                break;
            }
            NodeList successor = get_successors(current,start,goal);
            while(!successor.isEmpty()){
                Node next = successor.remove();
                if(next== null){
                    continue;
                }
                this.jump_point[next.getX()][next.getY()] = 1;
                next.setF(next.getWeight() + heuristic(next,goal));
                this.queue.insert(next,next.getF());
            }


            
            
        }
        
        
    }
    /**
     * gets a tuple and returns a node with the tuples coordinates if the coordinates point to a valid place on the maze, otherwise returns null
     * @param T the tuple with the coordinates
     * @return a node with the coordinates of the tuple if the coordinates point to a valid place otherwise returns null
     */
    public Node get_valid_place(Tuple T){
        if(T.getX()< 0 || T.getY() < 0){
            return null;
        }
        if(T.getX()>=this.maze.length || T.getY() >= this.maze[0].length){
            return null;
        }
        if(this.maze[T.getX()][T.getY()] == 1){
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
                this.predecessor[jump.getX()][jump.getY()] = node;

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
        
        Tuple next = new Tuple(node.getX() + direction.getX(), node.getY() + direction.getY());
        Node n = get_valid_place(next);
        
        if(n == null){
            return null;
        }
        
        n.setParent(node);
        if(direction.getX() !=0 && direction.getY() !=0){
            n.setWeight(n.getParent().getWeight() + RootTwo);
        }else{
            n.setWeight(n.getParent().getWeight() + 1);
        }
        
        if(n.getX() == goal.getX() && n.getY() == goal.getY()){
            this.goal = n;
            return n;
        }
        NodeList neighbors = get_neighbor_pruned(n);
        
        while(!neighbors.isEmpty()){
            Node neighbor = neighbors.remove();
            if(neighbor== null){
                continue;
            }
            if(neighbor.isForced()){
                return n;
            }
        }
        if(direction.getX() !=0 && direction.getY() !=0){
            if( jump(n, new Tuple(direction.getX() ,0),start,goal) != null || jump(n, new Tuple(0,direction.getY() ),start,goal) != null){
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
        int dx = node.getX() - node.getParent().getX();
        int dy = node.getY() - node.getParent().getY();
        NodeList pruned = new NodeList();
        if(dx==0 && dy == 0){
            return getNeighbors(node);
        }
        
        if(dx == 0){
            if (is_valid(node.getX(), node.getY() + dy)){
                if(!is_valid(node.getX() +1, node.getY())){
                    Node forced = get_valid_place(new Tuple(node.getX() +1, node.getY() + dy));
                    if(forced != null){
                        
                        forced.setParent(node);
                        
                        forced.setForced(true);
                        pruned.add(forced);
                    }
                }
                if(!is_valid(node.getX() -1, node.getY())){
                    Node forced = get_valid_place(new Tuple(node.getX() -1, node.getY() + dy));
                    if(forced != null){
                        forced.setParent(node);
                        forced.setForced(true);
                        pruned.add(forced);
                    }
                }
                Node forced = get_valid_place(new Tuple(node.getX(), node.getY() + dy));
                forced.setParent(node);;
                pruned.add(forced);
            }
        }
        if( dy==0){
            if (is_valid(node.getX() + dx, node.getY())){
                if(!is_valid(node.getX(), node.getY() + 1)){
                    Node forced = get_valid_place(new Tuple(node.getX() +dx, node.getY() + 1));
                    if(forced != null){
                        forced.setParent(node);;
                        forced.setForced(true);
                        pruned.add(forced);
                    }
                }
                if(!is_valid(node.getX(), node.getY() - 1)){
                    Node forced = get_valid_place(new Tuple(node.getX() +dx, node.getY() -1));
                    if(forced != null){
                        forced.setParent(node);;
                        forced.setForced(true);
                        pruned.add(forced);
                    }
                }
                Node forced = get_valid_place(new Tuple(node.getX() + dx, node.getY() ));
                forced.setParent(node);;
                pruned.add(forced);
            }
        }
        if(dx != 0 && dy != 0){
            if(!is_valid(node.getX(), node.getY() + (dy * -1))){
                Node forced = get_valid_place(new Tuple(node.getX() + dx, node.getY() + (dy*-1)));
                if(forced !=null){
                    forced.setParent(node);;
                    forced.setForced(true);
                    pruned.add(forced);
                }
            }
            if(!is_valid(node.getX() + (dx*-1),node.getY())){
                Node forced = get_valid_place(new Tuple(node.getX() + (dx*-1), node.getY() + dy));
                if(forced != null){
                    forced.setParent(node);;
                    forced.setForced(true);
                    pruned.add(forced);
                }
            }
            Node temp = get_valid_place(new Tuple(node.getX()+dx, node.getY()+dy));
            if(temp!=null){
            temp.setParent(node);
            pruned.add(temp);
            }
            
            Node temp1 = get_valid_place(new Tuple(node.getX(), node.getY()+dy));
            if(temp1!=null){
            temp1.setParent(node);
            pruned.add(temp1);
            }
            
            Node temp2 = get_valid_place(new Tuple(node.getX()+dx, node.getY()));
            if(temp2!=null){
            temp2.setParent(node);
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
        int x = node.getX() -node.getParent().getX();
        int y = node.getY() -node.getParent().getY();
        return new Tuple(x,y);
    }
    
    


    
}
