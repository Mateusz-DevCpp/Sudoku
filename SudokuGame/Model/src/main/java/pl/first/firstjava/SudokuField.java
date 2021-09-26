package pl.first.firstjava;

import java.io.Serializable;

public class SudokuField implements Serializable,Cloneable,Comparable<SudokuField> {
    private int value;

    SudokuField() {
        value = 0;
    }
    
    public int getFieldValue() {
        return value;
    }
    
    public void setFieldValue(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.value;
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
            
        SudokuField x = (SudokuField) obj;
        return value == x.getFieldValue();
    }

    @Override
    public int compareTo(SudokuField o) {
        if (this.getFieldValue() < o.getFieldValue()) {
            return -1;
        } else if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public SudokuField clone() {
        try {
            SudokuField result = (SudokuField) super.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    
}
