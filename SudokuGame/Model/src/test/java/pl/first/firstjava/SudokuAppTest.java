package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SudokuAppTest {
    
    public SudokuAppTest() {
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
    public void testApp() {
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        sb.solveGame();
        sb.boardPrepare(40);
        
        System.out.print(sb.toString());
        
        /*
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                System.out.print(sb.get(b, a) + " ");
            }
            System.out.print("\n");
        }
        sb.solveGame();
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                System.out.print(sb.get(b, a) + " ");
            }
            System.out.print("\n");
        }
        SudokuColumn sc = sb.getColumn(0);
        assertEquals(sc.verify(), true);
        sb.set(0, 0, 1);
        sb.set(0, 1, 1);
        sc = sb.getColumn(0);
        assertEquals(sc.verify(), false);
        */
    }
}
