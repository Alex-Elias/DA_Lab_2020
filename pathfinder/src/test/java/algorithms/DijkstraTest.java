
package algorithms;

import datastructures.NodeList;
import datastructures.Tuple;
import filereader.Filereader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for the Dijkstra class
 * @author alex
 */
public class DijkstraTest {
    /**
     * Filereader class used to read a file and return the maze array
     */
    private Filereader fr;
    
    
    
    /**
     * initializes the Filereader with the test map
     */
    @Before
    public void setUp() {
        this.fr = new Filereader("Mazes/test.map");
    }
    
    
    /**
     * tests if Dijkstra can find any path between two nodes 
     */
    @Test
    public void findPathSimpleMap(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0),new Tuple(3,0));
        NodeList list = dijk.getShortestPath();
        
        assertTrue(list.length() > 0);
    }
    /**
     * tests if Dijkstra can find the shortest path between two nodes on a simple map
     */
    @Test
    public void findShortestPathSimpleMap(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0), new Tuple(0,3));
        assertTrue(Math.abs(dijk.getDistance() - 18.31) < 0.01);
    }
    /**
     * tests if Dijkstra can find the shortest path between two node on a complex map
     */
    @Test
    public void findShortestPathComplex(){
        Filereader f = new Filereader("Mazes/maze512-8-0.map");
        Dijkstra dijk = new Dijkstra(f.returnArray());
        dijk.runDijkstra(new Tuple(2,2), new Tuple(511,511));
        assertTrue(Math.abs(dijk.getDistance() - 1447.241) < 0.001);
    }
    /**
     * tests if the Dijkstra fails when the starting node is invalid
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void invalidStartTest(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(-1,0), new Tuple(5,5));
    }
    /**
     * tests if Dijkstra fails when the destination is an obstacle
     */
    @Test(expected = NullPointerException.class)
    public void invalidTestObstacleAsDestination(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0), new Tuple(1,2));
        dijk.getShortestPath();
    }
    
    
}
