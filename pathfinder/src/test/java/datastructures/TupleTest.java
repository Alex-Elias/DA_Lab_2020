
package datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for Tuple class
 * @author alex
 */
public class TupleTest {
    
    @Test
    public void equalWorksCorrectly(){
        Tuple t1 = new Tuple(1,0);
        Tuple t2 = new Tuple(0,1);
        Tuple t3 = new Tuple(0,0);
        Tuple t4 = new Tuple(1,0);
        
        assertTrue(t1.equals(new Tuple(1,0)));
        assertFalse(t3.equals(t1));
        assertTrue(t1.equals(t4));
        assertFalse(t2.equals(new Node(new Tuple(0,1))));
        assertTrue(t1.equals(t1));
        
    }
    @Test
    public void toStringWorksCorrectly(){
        Tuple t = new Tuple(3,4);
        assertTrue(t.toString().equals("X = 3, Y = 4"));
    }
    
}
