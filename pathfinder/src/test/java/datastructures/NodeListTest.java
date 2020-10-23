
package datastructures;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * JUnit test for the NodeList class
 * @author alex
 */
public class NodeListTest {
    /**
     * NodeList class
     */
    private NodeList list;
    /**
     * initializes the NodeList class
     */
    @Before
    public void setUp(){
        this.list = new NodeList();
    }
    /**
     * tests if NodeList can add a node
     */
    @Test
    public void canAddNode(){
        list.add(new Node(new Tuple(0,0)));
        list.add(new Node(new Tuple(0,1)));
        assertTrue(list.length() == 2);
    }
    /**
     * tests if NodeLists adds nodes correctly
     */
    @Test
    public void listIncreasesCorrectly(){
        for (int i = 0; i< 1000; i++){
            list.add(new Node(new Tuple(0,i),i));
        }
        assertTrue(list.length() == 1000);
    }
    /**
     * tests if NodeList removes nodes correctly
     */
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
    /**
     * tests if removing from an empty list returns null
     */
    @Test
    public void removeFromEmptyList(){
        assertTrue(list.remove() == null);
    }
}
