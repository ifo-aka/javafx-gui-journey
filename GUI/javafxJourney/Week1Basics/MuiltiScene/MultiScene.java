package GUI.javafxJourney.Week1Basics.MuiltiScene;

import GUI.javafxJourney.Week1Basics.LoginForm.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MultiScene  extends Application {





  @Override
  public void start(Stage stage) throws Exception {
//    LoginScene
    Label title = new Label("Welcome Please Login");
    TextField userName = new TextField();
    userName.setPromptText("Enter your name");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter user password");
    Button loginButton = new Button("Login");
    Label check= new Label();



    VBox loginForm = new VBox(10);
    loginForm.getChildren().addAll(title,userName,passwordField,loginButton,check);

    Scene LoginScene = new Scene(loginForm,1000,800);


//    App Scene
    Label appTitle = new Label("welcome to App");
    Button appButton= new Button("Click me");
    appButton.setOnAction(e->{
      appTitle.setText("I am clicked");
    });
    VBox  app= new VBox(10);
    app.getChildren().addAll(appTitle,appButton);

    Scene AppScene =  new Scene(app,1000,1000);

//
    loginButton.setOnAction(e->{
      String username = userName.getText().trim();
      String password= passwordField.getText().trim();

      if(!username.isEmpty() && !password.isEmpty()) {
        if (!username.equals("ifham") || !password.equals("java123")) {
          check.setText("❌ Invalid credentials!");
        } else {
          check.setText("✅ Access granted.");
          stage.setScene(AppScene);
        }
      }else {
        check.setText("please fill the above fields.");
      }
    });







    stage.setTitle("first App");
    stage.setScene(LoginScene);
    stage.show();



  }


  public static void main(String[] args) {
    launch(args);
  }







}
