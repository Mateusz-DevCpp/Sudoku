package pl.first.firstjava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver,Serializable {
    
    @Override
    public void solve(SudokuBoard board) {
        solveSudoku(0,0,board);
    }

    private List<Integer> cyfry = Arrays.asList(new Integer[9]);
    
    public BacktrackingSudokuSolver() {
        for (int i = 1; i < 10; i++) {
            cyfry.set(i - 1, i);
        } 
        Collections.shuffle(cyfry);
    }
    
    private boolean solveSudoku(int wiersz, int kolumna, SudokuBoard sudoku) {
        if (wiersz == 8 && kolumna == 9) {
            return true;
        }

        if (kolumna == 9) { 
            wiersz++; 
            kolumna = 0; 
        }

        if (sudoku.get(wiersz, kolumna) != 0) {
            return solveSudoku(wiersz, kolumna + 1, sudoku); 
        }
  
        for (int cyfra: cyfry) { 
            if (!numberIsUsed(wiersz, kolumna, cyfra, sudoku)) { 
                sudoku.set(wiersz,kolumna,cyfra);
                  
                if (solveSudoku(wiersz, kolumna + 1, sudoku)) {
                    return true;
                } 
            } else {
                sudoku.set(wiersz, kolumna, 0);
            }
        }
        return false;
    }
    
    private boolean numberIsUsed(int wiersz, int kolumna, int cyfra, SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            if (sudoku.get(wiersz, i) == cyfra) {
                return true;
            } 
        }
        
        for (int i = 0; i < 9; i++) {
            if (sudoku.get(i, kolumna) == cyfra) {
                return true;
            } 
        }
  
        int poczatekKostkiWiersz = wiersz - wiersz % 3;
        int poczatekKostkiKolumna = kolumna - kolumna % 3; 
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku.get(poczatekKostkiWiersz + i, poczatekKostkiKolumna + j) == cyfra) {
                    return true;
                } 
            }
        }
        
        return false;
    }
}