package GUI.javafxJourney.Week4DatabaseUserApp.Controller;


import GUI.javafxJourney.Week4DatabaseUserApp.MongoUtil;
import GUI.javafxJourney.Week4DatabaseUserApp.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.bson.Document;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.List;

public class AdminView implements Initializable {
    @FXML
    public Label WelcomeText;


    @FXML private TableView<User> userTable;
    @FXML public TableColumn<User,String> idCol;
    @FXML private TableColumn<User, String> nameCol;
    @FXML private TableColumn<User, String> emailCol;
    @FXML private TableColumn<User, String> roleCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setEditable(true);



        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        loadUsersFromMongo();
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    public void updateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("No User Selected", "Please select a user to update.");
            return;
        }

        // Create a custom dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update User");
        dialog.setHeaderText("Edit user details");

        // Set buttons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create input fields
        TextField usernameField = new TextField(selectedUser.getUsername());
        TextField emailField = new TextField(selectedUser.getEmail());
        ChoiceBox<String> roleChoice = new ChoiceBox<>(FXCollections.observableArrayList("Admin", "User"));
        roleChoice.setValue(selectedUser.getRole());

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Role:"), 0, 2);
        grid.add(roleChoice, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Handle result
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String newUsername = usernameField.getText().trim();
            String newEmail = emailField.getText().trim();
            String newRole = roleChoice.getValue();

            // Update object
            selectedUser.setUsername(newUsername);
            selectedUser.setEmail(newEmail);
            selectedUser.setRole(newRole);

            // Update MongoDB
            MongoUtil.updateUserInMongo(selectedUser);

            // Refresh Table
            userTable.refresh();
            showAlert("Updated", "User updated successfully.");
        }
    }

    private void loadUsersFromMongo() {
        List<Document> documents = MongoUtil.getAllUsers();
        ObservableList<User> users = FXCollections.observableArrayList();

        for (Document doc : documents) {
            String id = doc.getObjectId("_id").toHexString();
            String name = doc.getString("username");
            String email = doc.getString("email");
            String role = doc.getString("role");
            users.add(new User(id,name, email, role));
        }

        userTable.setItems(users);
    }



   @FXML
    public void deleteUser() {

       User selectedUser = userTable.getSelectionModel().getSelectedItem();
       if (selectedUser == null) {
           showAlert("No User Selected", "Please select a user to update.");
           return;
       }
        String id = selectedUser.getId();
       System.out.println( id);
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Delete User");
       alert.setHeaderText("Are you sure you want to delete this user?");
       alert.setContentText("This action cannot be undone.");

       Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent() && result.get() == ButtonType.OK){
           MongoUtil.deleteUser(id);
           userTable.refresh();
           loadUsersFromMongo();

       }


   }
}

