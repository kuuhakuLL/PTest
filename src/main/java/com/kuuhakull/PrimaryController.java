package com.kuuhakull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException, ClassNotFoundException {
        Button button = (Button)event.getSource();
        String title = button.getText();
        int id = Integer.parseInt((String)button.getUserData());
        App.setRoot("secondary", title, id);
    }

}
