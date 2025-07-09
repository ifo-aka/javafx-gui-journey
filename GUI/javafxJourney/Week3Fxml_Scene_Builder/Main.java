package GUI.javafxJourney.Week3Fxml_Scene_Builder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
    Scene scene = new Scene(root,400,400);
    stage.setTitle("FXL mvc scene");
    stage.setScene(scene);
    stage.show();

  }
}
