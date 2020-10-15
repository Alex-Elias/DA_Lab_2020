/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author alex
 */
public class NodeListTest {
    private NodeList list;
    
    @Before
    public void setUp(){
        this.list = new NodeList();
    }
    
    @Test
    public void canAddNode(){
        list.add(new Node(new Tuple(0,0)));
        list.add(new Node(new Tuple(0,1)));
        assertTrue(list.length() == 2);
    }
    @Test
    public void listIncreasesCorrectly(){
        for (int i = 0; i< 1000; i++){
            list.add(new Node(new Tuple(0,i),i));
        }
        assertTrue(list.length() == 1000);
    }
    @Test
    public void listDecreaseCorrectly(){
        for(int i = 0; i< 1000; i++){
            list.add(new Node(new Tuple(0,i),i));
        }
        while(!list.isEmpty()){
            list.remove();
        }
        assertTrue(list.length() ==0);
    }
    @Test
    public void removeFromEmptyList(){
        assertTrue(list.remove() == null);
    }
}
