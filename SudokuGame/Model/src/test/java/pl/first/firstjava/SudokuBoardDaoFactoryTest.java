package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {
    
    public SudokuBoardDaoFactoryTest() {
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
    public void testGetFileDao() throws DaoException {
        System.out.println("getFileDao");
        String fileName = "plik.txt";
        Dao expResult = null;
        Dao result = SudokuBoardDaoFactory.getFileDao(fileName);
        assertNotEquals(result, expResult);
    }
    
}
