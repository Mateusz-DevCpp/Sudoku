package pl.first.firstjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    
    private String database = "jdbc:postgresql://localhost:5432/Sudoku";
    private Connection con;
    private Statement st = null;
    private ResultSet rs = null;
    
    private String boardName;
    
    public JdbcSudokuBoardDao(String tableName) throws InvalidDatabaseException, SQLException {
        
        boardName = tableName;
        
        try {
            con = DriverManager.getConnection(database, "postgres", "root");
        } catch (SQLException e) {
            throw new InvalidDatabaseException("connection error " + e.getMessage());
        }
        
        st = con.createStatement();
    }
    
    @Override
    public SudokuBoard read() {
        SudokuBoard obj = new SudokuBoard(new BacktrackingSudokuSolver());
        
        String sqlQuerry = "SELECT board FROM SudokuSaves WHERE name='" + boardName + "'";
        String sqlQuerryAnswer = "";
        
        try {
            rs = st.executeQuery(sqlQuerry);
            while (rs.next()) {
                sqlQuerryAnswer = rs.getString(1);
            }
            for (int y = 0, i = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++, i++) {
                    obj.set(x, y, sqlQuerryAnswer.charAt(i) - '0');
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JdbcSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }

    @Override
    public void write(SudokuBoard obj) {
        
        String buffor = "";
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                buffor += Integer.toString(obj.get(x, y));
            }
        }
        
        
        String sqlQuerry = "";
        sqlQuerry = "insert into SudokuSaves (\"name\", \"board\", \"mod\") "
                + "values ('" + boardName + "', '" + buffor + "', 'free')";
        
        try {
            st.executeUpdate(sqlQuerry);
        } catch (SQLException ex) {
            LogSystem.getConfiguration().log(Level.SEVERE, "Save to database - FAIL");
        }
        
    }

    @Override
    public void close() throws Exception {
        rs.close();
        st.close();
        con.close();
    }
    
}
