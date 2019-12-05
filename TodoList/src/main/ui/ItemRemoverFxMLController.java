package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemRemoverFxMLController implements Initializable {

    public SplitPane splitPane;
    public VBox topVertical;
    public VBox bottomVertical;
    public TextField itemNoTextField;
    public Label labelStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTopVBox();
    }

    @FXML
    protected void setupTopVBox() {

        MainFxGUI.itemDisplay(topVertical);

    }

    @FXML
    protected void removeFromTable() {

        int i = Integer.parseInt(String.valueOf(itemNoTextField.getText()));
        MainFxGUI.itemRemover(i);
        labelStatus.setText("Status: Item successfully removed");
        labelStatus.setTextFill(Color.GREEN);
        //topVertical.getChildren().clear();
        //setupTopVBox();


    }

    @FXML
    protected void goBack() throws IOException {

        MainFxGUI.verifiedScene();

    }
}
