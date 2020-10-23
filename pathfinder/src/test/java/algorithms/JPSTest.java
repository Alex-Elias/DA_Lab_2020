/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.Node;
import datastructures.NodeList;
import datastructures.Tuple;
import filereader.Filereader;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * JUnit tests for JPS class
 * @author alex
 */
public class JPSTest {
    /**
     * Filereader class used to read a file and return the maze array
     */
    private Filereader fr;
    /**
     * initializes the Filereader with the test map
     */
    @Before
    public void setUp(){
        this.fr = new Filereader("Mazes/test.map");
    }
    /**
     * tests if JPS can find any path between two nodes 
     */
    @Test
    public void findPathSimpleMap(){
       
        JPS jps = new JPS(fr.returnMaze());
        jps.runJPS(new Node(new Tuple(0,0)), new Node(new Tuple(3,0)));
        NodeList list = jps.getShortestPath();
        
        assertTrue(list.length() > 0);
    }
    /**
     * tests if JPS can find the shortest path between two nodes on a simple map
     */
    @Test
    public void findShortestPathSimpleMap(){
        JPS jps = new JPS(fr.returnMaze());
        jps.runJPS(new Node(new Tuple(0,0)),new Node(new Tuple(0,3)));
        assertTrue(Math.abs(jps.getDistance() - 18.31) < 0.01);
    }
    /**
     * tests if JPS can find the shortest path between two node on a complex map
     */
    @Test
    public void findShortestPathComplex(){
        Filereader f = new Filereader("Mazes/maze512-8-0.map");
        JPS jps = new JPS(f.returnArray());
        jps.runJPS(new Node(new Tuple(2,2)), new Node(new Tuple(511,511)));
        assertTrue(Math.abs(jps.getDistance() - 1447.241) < 0.001);
    }
    /**
     * tests if the JPS fails when the starting node is invalid
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void invalidStartTest(){
        JPS jps = new JPS(fr.returnMaze());
        jps.runJPS(new Node(new Tuple(-1,0)), new Node(new Tuple(5,5)));
    }
    /**
     * tests if JPS fails when the destination is an obstacle
     */
    @Test(expected = NullPointerException.class)
    public void InvalidTestObstacleAsDestination(){
        JPS jps = new JPS(fr.returnMaze());
        jps.runJPS(new Node(new Tuple(0,0)), new Node(new Tuple(1,2)));
        jps.getShortestPath();
    }
    
}
