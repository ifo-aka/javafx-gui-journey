package GUI.javafxJourney.Week4DatabaseUserApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public   void start(Stage mainStage) throws IOException {




        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/login.fxml")));



        Scene scene = new Scene(root,500,500);

        mainStage.setTitle("Login Auth.");
        mainStage.setScene(scene);
        mainStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
