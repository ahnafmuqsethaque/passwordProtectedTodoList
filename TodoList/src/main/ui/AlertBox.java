package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class AlertBox {
    protected static void display(String title, String message) {
        Stage window = new Stage();

        //this line blocks ui from prev window until new alertBox is dealt with
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        //sets the min width of the popUP box
        window.setMinWidth(200);

        Label label = new Label(message);
        //label.setText(message);

        Button closeButton = new Button("Close window");
        closeButton.setOnAction(e -> window.close());

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label, closeButton);
        layout1.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();


    }
}
