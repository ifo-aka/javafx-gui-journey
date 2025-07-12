package GUI.javafxJourney.Week4DatabaseUserApp.Controller;

import GUI.javafxJourney.Week4DatabaseUserApp.MongoUtil;
import GUI.javafxJourney.Week4DatabaseUserApp.PasswordUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.Objects;

public class Register {
   @FXML
    public TextField usernameField;
   @FXML
    public TextField emailField;
   @FXML
    public TextField passwordField;
    @FXML
    public TextField confirmPassword;
    @FXML
    public Label statusCheck;
    @FXML
    public  ChoiceBox<String> rolechoiceBox;


    public  void registerUser(){
        String role = rolechoiceBox.getValue();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String  password= passwordField.getText().trim();
        String confirmPass=  confirmPassword.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || role == null) {
            statusCheck.setText("Please fill all fields");

        }else {
            if(!password.equals(confirmPass)){
                statusCheck.setText("please match password");
            }
            else {
                String  hashPassword= PasswordUtil.hashPassword(password);
              String status=  MongoUtil.registerToMongo(username,email,hashPassword,role);
              if(status.equals("success")){
                  usernameField.setText("");
                  emailField.setText("");
                  passwordField.setText("");
                  confirmPassword.setText("");
                  rolechoiceBox.setValue("");
                  statusCheck.setText("Successful signup go back and login");
              }

            }
        }

    }


    public  void backToLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Views/login.fxml")));
        Stage stage =(Stage) usernameField.getScene().getWindow();

        stage.setTitle("login");
        stage.setScene(new Scene(root));


    }



}


