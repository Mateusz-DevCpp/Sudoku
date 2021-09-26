package pl.first.firstjava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;

public class SudokuBoard implements Serializable,Cloneable {
    private SudokuField[][] board;
    public SudokuSolver solver;
    
    public SudokuBoard(SudokuSolver sudokuSolverTemp) {
        solver = sudokuSolverTemp;
        
        board = new SudokuField [9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = new SudokuField();
            }
        }
        
        LogSystem.getConfiguration().log(Level.INFO, "Create new clear board");
    }
    
    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }    
    
    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }
    
    public void solveGame() {
        solver.solve(this);
    }
    
    public SudokuRow getRow(int y) {
        SudokuField[] sf = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            sf[i] = new SudokuField();
            sf[i] = board[i][y];
        }
        SudokuRow sr = new SudokuRow(sf);
        return sr;
    }
    
    public SudokuColumn getColumn(int x) {
        SudokuField[] sf = new SudokuField [9];
        for (int i = 0; i < 9; i++) {
            sf[i] = new SudokuField();
            sf[i] = board[x][i];
        }
        SudokuColumn sc = new SudokuColumn(sf);
        return sc;
    }
   
    public SudokuBox getBox(int x, int y) {
        SudokuField[] sf = new SudokuField [9];
        for (int i = 0, a = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, a++) {
                sf[a] = board[(3 * x) + i][(3 * y) + j];
            }
        }
        SudokuBox sb = new SudokuBox(sf);
        return sb;
    }
    
    public void boardPrepare(int value) {
        Random generator = new Random();
        for (int i = 0; i < value;) {
            int x = generator.nextInt() % 9;
            int y = generator.nextInt() % 9;
            if (x < 0) {
                x = -x;
            }
            if (y < 0) {
                y = -y;
            }
            
            if (board[x][y].getFieldValue() != 0) {
                board[x][y].setFieldValue(0);
                i++;
            }
        }
    }
    
    @Override
    public String toString() {
        String text = "";
        
        for (int i = 0; i < 9; i++) {
            text += getRow(i).toString() + "\n";
        }
        
        LogSystem.getConfiguration().log(Level.INFO, "Convert board to string");
        return text;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuBoard other = (SudokuBoard) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

    @Override
    public SudokuBoard clone() {
        try {
            SudokuBoard result = (SudokuBoard) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
            }
        }
    }
