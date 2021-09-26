package pl.userinterface.graphicuserinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.first.firstjava.LogSystem;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import java.util.logging.Logger;

public class App extends Application {
    
    private static Scene scene;
    
    public static ResourceBundle lang;
    public static Locale local = new Locale("pl", "PL");
    
    public static Logger log;
    
    public static void main(String[] args) {
        log = LogSystem.getConfiguration();
        log.info("App start");
        lang = ResourceBundle.getBundle("pl.userinterface.graphicuserinterface.lang", local);
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), lang);
        return fxmlLoader.load();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Sudoku");
        stage.show();
    }
    
   
}