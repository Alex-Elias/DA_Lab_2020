/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import algorithms.BFS;
import datastructures.Tuple;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alex
 */
public class TestBFS {
    private int maze4x4[][]= {{1,1,1,1},{1,0,1,0},{1,0,1,0},{1,0,0,0}};
    
    
    public TestBFS() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void tiny_test(){
        int[][] tiny_maze = {{1,0,0,1}};
        BFS tiny_bfs = new BFS(tiny_maze);
        tiny_bfs.runBFS(new Tuple(0,1));
        tiny_bfs.shortestpath(new Tuple(0,2));
        assertEquals(1,tiny_bfs.getDistance(new Tuple(0,2)));
        assertEquals(0,tiny_bfs.getDistance(new Tuple(0,1)));
    }
    
    @Test
    public void test_adjacencyList_singular(){
        int[][] test_maze = {{0}};
        BFS test_BFS = new BFS(test_maze);
        assertTrue(test_BFS.adjacencyList(new Tuple(0,0)).isEmpty());
    }
    
    @Test
    public void test_adjacencyList(){
        int [][] test_maze = {{1,1,1,0,0,0},{1,0,1,0,0,0},{1,1,1,0,0,0},{0,1,1,1,1,1,1},{0,0,0,0,0,1},{0,1,1,0,1,1}};
        BFS test_BFS = new BFS(test_maze);
        assertTrue(test_BFS.adjacencyList(new Tuple(1,1)).isEmpty());
        Tuple test_T = test_BFS.adjacencyList(new Tuple(3,0)).get(0);
        assertEquals(4,test_T.x);
        assertEquals(0,test_T.y);
        
        assertEquals("four adjacent nodes test failed",4,test_BFS.adjacencyList(new Tuple(1,4)).size());
        assertEquals("one adjacent nodes test failed",1,test_BFS.adjacencyList(new Tuple(5,0)).size());
        assertEquals("two adjacent nodes test failed",2,test_BFS.adjacencyList(new Tuple(4,2)).size());
        assertEquals("three adjacent nodes test failed",3,test_BFS.adjacencyList(new Tuple(1,3)).size());
        ArrayList<Tuple> test_nodes = new ArrayList<>(Arrays.asList(new Tuple(2,4), new Tuple(1,5), new Tuple(0,4), new Tuple(1,3)));
        Assert.assertEquals(test_nodes,test_BFS.adjacencyList(new Tuple(1,4)));
        
    }
    
    
    
    
    
}
