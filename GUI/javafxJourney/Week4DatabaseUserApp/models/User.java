package GUI.javafxJourney.Week4DatabaseUserApp.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private  final StringProperty id;
    private final StringProperty username;
    private final StringProperty email;
    private final StringProperty role;

    public User(String id,  String username, String email, String role) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.role = new SimpleStringProperty(role);
    }

    public String getId(){
        return  id.get();
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String value) {
        username.set(value);
    }
    public String getRole() {
        return role.get();
    }

    public void setRole(String value) {
        role.set(value);
    }


    public StringProperty usernameProperty() {
        return username;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }


    public StringProperty roleProperty() {
        return role;
    }
}
