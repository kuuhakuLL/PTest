package com.kuuhakull;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EndTestController {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    Label rezalt;

    @FXML
    void initialize(){
        rezalt.setText(Integer.toString(App.rez));
    }

    public void switchToEnd(ActionEvent event) throws IOException {
        String student =name.getText() + " " + surname.getText();
        new CreateExele(App.qwests, App.rez, App.title, student);
        App.setRoot("primary", "");
    }

}
