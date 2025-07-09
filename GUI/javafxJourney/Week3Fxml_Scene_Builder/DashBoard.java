package GUI.javafxJourney.Week3Fxml_Scene_Builder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoard {
    @FXML
    Label welcomeLabel;
    @FXML
    public Button logoutBtn;
    @FXML
     Label userName;

  public  void  handleLogout() throws IOException {
      FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
      Parent root = loader.load();
      Stage stage = (Stage) welcomeLabel.getScene().getWindow();
      stage.setTitle("login");
      stage.setScene(new Scene(root));
  }
    public void setWelcomeText(String name) {
        userName.setText(name);
    }


}
