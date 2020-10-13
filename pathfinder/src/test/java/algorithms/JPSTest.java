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
 *
 * @author alex
 */
public class JPSTest {
    
    private Filereader fr;
    
    @Before
    public void setUp(){
        this.fr = new Filereader("Mazes/test.map");
    }
    
    @Test
    public void findPathSimpleMap(){
       
        JPS jps = new JPS(fr.returnMaze());
        jps.run_JPS(new Node(new Tuple(0,0)), new Node(new Tuple(3,0)));
        NodeList list = jps.getShortestPath();
        
        assertTrue(list.length() > 0);
    }
    @Test
    public void findShortestPathSimpleMap(){
        JPS jps = new JPS(fr.returnMaze());
        jps.run_JPS(new Node(new Tuple(0,0)),new Node(new Tuple(0,3)));
        assertTrue(Math.abs(jps.getDistance() - 18.31) < 0.01);
    }
    @Test
    public void findShortestPathComplex(){
        Filereader f = new Filereader("Mazes/maze512-8-0.map");
        JPS jps = new JPS(f.returnArray());
        jps.run_JPS(new Node(new Tuple(2,2)), new Node(new Tuple(511,511)));
        assertTrue(Math.abs(jps.getDistance() - 1447.241) < 0.001);
    }
    
}
