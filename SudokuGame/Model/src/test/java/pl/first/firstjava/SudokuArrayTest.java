package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuArrayTest {
    
    public SudokuArrayTest() {
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
    public void testVerifyRow() {
        System.out.println("verify row");

        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            SudokuRow sr = instance.getRow(i);
            assertEquals(sr.verify(), false); 
        }
        
        instance.solveGame();
        for (int i = 0; i < 9; i++) {
            SudokuRow sr = instance.getRow(i);
            assertEquals(sr.verify(), true); 
        }
    }
    
    @Test
    public void testVerifyColumn() {
        System.out.println("verify column");

        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            SudokuColumn sr = instance.getColumn(i);
            assertEquals(sr.verify(), false); 
        }
        
        instance.solveGame();
        for (int i = 0; i < 9; i++) {
            SudokuColumn sr = instance.getColumn(i);
            assertEquals(sr.verify(), true); 
        }
    }
    
    @Test
    public void testVerifyBox() {
        System.out.println("verify box");

        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuBox sbx = instance.getBox(i,j);
                assertEquals(sbx.verify(), false); 
            }
        }
        
        instance.solveGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SudokuBox sbx = instance.getBox(i,j);
                assertEquals(sbx.verify(), true); 
            }
        }
    }
    
    @Test
    public void testEquals() {
        SudokuField[] tmp1 = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            tmp1[i] = new SudokuField();
            tmp1[i].setFieldValue(i+1);
        }
        SudokuField[] tmp2 = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            tmp2[8-i] = new SudokuField();
            tmp2[8-i].setFieldValue(i+1);
        }
        SudokuArray sa1 = new SudokuArray(tmp1);
        SudokuArray sa2 = new SudokuArray(tmp2);
        
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        
        assertEquals(sa1, sa1);
        assertNotEquals(sa1, sa2);
        assertNotEquals(sa1, null);
        assertNotEquals(sa1, sb);
    }
    
    @Test
    public void testHashCode() {
        SudokuField[] tmp1 = new SudokuField [9];
        SudokuField[] tmp3 = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            tmp1[i] = new SudokuField();
            tmp1[i].setFieldValue(i+1);
            
            tmp3[i] = new SudokuField();
            tmp3[i].setFieldValue(i+1);
        }
        SudokuField[] tmp2 = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            tmp2[8-i] = new SudokuField();
            tmp2[8-i].setFieldValue(i+1);
        }
        
        SudokuArray sa1 = new SudokuArray(tmp1);
        SudokuArray sa2 = new SudokuArray(tmp2);
        SudokuArray sa3 = new SudokuArray(tmp3);
        
        int hsb1 = sa1.hashCode();
        int hsb3 = sa3.hashCode();
        int hsb2 = sa2.hashCode();
        
        assertEquals(hsb1, hsb1);
        assertEquals(hsb1, hsb3);
        assertNotEquals(hsb1, hsb2);
    }
}
