package pl.first.firstjava;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {
    
    public FileSudokuBoardDaoTest() {
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
    public void testDao() throws DaoException {
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        sb.solveGame();
        
        SudokuBoard sb2 = null;
        
        String tmp = "";
        String tmp2 = "";
        
        try (FileSudokuBoardDao dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao("test.txt")) {
            dao.write(sb);
            sb2 = dao.read();
        }
        tmp = sb.toString();
        tmp2 = sb2.toString();
        
        assertEquals(tmp, tmp2);
    }
    
    @Test
    public void testFileNotExist() throws DaoException {
        try (FileSudokuBoardDao dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao("TenPlikNieIstnieje.txt")) {
            SudokuBoard sb = dao.read();
        }
    }
    
    @Test
    public void testInvalidArg() throws DaoException {
        SudokuBoard sb = new SudokuBoard(new BacktrackingSudokuSolver());
        sb.solveGame();
        
        try (FileSudokuBoardDao dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao(null)) {
            dao.write(sb);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
