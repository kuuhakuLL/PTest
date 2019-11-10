package com.kuuhakull;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends Application {

    private static Scene scene;
    private static Stage root;
    static ArrayList<Qwest> qwests;
    static HashMap<Integer,String> Tests;
    static int Id;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Tests = TestLoader.selectTests();
        root = stage;
        scene = new Scene(loadFXML("primary"));
        root.setScene(scene);
        root.show();
    }

    static void setRoot(String fxml, String title) throws IOException {
        root.setTitle(title);
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(String fxml, String title, int id) throws IOException {
        Id = id;
        root.setTitle(title);
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(String fxml, Boolean test) throws IOException, ClassNotFoundException {
        qwests = TestLoader.selectQwests(test, Id);
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public HashMap<Integer, String> getTests() {
        return Tests;
    }
}
