package GUI.javafxJourney.Week4DatabaseUserApp.Controller;

import GUI.javafxJourney.Week4DatabaseUserApp.MongoUtil;
import GUI.javafxJourney.Week4DatabaseUserApp.PasswordUtil;
import com.mongodb.client.FindIterable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
   @FXML
    public Label welcomeText;

  @FXML
    public Button loginBtn;
  @FXML
    public TextField usernameField;
  @FXML
    public PasswordField passwordField;
  @FXML
    public Label statusField;


    public  void doLogin() throws IOException {

          String username= usernameField.getText().trim();
          String password = passwordField.getText().trim();
          if(username.isEmpty() || password.isEmpty()){
              statusField.setText("Please fill the above fields.");
          }else {
              String hashPassword = PasswordUtil.hashPassword(password);
              JSONObject resObj = MongoUtil.checkLogin(username, hashPassword);
              String status = resObj.getString("status");

              if(status.equals("success")){
                  String role = resObj.getString("role");
                  statusField.setText("Access granted as " + role );
                  if(role.equals("Admin")){
                      FXMLLoader loader= new FXMLLoader(getClass().getResource("../Views/AdminView.fxml"));
                      Parent root = loader.load();
                      AdminView controller = loader.getController();
                      controller.WelcomeText.setText("Welcome "+ username + " !");

                      Stage stage = (Stage) usernameField.getScene().getWindow();

                      stage.setTitle("Admin Dashboard");
                      stage.setScene(new Scene(root));
                  }
                  else {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/UserView.fxml"));
                      Parent root = loader.load();
                      UserView controller = loader.getController();
                      controller.userWelcome.setText("Welcome " + username + " !");
                      controller.gridPane.add(new Label("id"),0,0);
                      controller.gridPane.add(new Label("Name"),0,1);
                      controller.gridPane.add(new Label("Email"),0,2);
                      controller.gridPane.add(new Label("Password"),0,3);

                    Document user =  MongoUtil.getOneUser(username, hashPassword);

                      assert user != null;
                      String mongoId = user.getObjectId("_id").toHexString();
                      String mongoUsername = user.getString("username");
                      String mongoEmail = user.getString("email");
                      String mongoPassword = user.getString("password");
                      controller.gridPane.add(new Label(mongoId),1,0);
                      controller.gridPane.add(new Label(mongoUsername),1,1);
                      controller.gridPane.add(new Label(mongoEmail),1,2);
                      controller.gridPane.add(new Label(mongoPassword),1,3);

                          Stage stage = (Stage) usernameField.getScene().getWindow();
                          stage.setTitle("UserDashBoard");
                          stage.setScene(new Scene(root));


                      }






              }else {
                  statusField.setText("invalid credentials");
              }
          }

  }
  public  void doSignup() throws IOException {

      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Views/Register.fxml")));
      Stage stage =(Stage) usernameField.getScene().getWindow();

      stage.setTitle("signup");
      stage.setScene(new Scene(root));
  }



}
