package com.kuuhakull;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary","");
    }
    @FXML
    private void startTest() throws IOException, ClassNotFoundException {
        App.setRoot("test",true);
    }
    @FXML
    private void startCase() throws IOException, ClassNotFoundException {
        App.setRoot("test", false);
    }
}