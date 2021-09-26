package pl.userinterface.graphicuserinterface;

import java.util.ListResourceBundle;

public class Autor extends ListResourceBundle {
    private final Object[][] resources = {
        {"imie1", "Mateusz"},
        {"imie2", "Kinga"},
        {"nazwisko1", "Darul"},
        {"nazwisko2", "Błaszczyk"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}