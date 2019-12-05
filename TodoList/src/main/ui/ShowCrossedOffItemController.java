package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Item;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import static ui.MainFxGUI.collaboratorList;
import static ui.MainFxGUI.currentProfile;

public class ShowCrossedOffItemController implements Initializable {

    public AnchorPane anchorPaneCrossed;
    public ImageView imageViewer;
    public VBox verticalBox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUp();
        paneSetUp();
    }

    @FXML
    protected void setUp() {
        //anchorPaneCrossed.getChildren().clear();
        Image image = new Image("file:///Users/ahnafmuqsethaque/Desktop/CPSC210/project_a4n2b/src/background.jpg");
        imageViewer.setImage(image);
//        imageViewer.setFitWidth(800);
//        imageViewer.setFitHeight(800);
        //anchorPaneCrossed.getChildren().addAll(imageViewer);
    }

    @FXML
    protected void goBack() throws IOException {

        MainFxGUI.verifiedScene();

    }

    @FXML
    protected void paneSetUp() {

        for (Item item : currentProfile.getToDoList().getItemsList()) {
            if (item.getCrossedOff()) {
                javafx.scene.control.Label label = new Label();
                label.setText(item.getItemName());
                label.setFont(new Font("CMU Serif",15));
                verticalBox.getChildren().addAll(label);
            }
        }

    }



}
