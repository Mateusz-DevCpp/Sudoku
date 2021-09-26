module pl.userinterface.graphicuserinterface {
    requires javafx.controls;
    requires javafx.fxml;

    requires ModelProject;
    
    requires java.logging;
    requires java.sql;
    
    opens pl.userinterface.graphicuserinterface to javafx.fxml;
    exports pl.userinterface.graphicuserinterface;
}
