package pl.userinterface.graphicuserinterface;

import java.io.IOException;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import pl.first.firstjava.*;

public class PrimaryController implements Initializable {
    
    @FXML Button id_start;
    @FXML Button id_exit;
    
    @FXML ToggleGroup group;
    @FXML RadioButton id_easy;
    @FXML RadioButton id_medium;
    @FXML RadioButton id_hard;
    
    @FXML ToggleGroup group_lang;
    @FXML RadioButton id_pl;
    @FXML RadioButton id_eng;
    
    @FXML Label label_1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App.log.info("Initialize menu site");
        Autor autor = new Autor();
        label_1.setText(
                autor.getObject("imie1").toString()+" "+
                autor.getObject("nazwisko1").toString()+
                " & "+
                autor.getObject("imie2").toString()+" "+
                autor.getObject("nazwisko2").toString());
        
        group = new ToggleGroup();
        id_easy.setToggleGroup(group);
        id_medium.setToggleGroup(group);
        id_hard.setToggleGroup(group);
        id_easy.setSelected(true);
        
        group_lang = new ToggleGroup();
        id_pl.setToggleGroup(group_lang);
        id_eng.setToggleGroup(group_lang);
        if ("pl".equals(App.local.getLanguage())) {
            id_pl.setSelected(true);
        } else {
            id_eng.setSelected(true);
        }
    }
    
    @FXML
    private void setLang() throws IOException {
        if (id_pl.isSelected()) {
            App.log.info("Change language to : pl-PL");
            App.local = Locale.forLanguageTag("pl-PL");
        } else {
            App.log.info("Change language to : en-US");
            App.local = Locale.forLanguageTag("en-US");
        }
        App.lang = ResourceBundle.getBundle("pl.userinterface.graphicuserinterface.lang", App.local);
        App.setRoot("primary");
    }
    
    @FXML
    private void buttonStart() throws IOException {
        if (id_easy.isSelected()) {
        App.log.info("Start game on level: EASY");
            SecondaryController.setLevel(SudokuLevel.EASY);
        } else if (id_medium.isSelected()) {
        App.log.info("Start game on level: MEDIUM");
            SecondaryController.setLevel(SudokuLevel.MEDIUM);
        } else {
        App.log.info("Start game on level: HARD");
            SecondaryController.setLevel(SudokuLevel.HARD);
        }
        
        App.setRoot("secondary");
    }
    
    @FXML
    private void buttonExit() throws IOException {
        App.log.info("App close");
        System.exit(0);
    }
}
