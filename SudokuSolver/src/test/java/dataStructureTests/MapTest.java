package dataStructureTests;

import dataStructures.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class MapTest {
    Map map;
    
    @Before
    public void setUp() {
        map = new Map<>();
        map.put(1, 1);
        map.put(2, 4);
        map.put(3, 9);
        map.put(4, 16);
    }
    
    @Test
    public void testMyMap() {
        assertNotNull(map);
        //assertEquals(4, map.size());
        assertEquals(4, map.get(2));
        assertEquals(16, map.get(4));
    }
    
    @Test
    public void testClear() {
        map.clear();
        assertNull(map.get(2));
        assertNull(map.get(4));
    }
    
    @Test
    public void testContainsKey() {
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(4));
        assertFalse(map.containsKey(5));
    }
    
    @Test
    public void testMapSize() {
        assertEquals(128, map.size());
    }
    
    @Test
    public void testMapMultipleHitsOnSamePlace() {
        map.put(129, 5);
        assertEquals(5, map.get(129));
        assertEquals(1, map.get(1));
        map.put(1, 2);
        assertEquals(5, map.get(129));
        assertEquals(2, map.get(1));
    }

}
