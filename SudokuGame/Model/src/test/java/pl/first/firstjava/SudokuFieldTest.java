package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {
    
    public SudokuFieldTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetFieldValue() {
        System.out.println("getFieldValue");
        SudokuField instance = new SudokuField();
        int expResult = 0;
        int result = instance.getFieldValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetFieldValue() {
        System.out.println("setFieldValue");
        int value = 0;
        SudokuField instance = new SudokuField();
        instance.setFieldValue(value);
        assertEquals(instance.getFieldValue(), value);
    }
    
    @Test
    public void testEquals() {
        SudokuField sf1 = new SudokuField();
        sf1.setFieldValue(1);
        
        SudokuField sf2 = new SudokuField();
        sf2.setFieldValue(1);
        
        SudokuField sf3 = new SudokuField();
        sf3.setFieldValue(2);
        
        SudokuField sf4 = null;
        
        SudokuBoard sf5 = new SudokuBoard(new BacktrackingSudokuSolver());
        
        assertEquals(sf1, sf1);
        assertEquals(sf1, sf2);
        assertNotEquals(sf1, sf3);
        assertNotEquals(sf1, sf4);
        assertNotEquals(sf1, sf5);
    }
    
    @Test
    public void testHashCode() {
        SudokuField sf1 = new SudokuField();
        sf1.setFieldValue(0);
        SudokuField sf2 = new SudokuField();
        sf2.setFieldValue(0);
        SudokuField sf3 = new SudokuField();
        sf3.setFieldValue(1);
        
        int hsf1 = sf1.hashCode();
        int hsf2 = sf2.hashCode();
        int hsf3 = sf3.hashCode();
        
        assertEquals(hsf1, hsf1);
        assertEquals(hsf1, hsf2);
        assertNotEquals(hsf1, hsf3);
    }
    
    @Test
    public void testCompareTo() {
        SudokuField sf1 = new SudokuField();
        SudokuField sf2 = new SudokuField();
        sf2.setFieldValue(1);
        
        sf1.setFieldValue(0);
        assertTrue(sf1.compareTo(sf2) == -1);
        
        sf1.setFieldValue(1);
        assertTrue(sf1.compareTo(sf2) == 0);
        
        sf1.setFieldValue(2);
        assertTrue(sf1.compareTo(sf2) == 1);
    }
}
