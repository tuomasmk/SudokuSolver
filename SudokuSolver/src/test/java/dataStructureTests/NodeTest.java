package dataStructureTests;

import dataStructures.Node;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class NodeTest {
    Node node;
    
    @Before
    public void setUp() {
        node = new Node(1, 3, null);
    }
    
    @Test
    public void testNodeKey() {
        assertEquals(node.getKey(), 1);
    }
    
    @Test
    public void testNodeValue() {
        assertEquals(node.getValue(), 3);
    }
    
    @Test
    public void testNodeValueChange() {
        node.setValue(2);
        assertEquals(node.getValue(), 2);
    }
    
    @Test
    public void testSettingNext() {
        Node next = new Node(2, 4, null);
        node.setNext(next);
        assertEquals(node.getNext(), next);
    }

}
