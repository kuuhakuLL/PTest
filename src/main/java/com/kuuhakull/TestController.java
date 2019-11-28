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
    private int [] answers;
    @FXML
    private VBox QwestVBox;


    @FXML
    public void replyQwest(ActionEvent event){
        ArrayList<String> reply = new ArrayList<>();
        switch (qwest.getTypeQwest()){
            case 0:
            case 4:{
                try {
                    RadioButton selection = (RadioButton) group.getSelectedToggle();
                    reply.add(selection.getText());
                    qwest.setUserAnswer(selection.getText());
                }catch (NullPointerException e){
                    reply.add("");
                }
                break;
            }
            case 1: {
                reply.add(field.getText().toLowerCase());
                qwest.setUserAnswer(field.getText());
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
                try {
                    for (ComboBox<String> item : comboBoxes) {
                        rezalt += item.getValue().split(" ")[0];
                    }
                    comboBoxes = null;
                    reply.add(rezalt);
                }catch (NullPointerException e){}
                break;
            } case 5: {
                String rezalt = "";
                try {
                    for(int i=0; i<comboBoxes.size(); i++){
                        rezalt += Integer.toString(i+1) + comboBoxes.get(i).getValue().charAt(0);
                    }
                    comboBoxes = null;
                    reply.add(rezalt);
                }catch (NullPointerException e){}
                break;
            } case 6:{
                try {
                    RadioButton selection = (RadioButton) group.getSelectedToggle();
                    qwest.setUserAnswer(selection.getText());
                    ArrayList<String> ans = qwest.getAllAnswer();
                    for (int i = 0; i <ans.size() ; i++){
                        if (ans.get(i).equals(qwest.getUserAnswer())){
                            answers[numberQwest] = i;
                        }
                    }
                }catch (NullPointerException e){
                    reply.add("");
                }
                break;
            }
            default: break;
        }
        if(qwest.chekAnswer(reply)) {
            answers[numberQwest] = 1;
        }
        setNumberQwest(1);
    }

    public void setQwestVBox() {
        QwestVBox.getChildren().clear();
        Label NumberQwest = new Label("№ " + (numberQwest + 1) + " / " + qwests.size());
        Label textQ = new Label(qwest.getText());
        textQ.setWrapText(true);
        QwestVBox.getChildren().addAll(NumberQwest, textQ);
        switch (qwest.getTypeQwest()){
            case 0:
            case 6:{
                for (String item: qwest.getAllAnswer()){
                    RadioButton radioButton = new RadioButton(item);
                    radioButton.setToggleGroup(group);
                    radioButton.setWrapText(true);
                    if(qwest.getUserAnswer().equals(item)){
                        radioButton.setSelected(true);
                    }
                    QwestVBox.getChildren().add(radioButton);
                }
                break;
            }
            case 1: {
                field = new TextField();
                field.setText(qwest.getUserAnswer());
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
                if(qwest.getUserAnswer().equals("да")){
                    radioYes.setSelected(true);
                }
                if(qwest.getUserAnswer().equals("нет")){
                    radioYes.setSelected(true);
                }
                QwestVBox.getChildren().addAll(radioYes,radioNo);
                break;
            } case 5: {
                comboBoxes = new ArrayList<>();
                ArrayList<String> answers = new ArrayList<>();
                ArrayList<String> numbers = new ArrayList<>();
                for (String item: qwest.getAllAnswer()){
                    if(item.charAt(0)>'0' && item.charAt(0)<'9'){
                        numbers.add(item);
                    }else {
                        answers.add(item);
                    }
                }
                ObservableList<String> allAnswers = FXCollections.observableList(answers);
                for (String item: numbers){
                    ComboBox<String> comboBox = new ComboBox<String>(allAnswers);
                    comboBox.setMaxWidth(600);
                    comboBoxes.add(comboBox);
                    Label namber = new Label(item);
                    namber.setMinWidth(150);
                    HBox hBox = new HBox(namber, comboBox);
                    QwestVBox.getChildren().add(hBox);
                }
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
        answers = new int[qwests.size()];
        group = new ToggleGroup();
        setNumberQwest(0);
    }
    @FXML
    public void switchToEnd(ActionEvent event) throws IOException {
        int rezalt = 0;
        for (int i = 0; i < answers.length; i++){
            rezalt += answers[i];
        }
        App.setRoot("endTest", rezalt, qwests);
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
