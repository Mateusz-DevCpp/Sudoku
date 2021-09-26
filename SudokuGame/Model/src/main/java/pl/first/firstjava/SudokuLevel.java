package pl.first.firstjava;

public enum SudokuLevel {
    EASY,
    MEDIUM, 
    HARD;
    
    public static int getLevelInt(SudokuLevel sl) {
        if (sl == EASY) {
            return 40;
        } else if (sl == MEDIUM) {
            return 50;
        } else if (sl == HARD) {
            return 60;
        } else {
            return 0;
        }
    }
}
