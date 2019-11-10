package com.kuuhakull;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private VBox QwestVBox;

    @FXML
    void initialize(){
        numberQwest=0;
        qwests = App.qwests;
        setNumberQwest(0);
        group = new ToggleGroup();
        setQwestVBox();
    }

    @FXML
    public void replyQwest(ActionEvent event){
        ArrayList<String> answer = new ArrayList<>();
        switch (qwest.getTypeQwest()){
            case 0: {
                RadioButton selection = (RadioButton) group.getSelectedToggle();
                answer.add(selection.getText());
                System.out.println((qwests.get(numberQwest).chekAnswer(answer)));
                break;
            }
            case 1: {
                answer.add(field.getText());
                System.out.println((qwests.get(numberQwest).chekAnswer(answer)));
                break;
            }
            case 2: {
                for (CheckBox item: checkBoxes){
                    if(item.isSelected())
                        answer.add(item.getText());
                }
                System.out.println((qwests.get(numberQwest).chekAnswer(answer)));
                break;
            }
            default: break;
        }
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
        switch (qwest.getTypeQwest()){
            case 0: {
                for (String item: qwest.getAllAnswer()){
                    RadioButton radioButton = new RadioButton(item);
                    radioButton.setToggleGroup(group);
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
                    checkBoxes.add(new CheckBox(item));
                }
                QwestVBox.getChildren().addAll(checkBoxes);
                break;
            }
//            case 3: {
//                break;
//            }
            default: break;
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
