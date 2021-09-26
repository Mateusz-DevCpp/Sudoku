package pl.first.firstjava;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {
    }
  
    public static Dao<SudokuBoard> getFileDao(String fileName) throws DaoException {
        if (fileName == null) {
            throw new InvalidFileException("NULL file");
        }
        
        Dao<SudokuBoard> dao = new FileSudokuBoardDao(fileName);
        return dao;
    }
    
    public static Dao<SudokuBoard> getDatabaseDao(String baseName) throws Exception {
        if (baseName == null) {
            throw new InvalidDatabaseException("NULL base");
        }
        
        Dao<SudokuBoard> dao;
        try {
            dao = new JdbcSudokuBoardDao(baseName);
        } catch (Exception e) {
            throw e;
        }
        
        return dao;
    }
}
