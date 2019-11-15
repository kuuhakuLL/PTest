package com.kuuhakull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class TestController extends VBox {
    private ArrayList<Qwest> qwests;
    private int numberQwest;
    private Qwest qwest;
    private ToggleGroup group;
    private TextField field;
    private ArrayList<CheckBox> checkBoxes;
    private ArrayList<ComboBox<String>> comboBoxes;
    private boolean [] answers;
    @FXML
    private VBox QwestVBox;

    @FXML
    public void replyQwest(ActionEvent event){
        ArrayList<String> reply = new ArrayList<>();
        switch (qwest.getTypeQwest()){
            case 0:
            case 4:{
                RadioButton selection = (RadioButton) group.getSelectedToggle();
                reply.add(selection.getText());
                break;
            }
            case 1: {
                reply.add(field.getText().toLowerCase());
                field = null;
                break;
            }
            case 2: {
                for (CheckBox item: checkBoxes){
                    if(item.isSelected())
                        reply.add(item.getText());
                }
                checkBoxes = null;
                break;
            }
            case 3: {
                String rezalt = "";
                for(ComboBox<String> item: comboBoxes){
                    rezalt += item.getValue().split(" ")[0];
                }
                comboBoxes = null;
                reply.add(rezalt);
                break;
            }
            default: break;
        }
        answers[numberQwest] = qwest.chekAnswer(reply);
        System.out.println(answers[numberQwest]);
        setNumberQwest(1);
    }

    public void setQwestVBox() {
        QwestVBox.getChildren().clear();
        Label NumberQwest = new Label("№ " + (numberQwest + 1) + " / " + qwests.size());
        Label textQ = new Label(qwest.getText());
        textQ.setWrapText(true);
        QwestVBox.getChildren().addAll(NumberQwest, textQ);
        switch (qwest.getTypeQwest()){
            case 0: {
                for (String item: qwest.getAllAnswer()){
                    RadioButton radioButton = new RadioButton(item);
                    radioButton.setToggleGroup(group);
                    radioButton.setWrapText(true);
                    QwestVBox.getChildren().add(radioButton);
                }
                break;
            }
            case 1: {
                field = new TextField();
                QwestVBox.getChildren().add(field);
                break;
            }
            case 2: {
                checkBoxes = new ArrayList<>();
                for (String item: qwest.getAllAnswer()){
                    CheckBox checkBox = new CheckBox(item);
                    checkBox.setWrapText(true);
                    checkBoxes.add(checkBox);
                }
                QwestVBox.getChildren().addAll(checkBoxes);
                break;
            }
            case 3: {
                comboBoxes = new ArrayList<>();
                ObservableList<String> allAnswers = FXCollections.observableList(qwest.getAllAnswer());
                for (int i = 0; i < allAnswers.size(); i++ ){
                    ComboBox<String> comboBox = new ComboBox<String>(allAnswers);
                    comboBoxes.add(comboBox);
                    Label namber = new Label((i+1) + ".");
                    HBox hBox = new HBox(namber, comboBox);
                    QwestVBox.getChildren().add(hBox);
                }
                break;
            }case 4: {
                RadioButton radioYes = new RadioButton("да");
                RadioButton radioNo = new RadioButton("нет");
                radioYes.setToggleGroup(group);
                radioNo.setToggleGroup(group);
                QwestVBox.getChildren().addAll(radioYes,radioNo);
                break;
            }
            default: break;
        }
    }

    public void setNumberQwest(int i){
        if(numberQwest + i >= 0 && numberQwest + i < qwests.size() ){
            this.numberQwest = numberQwest + i;
            setQwest();
            setQwestVBox();
        }
    }
    private void setQwest() {
        this.qwest = qwests.get(numberQwest);
    }

    @FXML
    void initialize(){
        numberQwest = 0;
        qwests = App.qwests;
        answers = new boolean[qwests.size()];
        group = new ToggleGroup();
        setNumberQwest(0);
    }
    @FXML
    public void switchToPrimary(ActionEvent event) throws IOException {
        int rezalt = 0;
        for (int i = 0; i < answers.length; i++){
            rezalt += answers[i] ? 1 : 0;
        }
        System.out.println(rezalt);
        App.setRoot("primary","");
    }
    @FXML
    public void nextQwest(ActionEvent event)throws IOException{
        setNumberQwest(1);
    }
    @FXML
    public void backQwest (ActionEvent event)throws IOException{
        setNumberQwest(-1);
    }
}
