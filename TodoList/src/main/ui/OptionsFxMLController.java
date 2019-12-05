package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import static ui.MainFxGUI.*;

public class OptionsFxMLController implements Initializable {
//    Button itemAdderButton;
//    Button itemRemoverButton;
//    Button itemListButton;
//    Button crossedOffListButton;
//    Button saveFileButton;
//    Button exitButton;

   // public Button musicToggle;
    public Button musicToggle;
    //public AudioStream audioStream;


    @FXML
    protected void saveAll() throws IOException {
        MainFxGUI.saveFile();
    }

    @FXML
    protected void addItemButtonPressed() throws IOException {
        MainFxGUI.itemAdderScene();

    }

    @FXML
    protected void itemListButtonPressed() throws IOException {
        MainFxGUI.itemDisplayScene();
    }

    @FXML
    protected void itemRemoverButtonPressed() throws IOException {
        MainFxGUI.itemRemoverScene();
    }

    @FXML
    protected void showCrossedOffList() throws IOException {
        MainFxGUI.crossedItemScene();
    }

    @FXML
    protected void logout() throws IOException {
        MainFxGUI.saveFile();
        MainFxGUI.loginScene();
    }

    @FXML
    protected void musicButtonPressed() throws IOException {

        if (musicToggle.getText().equals("Music Off")) {
            //text color is changed
            //usicToggle.get
            musicToggle.setText("Music On");
            musicToggle.setTextFill(Color.GREEN);
//        // play music;

            MainFxGUI.playStop(true);
        } else {
            //text color is changed
            musicToggle.setText("Music Off");
            musicToggle.setTextFill(Color.RED);
//        // play music;

            MainFxGUI.playStop(false);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainFxGUI.loadAudio();

    }
}
