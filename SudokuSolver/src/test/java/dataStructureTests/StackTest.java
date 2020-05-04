package dataStructureTests;

import dataStructures.IntStack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class StackTest {
    IntStack stack;
    
    @Before
    public void setUp() {
        stack = new IntStack();
    }
    
    @Test
    public void stackPushingAndPoping() {
        assertNotNull(stack);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
    }
    
    @Test
    public void stackIsEmpty() {
        assertTrue(stack.isEmpty());
        
    }
    
    @Test
    public void stackIsNotEmpty() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void stackBecomesEmpty() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void stackSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(1);
        assertEquals(2, stack.size());
        stack.push(1);
        assertEquals(3, stack.size());
    }
    
    @Test
    public void stackGrows() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        stack.push(11);
        assertEquals(11, stack.size());
    }
}
