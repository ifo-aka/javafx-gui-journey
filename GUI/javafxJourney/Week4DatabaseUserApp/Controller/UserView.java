
package GUI.javafxJourney.Week4DatabaseUserApp.Controller;
import GUI.javafxJourney.Week4DatabaseUserApp.MongoUtil;
import GUI.javafxJourney.Week4DatabaseUserApp.PasswordUtil;
import GUI.javafxJourney.Week4DatabaseUserApp.models.UserForUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class UserView {
    @FXML
    public Label userWelcome;

    @FXML
    public GridPane gridPane;

    public void deleteUser() throws IOException {
        MongoUtil.deleteUser(getLabelTextFromGrid(1, 0)); // Get user ID from grid
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.setTitle("LoginAuth");
        stage.setScene(new Scene(root));
    }

    public void updateUser() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update User");
        dialog.setHeaderText("Edit user details");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField usernameField = new TextField();
        TextField userEmailField = new TextField();
        TextField userPasswordField = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(userEmailField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(userPasswordField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String newUsername = usernameField.getText().trim();
            String newEmail = userEmailField.getText().trim();
            String newPassword = userPasswordField.getText().trim();
            String hashPassword = PasswordUtil.hashPassword(newPassword);

            // Update gridPane display (optional)
           setLabelTextToGrid(1,1,newUsername);
           setLabelTextToGrid(1,2,newEmail);
           setLabelTextToGrid(1,3,hashPassword);

            // Get ID from grid and update Mongo
            String id = getLabelTextFromGrid(1, 0);
            MongoUtil.updateUserViaUserInMongo(new UserForUser(id, newUsername, newEmail, hashPassword));
        }
    }

    private String getLabelTextFromGrid(int col, int row) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            if (rowIndex == row && colIndex == col && node instanceof Label) {
                return ((Label) node).getText();
            }
        }
        return null;
    }
    private void setLabelTextToGrid(int col, int row,String text) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);
            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            if (rowIndex == row && colIndex == col && node instanceof Label) {
                 ((Label) node).setText(text);
            }
        }

    }
}