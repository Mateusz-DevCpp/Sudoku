package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SudokuBoardTest {
    
    public SudokuBoardTest() {
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
    public void testFillBoard() {
        System.out.println("Fill board");
        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        instance.solveGame();
        
        int[] example = new int [9];
        for (int i = 0; i < 9; i++) {
            example[i] = i+1;
        }
        
        int[] tmpArray = new int [9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmpArray[j] = instance.get(i, j);
            }
            Arrays.sort(tmpArray);
            assertArrayEquals(example, tmpArray);
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tmpArray[j] = instance.get(j, i);
            }
            Arrays.sort(tmpArray);
            assertArrayEquals(example, tmpArray);
        }
        
        for (int i = 0, x = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                for (int a = 0; a < 3; a++) {
                    for (int b = 0; b < 3; b++) {
                        tmpArray[x] = instance.get(i + a, j + b);
                        x++;
                    }
                }
                x = 0;
                Arrays.sort(tmpArray);
                assertArrayEquals(example, tmpArray);
            }
        }
    }

    @Test
    public void testGeneratedOtherBoard() {
        
        System.out.println("Generated other board");
        
        SudokuBoard sb1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sb1.solveGame();
        
        SudokuBoard sb2 = new SudokuBoard(new BacktrackingSudokuSolver());
        sb2.solveGame();

        petla1: for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sb1.get(i, j) != sb2.get(i, j)) {
                    break petla1;
                }
                if (i == 8 && j == 8) {
                    fail("Tablice sa takie same");
                }
            }
        }
    }
    
    @Test
    public void testGetter() {
        
        System.out.println("Getter");
        
        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (instance.get(i, j) != 0) {
                    fail("Getter zwrocil bledna wartosc");
                }
            }
        }
    }
    
    @Test
    public void testSetter() {
        
        System.out.println("Setter");
        
        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                instance.set(i, j, 1);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (instance.get(i, j) != 1) {
                    fail("Getter zwrocil bledna wartosc");
                }
            }
        }
    }
    
    @Test
    public void testEquals() {
        SudokuBoard sb1 = new SudokuBoard(new BacktrackingSudokuSolver());
        
        SudokuBoard sb2 = new SudokuBoard(new BacktrackingSudokuSolver());
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                sb2.set(x, y, sb1.get(x, y));
            }
        }
        
        SudokuBoard sb3 = new SudokuBoard(new BacktrackingSudokuSolver());
        sb3.solveGame();
        
        SudokuBoard sb4 = null;
        
        SudokuField sb5 = new SudokuField();
        
        assertEquals(sb1, sb1);
        assertEquals(sb1, sb2);
        assertNotEquals(sb1, sb3);
        
        if (sb1.equals(sb4)) {
            fail("sb1 (have value) == sb4 (null)");
        }
        
        assertNotEquals(sb1, sb5);
    }
    
    @Test
    public void testHashCode() {
        SudokuBoard sb1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sb1.solveGame();
        SudokuBoard sb2 = new SudokuBoard(new BacktrackingSudokuSolver());
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {
                sb2.set(x, y, sb1.get(x, y));
            }
        }
        SudokuBoard sb3 = new SudokuBoard(new BacktrackingSudokuSolver());
        
        int hsb1 = sb1.hashCode();
        int hsb2 = sb2.hashCode();
        int hsb3 = sb3.hashCode();
        
        assertEquals(hsb1, hsb1);
        assertEquals(hsb1, hsb2);
        assertNotEquals(hsb1, hsb3);
    }
    
    @Test
    public void testClone() {
        SudokuBoard sb1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sb1.solveGame();
        
        SudokuBoard sb2 = sb1.clone();
        
        assertEquals(sb1.toString(), sb2.toString());
        assertEquals(sb1, sb2);
        sb1.set(0, 0, 0);
        assertEquals(sb1, sb2);
    }
}
