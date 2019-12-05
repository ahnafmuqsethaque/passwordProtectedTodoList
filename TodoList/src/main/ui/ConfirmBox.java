package ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class ConfirmBox {

    static Stage window;

    static boolean answer;


    //MODIFIES: this
    //EFFECTS: opens a new confirm box with relevant title and message

    public static boolean display(String title, String message) {
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        //sets the min width of the popUP box
        window.setMinWidth(200);

        Label label = new Label(message);
        //label.setText(message);

        //When answer = true , i.e. yes
        Button buttonYes = new Button("Yes");
        buttonYes.setOnAction(e -> confirmButtonResponse("Yes"));

        Button buttonNo = new Button("No");
        buttonNo.setOnAction(e -> confirmButtonResponse("No"));

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label, buttonYes, buttonNo);
        layout1.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();

        return answer;

    }

    //Deal with appropriate action depending on Yes/No response

    public static void confirmButtonResponse(String response) {
        if (response.equals("Yes")) {
            answer = true;
            window.close();
        } else {
            answer = false;
            window.close();
        }
    }
}
