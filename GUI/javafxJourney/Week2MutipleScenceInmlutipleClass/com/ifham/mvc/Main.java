package GUI.javafxJourney.Week2MutipleScenceInmlutipleClass.com.ifham.mvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public  void  start(Stage stage) throws Exception{
    LoginScreen loginScreen = new LoginScreen(stage);
    loginScreen.show();
  }



  public static void main(String[] args) {
    launch(args);
  }

}
