/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * JUnit test for NodePriorityQueue class
 * @author alex
 */
public class NodePriorityQueueTest {
    /**
     * NodePriorityQueue class
     */
    private NodePriorityQueue queue;
    /**
     * initializes the NodePriorityQueue
     */
    @Before
    public void setUp(){
        this.queue = new NodePriorityQueue();
    }
    /**
     * tests if a node can be added to NodePriorityQueue  
     */
    @Test
    public void queueCanAddNode(){
        queue.insert(new Node(new Tuple(0,0)), 0);
        queue.insert(new Node(new Tuple(0,1)), 1);
        assertTrue(queue.getSize() == 2);
    }
    /**
     * tests if NodePriorityQueue returns the node with the least priority
     */
    @Test
    public void checkValueSimple(){
        queue.insert(new Node(new Tuple(0,0)), 0);
        queue.insert(new Node(new Tuple(2,3)), 5);
        queue.insert(new Node(new Tuple(0,9)), -2);
        queue.insert(new Node(new Tuple(2,2)), 8);
        assertTrue(queue.min().getX() == 0 && queue.min().getY() == 9);
    }
    /**
     * tests if NodePriorityQueue grows correctly 
     */
    @Test
    public void queueGrowsCorrectly(){
        for(int i = 0; i <1000; i++){
            queue.insert(new Node(new Tuple(0,0)), i);
        }
        assertTrue(queue.getSize() == 1000);
    }
    /**
     * test if NodePriorityQueue returns right values for a large test
     */
    @Test
    public void largeRandomTest(){
        Random r = new Random();
        for (int i = 0; i< 5000; i++){
            queue.insert(new Node(new Tuple(0,i)), r.nextInt(100));
        }
        
        assertTrue(increasingPriority(queue));
    }
    /**
     * method to see if NodePriorityQueue returns nodes with increasing priority 
     * @param q the NodePriorityQueue
     * @return boolean value whether the NodePriorityQueue removes the correct nodes
     */
    private boolean increasingPriority(NodePriorityQueue q){
        double last = q.deleteMin().getPriority();
        while(!q.isEmpty()){
            double temp = q.deleteMin().getPriority();
            if(last > temp){
                return false;
            }
            last = temp;
        }
        return true;
        
        
    }
}
