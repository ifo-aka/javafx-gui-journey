package com.ifham.GUI.javafxGuiJjourney.Week1Basics.LoginForm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Login extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Label titleLabel= new Label("Welcome Please login.");
    TextField userNameField = new TextField();
    userNameField.setPromptText("Enter username.");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter password.");
    Button btn = new Button("Login");
    Label setStatus= new Label();




    btn.setOnAction(e-> {
      String username = userNameField.getText().trim();
      String password = passwordField.getText().trim();
      if(!username.isEmpty() || !password.isEmpty()) {
        if (!username.equals("ifham") || !password.equals("java123")) {
          setStatus.setText("❌ Invalid credentials!");
        } else {
          setStatus.setText("✅ Access granted.");
        }
      }else {
        setStatus.setText("please fill the above fields.");
      }
    });
    VBox vbox = new VBox(10);
    vbox.getChildren().addAll(titleLabel, userNameField,passwordField,btn,setStatus);
    Scene scene = new Scene(vbox,400,500);
    stage.setTitle("login form.");
    stage.setScene(scene);
    stage.show();

  }
}
