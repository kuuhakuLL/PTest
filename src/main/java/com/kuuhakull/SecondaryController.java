package com.kuuhakull;

import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SecondaryController {

    @FXML
    VBox vBox;

    @FXML
    void initialize() throws IOException {
        HashMap<Integer, String> tests = App.getTests();
        for (Integer key: tests.keySet()) {
            Button button = new Button(tests.get(key));
            button.setUserData(Integer.toString(key));
            button.setId("Button"+(key-1)%2);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Button button = (Button)event.getSource();
                    String title = button.getText();
                    int id = Integer.parseInt((String) button.getUserData());
                    try {
                        App.setRoot("test", id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            vBox.getChildren().add(button);
        }
        Button button = new Button("Вернуться назад");
        button.setId("Button2");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    App.setRoot("primary","");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        vBox.getChildren().add(button);
    }


}