/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author alex
 */
public class DijkstraTest {
    private Filereader fr;
    
    
    
    
    @Before
    public void setUp() {
        this.fr = new Filereader("Mazes/test.map");
    }
    
    

    @Test
    public void findPathSimpleMap(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0),new Tuple(3,0));
        NodeList list = dijk.getShortestPath();
        
        assertTrue(list.length() > 0);
    }
    
    @Test
    public void findShortestPathSimpleMap(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0), new Tuple(0,3));
        assertTrue(Math.abs(dijk.getDistance() - 18.31) < 0.01);
    }
    @Test
    public void findShortestPathComplex(){
        Filereader f = new Filereader("Mazes/maze512-8-0.map");
        Dijkstra dijk = new Dijkstra(f.returnArray());
        dijk.runDijkstra(new Tuple(2,2), new Tuple(511,511));
        assertTrue(Math.abs(dijk.getDistance() - 1447.241) < 0.001);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void invalidStartTest(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(-1,0), new Tuple(5,5));
    }
    @Test(expected = NullPointerException.class)
    public void invalidTestObstacleAsDestination(){
        Dijkstra dijk = new Dijkstra(fr.returnMaze());
        dijk.runDijkstra(new Tuple(0,0), new Tuple(1,2));
        dijk.getShortestPath();
    }
    
    
}
