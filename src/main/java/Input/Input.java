package Input;/**
 * Created by Shizuoko on 05.03.2017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Input extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("style.fxml"));

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Olympia");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

