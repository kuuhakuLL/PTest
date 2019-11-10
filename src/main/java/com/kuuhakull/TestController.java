package com.kuuhakull;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TestController extends VBox {
    private ArrayList<Qwest> qwests;
    private int numberQwest;
    private Qwest qwest;
    private ToggleGroup group;

    @FXML
    private VBox QwestVBox;

    @FXML
    void initialize(){
        numberQwest=0;
        qwests = App.qwests;
        setNumberQwest(0);
        group = new ToggleGroup();
        QwestVBox.getChildren().addAll(new Button("qer"),new Button("qer"),new Button("qer"),new Button("qer"));
        setQwestVBox();
    }

    @FXML
    public void replyQwest(ActionEvent event){
        RadioButton selection = (RadioButton) group.getSelectedToggle();
        ArrayList<String> answer = new ArrayList<>();
        answer.add(selection.getText());
        System.out.println((qwests.get(numberQwest).chekAnswer(answer)));

    }

    public void setNumberQwest(int i){
        if(numberQwest + i >= 0 && numberQwest + i < qwests.size() ){
            this.numberQwest = numberQwest + i;
            setQwest();
        }
    }

    public void setQwest() {
        this.qwest = qwests.get(numberQwest);
    }

    public void setQwestVBox() {
        QwestVBox.getChildren().clear();
        Label NumberQwest = new Label("№ " + (numberQwest + 1) + " / " + qwests.size());
        Label textQ = new Label(qwest.getText());
        QwestVBox.getChildren().addAll(NumberQwest, textQ);
        for (String item: qwest.getAllAnswer()){
            RadioButton radioButton = new RadioButton(item);
            radioButton.setToggleGroup(group);
            QwestVBox.getChildren().add(radioButton);
        }
    }

    @FXML
    public void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary","");
    }
    @FXML
    public void nextQwest(ActionEvent event)throws IOException{
        setNumberQwest(1);
        setQwestVBox();
    }
    @FXML
    public void backQwest (ActionEvent event)throws IOException{
        setNumberQwest(-1);
        setQwestVBox();
    }
}
