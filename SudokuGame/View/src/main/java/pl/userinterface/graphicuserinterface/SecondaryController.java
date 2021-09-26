package pl.userinterface.graphicuserinterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import pl.first.firstjava.*;

public class SecondaryController implements Initializable{
    
    private static SudokuLevel level = SudokuLevel.EASY;
    private SudokuBoard board = null;
    
    @FXML Button id_button_exit;
    @FXML Button id_button_load;
    @FXML Button id_button_save;
    @FXML Button id_button_load_db;
    @FXML Button id_button_save_db;
    
    public static void setLevel(SudokuLevel sl) {
        level = sl;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.log.info("Initialize game site");
        board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        board.boardPrepare(SudokuLevel.getLevelInt(level));
        App.log.info("Created new board");
        loadValues();
        setFirstField();
    }
    
    private void loadValues() {
        enableAll();
        String txt = " ";
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                int value = board.get(j,i);
                if(value != 0) {
                    txt = Integer.toString(value);
                    getButton(i*9+j).setDisable(true);
                } else {
                    txt = " ";
                }
                getButton(i*9+j).setText(txt);
            }
        }
    }
    
    private void setFirstField() {
        for (int i = 0; i < 81; i++) {
            if (!getButton(i).isDisable()) {
                focusedButton = getButton(i);
                break;
            }
        }
    }
    
    private void enableAll() {
        for (int i = 0; i < 81; i++) {
            if (getButton(i).isDisable()) {
                getButton(i).setDisable(false);
            }
        }
    }
    
    @FXML
    Button focusedButton;
    
    @FXML
    private void pressButton(ActionEvent event) throws IOException {
        focusedButton = (Button) event.getSource();
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.log.info("Exit from board");
        App.setRoot("primary");
    }
    
    @FXML
    private void pressKey(KeyEvent key) throws IOException {
        char znak = key.getText().charAt(0);
        if (znak >= '0' && znak <= '9') {
            focusedButton.setText(key.getText());
            
            for (int i = 0; i < 81; i++) {
                if (focusedButton == getButton(i)) {
                    int y = Math.floorDiv(i, 9);
                    int x = i%9;
                    board.set(x, y, znak-'0');
                    break;
                }
            }
        }
    }
    
    @FXML
    private void button_save() throws IOException, DaoException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save_as");
        File file = fileChooser.showSaveDialog(null);
        
        try (FileSudokuBoardDao dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao(file.getPath())) {
            dao.write(board);
            
            String mod = "";
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if (getButton(i*9+j).isDisable()) {
                        mod += "f";
                    } else {
                        mod += "t";
                    }
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()+"mod"))) {
                writer.write(mod);
            }
        }
        App.log.info("Save sudoku board to file");
    }
    
    @FXML
    private void button_load() throws FileNotFoundException, IOException, DaoException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("load_from");
        File file = fileChooser.showOpenDialog(null);
        
        try (FileSudokuBoardDao dao = (FileSudokuBoardDao) SudokuBoardDaoFactory.getFileDao(file.getPath())) {
            board = dao.read();
            loadValues();
           
            String mod = "";
            try {
                File myObj = new File(file.getPath()+"mod");
                try (Scanner myReader = new Scanner(myObj)) {
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        mod = data;
                    }
                }
            } catch (FileNotFoundException e) {
                App.log.severe("File not found");
            }
            enableAll();
            for(int a = 0; a < 81; a++) {
                if (mod.charAt(a) == 'f') {
                    getButton(a).setDisable(true);
                }
            }
        }
        App.log.info("Load sudoku board from file");
    }
    
    
    @FXML
    private void button_save_db() throws Exception {
        TextInputDialog dialog = new TextInputDialog();
 
        dialog.setTitle("Zapis do bazy danych");
        dialog.setHeaderText("Podaj nazwe planszy");
        dialog.setContentText("Nazwa:");
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(name -> {
            try {
                JdbcSudokuBoardDao dao;
                dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getDatabaseDao(name);
                dao.write(board);
            } catch (DaoException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    @FXML
    private void button_load_db() {
        TextInputDialog dialog = new TextInputDialog();
 
        dialog.setTitle("Wczytywanie z bazy danych");
        dialog.setHeaderText("Podaj nazwe planszy");
        dialog.setContentText("Nazwa:");
        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(name -> {
            try {
                JdbcSudokuBoardDao dao;
                dao = (JdbcSudokuBoardDao) SudokuBoardDaoFactory.getDatabaseDao(name);
                board = dao.read();
                loadValues();
            } catch (Exception ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private Button getButton(int id) {
        switch(id)
        {
            case 0: return id_button1;
            case 1: return id_button2;
            case 2: return id_button3;
            case 3: return id_button4;
            case 4: return id_button5;
            case 5: return id_button6;
            case 6: return id_button7;
            case 7: return id_button8;
            case 8: return id_button9;
            case 9: return id_button10;
            case 10: return id_button11;
            case 11: return id_button12;
            case 12: return id_button13;
            case 13: return id_button14;
            case 14: return id_button15;
            case 15: return id_button16;
            case 16: return id_button17;
            case 17: return id_button18;
            case 18: return id_button19;
            case 19: return id_button20;
            case 20: return id_button21;
            case 21: return id_button22;
            case 22: return id_button23;
            case 23: return id_button24;
            case 24: return id_button25;
            case 25: return id_button26;
            case 26: return id_button27;
            case 27: return id_button28;
            case 28: return id_button29;
            case 29: return id_button30;
            case 30: return id_button31;
            case 31: return id_button32;
            case 32: return id_button33;
            case 33: return id_button34;
            case 34: return id_button35;
            case 35: return id_button36;
            case 36: return id_button37;
            case 37: return id_button38;
            case 38: return id_button39;
            case 39: return id_button40;
            case 40: return id_button41;
            case 41: return id_button42;
            case 42: return id_button43;
            case 43: return id_button44;
            case 44: return id_button45;
            case 45: return id_button46;
            case 46: return id_button47;
            case 47: return id_button48;
            case 48: return id_button49;
            case 49: return id_button50;
            case 50: return id_button51;
            case 51: return id_button52;
            case 52: return id_button53;
            case 53: return id_button54;
            case 54: return id_button55;
            case 55: return id_button56;
            case 56: return id_button57;
            case 57: return id_button58;
            case 58: return id_button59;
            case 59: return id_button60;
            case 60: return id_button61;
            case 61: return id_button62;
            case 62: return id_button63;
            case 63: return id_button64;
            case 64: return id_button65;
            case 65: return id_button66;
            case 66: return id_button67;
            case 67: return id_button68;
            case 68: return id_button69;
            case 69: return id_button70;
            case 70: return id_button71;
            case 71: return id_button72;
            case 72: return id_button73;
            case 73: return id_button74;
            case 74: return id_button75;
            case 75: return id_button76;
            case 76: return id_button77;
            case 77: return id_button78;
            case 78: return id_button79;
            case 79: return id_button80;
            case 80: return id_button81;
        }
        return id_button1;
    }
    
    @FXML Button id_button1;
    @FXML Button id_button2;
    @FXML Button id_button3;
    @FXML Button id_button4;
    @FXML Button id_button5;
    @FXML Button id_button6;
    @FXML Button id_button7;
    @FXML Button id_button8;
    @FXML Button id_button9;
    @FXML Button id_button10;
    @FXML Button id_button11;
    @FXML Button id_button12;
    @FXML Button id_button13;
    @FXML Button id_button14;
    @FXML Button id_button15;
    @FXML Button id_button16;
    @FXML Button id_button17;
    @FXML Button id_button18;
    @FXML Button id_button19;
    @FXML Button id_button20;
    @FXML Button id_button21;
    @FXML Button id_button22;
    @FXML Button id_button23;
    @FXML Button id_button24;
    @FXML Button id_button25;
    @FXML Button id_button26;
    @FXML Button id_button27;
    @FXML Button id_button28;
    @FXML Button id_button29;
    @FXML Button id_button30;
    @FXML Button id_button31;
    @FXML Button id_button32;
    @FXML Button id_button33;
    @FXML Button id_button34;
    @FXML Button id_button35;
    @FXML Button id_button36;
    @FXML Button id_button37;
    @FXML Button id_button38;
    @FXML Button id_button39;
    @FXML Button id_button40;
    @FXML Button id_button41;
    @FXML Button id_button42;
    @FXML Button id_button43;
    @FXML Button id_button44;
    @FXML Button id_button45;
    @FXML Button id_button46;
    @FXML Button id_button47;
    @FXML Button id_button48;
    @FXML Button id_button49;
    @FXML Button id_button50;
    @FXML Button id_button51;
    @FXML Button id_button52;
    @FXML Button id_button53;
    @FXML Button id_button54;
    @FXML Button id_button55;
    @FXML Button id_button56;
    @FXML Button id_button57;
    @FXML Button id_button58;
    @FXML Button id_button59;
    @FXML Button id_button60;
    @FXML Button id_button61;
    @FXML Button id_button62;
    @FXML Button id_button63;
    @FXML Button id_button64;
    @FXML Button id_button65;
    @FXML Button id_button66;
    @FXML Button id_button67;
    @FXML Button id_button68;
    @FXML Button id_button69;
    @FXML Button id_button70;
    @FXML Button id_button71;
    @FXML Button id_button72;
    @FXML Button id_button73;
    @FXML Button id_button74;
    @FXML Button id_button75;
    @FXML Button id_button76;
    @FXML Button id_button77;
    @FXML Button id_button78;
    @FXML Button id_button79;
    @FXML Button id_button80;
    @FXML Button id_button81;
}