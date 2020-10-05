/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import algorithms.Dijkstra;
import datastructures.Tuple;
import datastructures.Node;
import java.util.ArrayList;
import java.util.Arrays;
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
    int[][] maze2x2 = {{0,0},{0,0}};
    int[][] maze4x4 = {{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
    
    public DijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of runDijkstra method, of class Dijkstra.
     */
//    @Test
//    public void testRunDijkstra() {
//        System.out.println("runDijkstra");
//        Tuple origin = new Tuple(0,0);
//        Dijkstra instance = new Dijkstra(this.maze2x2);
//        instance.runDijkstra(origin);
//        // TODO review the generated test code and remove the default call to fail.
//        
//        assertTrue(Math.abs(1.4142135 - instance.distance[1][1])< 0.00001);
//    }

    /**
     * Test of adjacencyList method, of class Dijkstra.
     */
    @Test
    public void testAdjacencyList() {
        System.out.println("adjacencyList");
        Tuple T = new Tuple(0,0);
        Dijkstra instance = new Dijkstra(maze2x2);
        ArrayList<Node> expResult = new ArrayList<>(Arrays.asList(new Node(new Tuple(1,0),1.0),new Node(new Tuple(0,1),1.0),new Node(new Tuple(1,1),1.4142135)));
        ArrayList<Node> result = instance.adjacencyList(T);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getShortestPath method, of class Dijkstra.
     */
//    @Test
//    public void testGetShortestPath() {
//        System.out.println("getShortestPath");
//        Tuple destination = new Tuple(0,3);
//        Dijkstra instance = new Dijkstra(maze4x4);
//        instance.runDijkstra(new Tuple(0,1));
//        ArrayList<Tuple> expResult =  new ArrayList<>(Arrays.asList(new Tuple(0,3),new Tuple(1,3),new Tuple(2,3),new Tuple(3,2),new Tuple(2,1),new Tuple(1,1),new Tuple(0,1)));
//        ArrayList<Tuple> result = instance.getShortestPath(destination);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        
//    }

    /**
     * Test of getDistance method, of class Dijkstra.
     */
//    @Test
//    public void testGetDistance() {
//        System.out.println("getDistance");
//        Tuple destination = null;
//        Dijkstra instance = null;
//        double expResult = 0.0;
//        double result = instance.getDistance(destination);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
