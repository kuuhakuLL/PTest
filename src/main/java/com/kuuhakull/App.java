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
    static HashMap<Integer,String> tests;
    static int rez;
    static String title;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        root = stage;
        scene = new Scene(loadFXML("primary"));
        root.setScene(scene);
        root.show();
    }

    static void setRoot(String fxml, String title) throws IOException {
        root.setTitle(title);
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(String fxml, String t, int id) throws IOException, ClassNotFoundException {
        tests = TestLoader.selectTests(id);
        title = t;
        root.setTitle(t);
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(String fxml, int id) throws IOException, ClassNotFoundException {
        qwests = TestLoader.selectQwests(id);
        scene.setRoot(loadFXML(fxml));
    }
    static void setRoot(String fxml, int rezalt, ArrayList<Qwest> q) throws IOException {
        qwests = q;
        rez= rezalt;
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static HashMap<Integer, String> getTests() { return tests; }
}
