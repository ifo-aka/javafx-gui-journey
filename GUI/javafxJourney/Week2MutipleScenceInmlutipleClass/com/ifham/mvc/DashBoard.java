package GUI.javafxJourney.Week2MutipleScenceInmlutipleClass.com.ifham.mvc;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashBoard {
  String user ;
  Stage stage ;

  public DashBoard(String user, Stage stage) {
    this.user = user;
    this.stage = stage;
  }

  public void show(){
    Label appTitle = new Label("welcome "+ user + "!");
    Button appButton= new Button("Click me");
    appButton.setOnAction(e->{
      appTitle.setText("I am clicked");
    });
    Button logOut = new Button("logout");
    logOut.setOnAction(e->{
      new LoginScreen(stage).show();
    });



    VBox DashBoardLayout= new VBox(10);
    DashBoardLayout.getChildren().addAll(appTitle,appButton,logOut);

    Scene dashBoardScene =  new Scene(DashBoardLayout,400,400);

    stage.setTitle("Dashboard");
    stage.setScene(dashBoardScene);
    stage.show();




  }

}
