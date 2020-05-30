package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    private static final double APPLICATION_WIDTH = 1500;
    private static final double APPLICATION_HEIGHT = 700;

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, APPLICATION_WIDTH, APPLICATION_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Read News");
        primaryStage.getIcons().add(new Image(new FileInputStream("app-icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
