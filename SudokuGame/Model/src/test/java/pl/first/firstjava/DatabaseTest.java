package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    
    public DatabaseTest() {
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
    public void connectTest() throws InvalidDatabaseException, Exception {
        try {
            JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getDatabaseDao("SudokuSave");
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            fail("Connection fail");
        }
    }
    
    @Test
    public void saveToDBTest() throws InvalidDatabaseException, Exception {
        SudokuBoard instance = new SudokuBoard(new BacktrackingSudokuSolver());
        instance.solveGame();
        
        try {
            JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getDatabaseDao("plansza_testowa");
            dao.write(instance);
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            fail("Write error");
        }
    }
    
    @Test
    public void loadFromDBTest() throws InvalidDatabaseException, Exception {
        SudokuBoard instance = null;
        
        try {
            JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getDatabaseDao("plansza_testowa");
            instance = dao.read();
        } catch (DaoException ex) {
            System.out.println(ex.getMessage());
            fail("Read error");
        }
        
        System.out.println(instance.toString());
    }
}
