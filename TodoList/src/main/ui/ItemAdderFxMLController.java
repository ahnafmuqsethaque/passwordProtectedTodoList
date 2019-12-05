package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Item;
import model.RegularItem;

import java.awt.*;
import java.io.IOException;

import static ui.MainFxGUI.currentProfile;

public class ItemAdderFxMLController {

    public TextField itemTextField;
    public Label statusLabel;


    @FXML
    protected void itemButtonAdd() {

        String inputString = itemTextField.getText();
        Item item = new RegularItem(inputString);

        MainFxGUI.regularItemAdder(item);
        itemTextField.clear();
        statusLabel.setText("Status: Successfully added to your list :)");
        statusLabel.setTextFill(Color.GREEN);

    }

    @FXML
    protected void goBack() throws IOException {
        MainFxGUI.verifiedScene();
    }


}
