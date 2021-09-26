package pl.first.firstjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final File file;
    
    public FileSudokuBoardDao(String fileName) {
        file = new File(fileName);
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sb = null;
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
        
            sb = (SudokuBoard) ois.readObject();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(obj);
            
        } catch (IOException ex) {
            Logger.getLogger(FileSudokuBoardDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
    
    }
}
