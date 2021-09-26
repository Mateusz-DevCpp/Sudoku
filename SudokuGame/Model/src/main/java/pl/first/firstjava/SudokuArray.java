package pl.first.firstjava;

import java.io.Serializable;
import java.util.Arrays;

public class SudokuArray implements Serializable,Cloneable {
    private SudokuField[] wartosc;
    
    SudokuArray(SudokuField [] wartosci) {
        wartosc = wartosci;
    }
    
    public boolean verify() {
        int [] tmp = new int [9];
        for (int i = 0; i < 9; i++) {
            tmp[i] = i + 1;
        }
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (wartosc[i].getFieldValue() == tmp[j]) {
                    tmp[j] = 0;
                    break;
                }
            }
        }
        
        for (int i = 0; i < 9; i++) {
            if (tmp[i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String text = "[ ";
        
        for (int i = 0; i < 9; i++) {
            text += wartosc[i].toString() + " ";
        }
        text += "]";
        
        return text;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.deepHashCode(this.wartosc);
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
        final SudokuArray other = (SudokuArray) obj;
        return Arrays.deepEquals(this.wartosc, other.wartosc);
    }

    @Override
    public SudokuArray clone() {
        try {
            SudokuArray result = (SudokuArray) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
}
