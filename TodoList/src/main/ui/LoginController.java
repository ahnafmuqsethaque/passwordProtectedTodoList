package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ui.MainFxGUI.*;

public class LoginController implements Initializable {

    public PasswordField passwordField;
    private int profilePointer;
    public TextField newUsername;
    public TextField newPassword;
    public ChoiceBox<String> profileChoice;
    public Label weatherLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChoiceBox();
        updateWeather();
    }

    private void loadChoiceBox() {

        for (int i = 0; i < collaboratorList.size(); i++) {
            profileChoice.getItems().add(i + 1 + ". " + collaboratorList.get(i).getName());
        }

    }

    @FXML
    protected void loginButtonClicked() throws IOException {
        //adjusting for zero index
        profilePointer = Integer.parseInt(String.valueOf(profileChoice.getValue().charAt(0))) - 1;
        MainFxGUI.matchPassword(passwordField.getText(), profilePointer);

        if (currentProfile != null) {
            MainFxGUI.verifiedScene();
        }


    }

    @FXML
    protected void signUpButtonClicked() throws IOException {

        MainFxGUI.addProfile(newUsername.getText(), newPassword.getText());
        currentProfile = collaboratorList.get(collaboratorList.size() - 1);
        MainFxGUI.verifiedScene();
    }

    @FXML
    protected void updateWeather() {

        String weather = MainFxGUI.printWeather();
        weatherLabel.setText("Live Weather Update: " + weather);

    }

}
