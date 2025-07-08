package com.ifham.GUI.javafxGuiJjourney.Week1Basics.HelloGUIApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import  javafx.scene.control.Label;



public class Main extends Application {


  @Override
  public void start(Stage stage) throws Exception {
    Label label = new Label("Welcome to javafx gui journey");
    Button btn = new Button("click");
    btn.setOnAction((e)->label.setText("clicked"));


    VBox box =  new VBox(10);
   box.getChildren().addAll(label,btn);


    Scene scene = new Scene(box,200,300);




    stage.setTitle("basics gui");
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
  launch(args);
  }
}
