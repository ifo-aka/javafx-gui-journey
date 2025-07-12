package GUI.javafxJourney.Week4DatabaseUserApp.models;

import javafx.beans.property.SimpleStringProperty;

public class UserForUser {
 private  String  userName;
 private  String email ;
 private  String password;
 private final String  id;




    public UserForUser( String id, String userName, String email, String password) {
        this.id=id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
public  String getId(){
        return  id;
}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
