package GUI.javafxJourney.Week3Fxml_Scene_Builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {


  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  Label statusLabel ;




@FXML
  public void handleLogin() {
  String username = usernameField.getText().trim();
  String password = passwordField.getText().trim();

  if (username.equals("ifham") && password.equals("java123")) {
    statusLabel.setText("✅ Access Granted");
try{
  FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoard.fxml"));
  Parent root = loader.load();
  DashBoard controller = loader.getController();
  controller.setWelcomeText("Hello " + username+ " !");
  Stage stage = (Stage) usernameField.getScene().getWindow();
  stage.setTitle("DashBoard");
  stage.setScene(new Scene(root));

}catch (Exception e){
  System.out.println(e);
}
  } else {
    statusLabel.setText("❌ Invalid Credentials");
  }
}


}

