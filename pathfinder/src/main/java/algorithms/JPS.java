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
 * the graph must be a 2d array where ones represent obstacles and zeros as open spaces
 * @author alex
 */
public class JPS extends Algorithm{
    /**
     * the 2d array that represents the maze
     */
    public int[][] maze;
   
    
    /**
     * the minimum priority queue that is used in JPS
     */
    NodePriorityQueue queue;
    
    /**
     * the array of jump point locations
     */
    public int[][] jumpPoints;
    
    /**
     * the array which stores whether the node has been processed or not
     */
    boolean[][] processed;
    /**
     * the value of root two
     */
    private final double RootTwo = 1.4142135;
    /**
     * the height of the maze
     */
    private int mazeHeight;
    /**
     * the length of the maze
     */
    private int mazeLength;
    
    /**
     * Initializes JPS
     * takes a 2d array as a parameter and initializes the Algorithm superclass with it and saves it to the class variable maze
     * 
     * @param maze a 2d array with ones as obstacles and zeros as open spaces
     */
    public JPS(int[][] maze){
        super(maze);
        this.maze = maze;
        this.mazeHeight = maze.length;
        this.mazeLength = maze[0].length;
        
        
    }
    /**
     * Runs the JPS algorithm
     * finds the shortest path between the start and goal node.
     * @param start a node which stores the position of the start
     * @param goal a node which stores the position of the goal
     */
    public void runJPS( Node start, Node goal){
        this.queue = new NodePriorityQueue();
        
        this.jumpPoints = new int[this.mazeHeight][this.mazeLength];
        this.processed = new boolean[this.mazeHeight][this.mazeLength];
        start.setF(0); // sets the priority to zero
        start.setParent(start);
        this.queue.insert(start,start.getF());
        

        
        while (!this.queue.isEmpty()){
            
            
            Node current = this.queue.deleteMin();
            if (this.processed[current.getX()][current.getY()]){
               continue; 
            }
            this.processed[current.getX()][current.getY()] = true;
            
            
            if (current.getX() == goal.getX() && current.getY() == goal.getY()){
                
                super.setDestination(current);
                super.setDistance(current.getWeight());
                break;
            }
            NodeList successor = getSuccessors(current,start,goal);
            while (!successor.isEmpty()){
                Node next = successor.remove();
                
                this.jumpPoints[next.getX()][next.getY()] = 1;
                next.setF(next.getWeight() + heuristic(next,goal)); // sets the priority
                this.queue.insert(next, next.getF());
            }


            
            
        }
        
        
    }
    /**
     * returns a node of the location if it is valid
     * gets a tuple and returns a node with the tuples coordinates if the coordinates point to a valid place on the maze, otherwise returns null
     * @param T the tuple with the coordinates
     * @return a node with the coordinates of the tuple if the coordinates point to a valid place otherwise returns null
     */
    public Node getLocationIfValid(Tuple T){
        if (T.getX() < 0 || T.getY() < 0){
            return null;
        }
        if (T.getX() >= this.mazeHeight || T.getY() >= this.mazeLength){
            return null;
        }
        if (this.maze[T.getX()][T.getY()] == 1){
            return null;
        }
        return new Node(T);
    }
    /**
     * returns true if the location is valid on the maze
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     * @return true or false depending if the location is valid or not
     */
    public boolean isValidLocation(int x, int y){
        if (x < 0 || y < 0){
            return false;
        }
        if (x >= this.mazeHeight || y >= this.mazeLength){
            return false;
        }
        if (this.maze[x][y] == 1){
            return false;
        }
        return true;
    }
    

    /**
     * returns a Nodelist of the successors to the current node
     * finds all the jump locations to the current node
     * @param current the current node
     * @param start the start node
     * @param goal the goal node
     * @return the list of successors to the current node
     */
    public NodeList getSuccessors(Node current, Node start, Node goal){
        NodeList successors = new NodeList();
        NodeList neighbors = getNeighborsPruned(current);
        while (!neighbors.isEmpty()){
            Node neighbor = neighbors.remove();
            
            
            Node jumpPoint = jump(current, getDirections(neighbor), start, goal);
            if (jumpPoint == null){
                continue;
            }
            successors.add(jumpPoint);
            
        }
        return successors;
    }
    /**
     * finds the jump points from the current node
     * @param current the current node
     * @param direction the direction to find the next jump point
     * @param start the starting node 
     * @param goal the goal node
     * @return the jump point as a node if found otherwise returns null
     */
    public Node jump(Node current, Tuple direction, Node start, Node goal){
        
        Tuple c = new Tuple(current.getX() + direction.getX(), current.getY() + direction.getY());
        Node next = getLocationIfValid(c);
        
        if (next == null){
            return null;
        }
        
        next.setParent(current);
        // if the next node is diagonal to the current node add root two to the weight
        if (direction.getX() != 0 && direction.getY() != 0){
            next.setWeight(next.getParent().getWeight() + RootTwo);
        }else{ // add 1 to the weight
            next.setWeight(next.getParent().getWeight() + 1);
        }
        // if next is the goal node return next
        if(next.getX() == goal.getX() && next.getY() == goal.getY()){
            
            return next;
        }
        // if next has a forced neighbor return next
        getNeighborsPruned(next);
        if (next.isForced()){
            return next;
        }
        
        // check the vertical and horizontal directions if the current direction is diagonal
        if(direction.getX() !=0 && direction.getY() !=0){
            if( jump(next, new Tuple(direction.getX() ,0),start,goal) != null || jump(next, new Tuple(0,direction.getY() ),start,goal) != null){
                return next;
            } 
        }
        
        
        
        
        
        return jump(next,direction,start,goal);
    }
    
    
    
    /**
     * returns a Nodelist of the pruned neighbors to the current node
     * finds all the valid neighbors and returns them as a list
     * @param current the current node which the neighbors are found
     * @return a list of nodes of the pruned neighbors
     */
    public NodeList getNeighborsPruned(Node current){
        // finds the direction from the parent to the current node
        int dx = current.getX() - current.getParent().getX();
        int dy = current.getY() - current.getParent().getY();
        NodeList pruned = new NodeList();
        // if the parent is the current node return just the neighbors
        if(dx==0 && dy == 0){
            return getNeighbors(current);
        }
        //if the direction is horizontal
        if(dx == 0){
            if (isValidLocation(current.getX(), current.getY() + dy)){
                if(!isValidLocation(current.getX() +1, current.getY())){
                    Node forced = getLocationIfValid(new Tuple(current.getX() +1, current.getY() + dy));
                    if(forced != null){
                        
                        forced.setParent(current);
                        current.setForced(true);
                        
                        pruned.add(forced);
                    }
                }
                if(!isValidLocation(current.getX() -1, current.getY())){
                    Node forced = getLocationIfValid(new Tuple(current.getX() -1, current.getY() + dy));
                    if(forced != null){
                        forced.setParent(current);
                        
                        current.setForced(true);
                        pruned.add(forced);
                    }
                }
                Node nonForced = getLocationIfValid(new Tuple(current.getX(), current.getY() + dy));
                if (nonForced != null){
                    nonForced.setParent(current);
                    pruned.add(nonForced);
                }
                
            }
        }
        // if the direction is vertical
        if( dy==0){
            if (isValidLocation(current.getX() + dx, current.getY())){
                if(!isValidLocation(current.getX(), current.getY() + 1)){
                    Node forced = getLocationIfValid(new Tuple(current.getX() +dx, current.getY() + 1));
                    if(forced != null){
                        forced.setParent(current);;
                        
                        current.setForced(true);
                        pruned.add(forced);
                    }
                }
                if(!isValidLocation(current.getX(), current.getY() - 1)){
                    Node forced = getLocationIfValid(new Tuple(current.getX() +dx, current.getY() -1));
                    if(forced != null){
                        forced.setParent(current);
                        
                        current.setForced(true);
                        pruned.add(forced);
                    }
                }
                Node nonForced = getLocationIfValid(new Tuple(current.getX() + dx, current.getY() ));
                if (nonForced != null){
                    nonForced.setParent(current);
                    pruned.add(nonForced);
                }
            }
        }
        // if the direction is diagonal
        if(dx != 0 && dy != 0){
            if(!isValidLocation(current.getX(), current.getY() + (dy * -1))){
                Node forced = getLocationIfValid(new Tuple(current.getX() + dx, current.getY() + (dy*-1)));
                if(forced !=null){
                    forced.setParent(current);
                    
                    current.setForced(true);
                    pruned.add(forced);
                }
            }
            if(!isValidLocation(current.getX() + (dx*-1),current.getY())){
                Node forced = getLocationIfValid(new Tuple(current.getX() + (dx*-1), current.getY() + dy));
                if(forced != null){
                    forced.setParent(current);
                   
                    current.setForced(true);
                    pruned.add(forced);
                }
            }
            Node nonForced = getLocationIfValid(new Tuple(current.getX()+dx, current.getY()+dy));
            if(nonForced!=null){
                nonForced.setParent(current);
                pruned.add(nonForced);
            }
            
            nonForced = getLocationIfValid(new Tuple(current.getX(), current.getY()+dy));
            if(nonForced!=null){
                nonForced.setParent(current);
                pruned.add(nonForced);
            }
            
            nonForced = getLocationIfValid(new Tuple(current.getX()+dx, current.getY()));
            if(nonForced!=null){
                nonForced.setParent(current);
                pruned.add(nonForced);
            }
            
        }
        return pruned;
        
    }
    
    
    
    /**
     * finds the direction from the parent to the node
     * @param node the node which the direction is found
     * @return the direction as a tuple
     */
    public Tuple getDirections(Node node){
        int x = node.getX() -node.getParent().getX();
        int y = node.getY() -node.getParent().getY();
        return new Tuple(x,y);
    }
    
    


    
}
