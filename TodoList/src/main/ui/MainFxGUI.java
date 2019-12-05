package ui;

import exceptions.WeakPasswordException;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;
import network.ApiWeather;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainFxGUI extends Application {

    public static Stage window;


    public static List<People> collaboratorList = new ArrayList<People>();
    static People currentProfile = null;
    public static AudioStream audioStream;
    //private AudioStream

    //EFFECTS: starts the application

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //loadAudio();

        Parent root = FXMLLoader.load(getClass().getResource("LoginFxML.fxml"));

//        String musicFile = "music.wav";
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(musicFile);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to load audio file");
//        }
//
//
//        try {
//            audioStream = new  AudioStream(inputStream);
//        } catch (IOException e) {
//            System.out.println("Unable to play audio file");;
//        }

        //AudioPlayer.player.start(audioStream);

        window.setTitle("ToDoList Application");
        window.setScene(new Scene(root, 600, 500));
        window.show();

    }

//    private void loginView() throws IOException {
//
//    }


    public static void main(String[] args) {
        try {
            People people1 = new People("a", "a");
            Loadable load = new ToDoList(people1);
            load.load();
        } catch (Exception e) {
            // System.out.println("too many items");
        }

        launch(args);
    }


    protected static void matchPassword(String inputPassword, int profilePointer) {
//inside get put the profile in the list
        boolean bool = true;
        if (!collaboratorList.get(profilePointer).getPassword().equals(inputPassword)) {
            AlertBox.display(":(", "Wrong password!");
            bool = false;
        }

        //TODO: check this, set pointer from the controller class
        if (bool) {
            currentProfile = collaboratorList.get(profilePointer);
        } else {
            currentProfile = null;
        }
    }

    protected static void loginScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("LoginFxML.fxml"));
        //window.setTitle("ToDoList Application");
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    protected static void verifiedScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("OptionsFxML.fxml"));
        //window.setTitle("ToDoList Application");
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    protected static void itemAdderScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("ItemAdderFxML.fxml"));
        //window.setTitle("ToDoList Application");
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    protected static void itemDisplayScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("ShowItemList.fxml"));
        //window.setTitle("ToDoList Application");
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }


    protected static void itemRemoverScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("ItemRemoverFxML.fxml"));
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    protected static void crossedItemScene() throws IOException {
        Parent root = FXMLLoader.load(MainFxGUI.class.getResource("ShowCrossedOffItem.fxml"));
        window.setScene(new Scene(root, 600, 500));
        window.show();
    }

    protected static void addProfile(String name, String password) { //throws WeakPasswordException {
        try {
            People people = new People(name, password);
            collaboratorList.add(people);
            //System.out.println("Profile and ToDoList successfully created!");
        } catch (WeakPasswordException e) {
            System.out.println("Password too short");
        }

    }

    protected static void saveFile() throws IOException {
        try {

            People people = new People("a", "a");

            Saveable save = new ToDoList(people);

            save.save();
        } catch (WeakPasswordException e) {
            //
        }
    }


    protected static void regularItemAdder(Item item) {
        if (currentProfile != null) {
//            try {
            currentProfile.getToDoList().addItemToList(item);
//            } catch (TooManyThingsToDoExceptions e) {

//            }
        } else {
            System.out.println("Please login first!");
        }
    }

    protected static void itemRemover(int inputItemNumber) {
        if (currentProfile != null) {
            currentProfile.getToDoList().removeItemFromList(inputItemNumber);
        } else {
            System.out.println("Please login first!");
        }
    }

    //
    protected static String printWeather() {
        ApiWeather apiWeather = new ApiWeather("80315d4688bacb8d674b29147f4b1ef4");
        apiWeather.setCurrentWeatherDescription();
        return apiWeather.getCurrentWeatherDescription();
    }

    protected static void itemDisplay(VBox topVerticalBox) {

        TableView<Item> table = new TableView<>();

        //Column 1


        TableColumn numberCol = new TableColumn("Item no.");
        numberCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>) p
                -> new ReadOnlyObjectWrapper(table.getItems().indexOf(p.getValue()) + 1));
        //numberCol.setSortable(true);


        //Column 2
        TableColumn<Item, String> nameColumn = new TableColumn<>("Item");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        //Column 3
        TableColumn<Item, Boolean> completedStatus = new TableColumn<>("Completed Status");
        completedStatus.setMinWidth(200);
        completedStatus.setCellValueFactory(new PropertyValueFactory<>("crossedOff"));

        table.setItems(getItems());
        table.getColumns().addAll(numberCol, nameColumn, completedStatus);


        topVerticalBox.getChildren().addAll(table);


        Scene scene = new Scene(topVerticalBox, 600,500);

        window.setScene(scene);
        window.show();

    }

    protected static ObservableList<Item> getItems() {

        ObservableList<Item> items = FXCollections.observableArrayList();
        items.addAll(currentProfile.getToDoList().getItemsList());
        return items;
    }

    protected static void loadAudio() {
//        String path = "music.mp3";
//        Media musicFile = new Media(new File(path).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
//        mediaPlayer.play();


        String musicFile = "/Users/ahnafmuqsethaque/Desktop/CPSC210/project_a4n2b/src/music.wav";

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(musicFile);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load audio file");
        }


        try {
            audioStream = new  AudioStream(inputStream);
        } catch (IOException e) {
            System.out.println("Unable to play audio file");;
        }


    }

    protected static void playStop(boolean bool) {
        if (bool) {
            AudioPlayer.player.start(audioStream);
        } else {
            AudioPlayer.player.stop(audioStream);
        }
    }


}
