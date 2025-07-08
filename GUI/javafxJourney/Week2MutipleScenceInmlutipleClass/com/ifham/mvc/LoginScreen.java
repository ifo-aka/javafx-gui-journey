package GUI.javafxJourney.Week2MutipleScenceInmlutipleClass.com.ifham.mvc;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicButtonUI;

public class LoginScreen {
  private final Stage stage ;

  public LoginScreen(Stage stage) {
    this.stage = stage;
  }

  public  void show(){
    Label title = new Label("Welcome Please login");
    TextField usernameField= new TextField();
    usernameField.setPromptText("Enter user name");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter password");
    Button loginButton = new Button("login");
    Label showCanLogin = new Label();


    loginButton.setOnAction(e->{
      String username = usernameField.getText().trim();
      String password = passwordField.getText().trim();
      if(username.isEmpty() || password.isEmpty()){
        showCanLogin.setText("please fill both fields");
      }
      else {
        if(username.equals("ifham") && password.equals("java123")){
          showCanLogin.setText("Access granted.");
          new DashBoard(username,stage).show();
        }
        else{
          showCanLogin.setText("invalid credentials");
        }
      }
    });


    VBox loginLayout = new VBox(10);
    loginLayout.getChildren().addAll(title,usernameField,passwordField,loginButton,showCanLogin);


    Scene loginScene = new Scene(loginLayout,400,400);

    stage.setTitle("Login page");
    stage.setScene(loginScene);
    stage.show();

  }
}
