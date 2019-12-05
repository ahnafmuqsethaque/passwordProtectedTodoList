package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowItemListController implements Initializable {

    public VBox topVerticalBox;
    public VBox bottomVerticalBox;
    public SplitPane splitPane;

    @FXML
    protected void goBack() throws IOException {
        MainFxGUI.verifiedScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainFxGUI.itemDisplay(topVerticalBox);
    }
}
