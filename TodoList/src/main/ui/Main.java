package ui;

import exceptions.TooManyThingsToDoExceptions;
import exceptions.WeakPasswordException;
import model.*;
import network.ApiWeather;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner userInput = new Scanner(System.in);

     ///// should this be inside Main.main()? or just Main?
    private static boolean isOver = false;
    public static List<People> collaboratorList = new ArrayList<People>();

    static People currentProfile = null;

    public static void main(String[] args) throws IOException {

        try {
            People people1 = new People("a", "a");
            Loadable load = new ToDoList(people1);
            load.load();
        } catch (Exception e) {
            System.out.println("too many items");
        }

        printWeather();

        while (!isOver) {
            ask();
            firstMenu();
        }
    }

    protected static void printWeather() {
        ApiWeather apiWeather = new ApiWeather("80315d4688bacb8d674b29147f4b1ef4");
        apiWeather.setCurrentWeatherDescription();
        System.out.println("Live Weather Description: " + apiWeather.getCurrentWeatherDescription());
    }

    // REQUIRES: Integer i is an element of the IntegerSet
    // MODIFIES: this
    // EFFECTS: Integer i is removed from the IntegerSet

    protected static void firstMenu() throws IOException {

       // userInput = new Scanner(System.in);
        String initialInput = userInput.nextLine();

        if (initialInput.equals("1")) {
//            try {
            addPeople();
//            } catch (WeakPasswordException e) {
//                System.out.println("Weak password (i.e. less than 5 characters)");
//            }
        } else if (initialInput.equals("2")) {
            loginOrLogout();
            while (!isOver) {
                askExtended();
                extendedMenu();
            }
        } else if (initialInput.equals("3")) {
            isOver = true;
        }
    }

    private static void extendedMenu() throws IOException {

        String initialInput = userInput.nextLine();

        if (initialInput.equals("1")) {
            whichItem();
        } else if (initialInput.equals("2")) {
            itemRemover();
        } else if (initialInput.equals("3")) {
            printBothLists();
        } else if (initialInput.equals("4")) {
            saveFile();
        } else if (initialInput.equals("5")) {
            isOver = true;
        } else if (initialInput.equals("6")) {
            printItemLocation();
        }
    }


    private static void loginOrLogout() {
        System.out.println("[1] Login");
        System.out.println("[2] Logout");
        String inputNumber = userInput.nextLine();
        if (Integer.parseInt(inputNumber) == 1) {
            matchPassword();
        } else if (Integer.parseInt(inputNumber) == 2) {
            currentProfile = null;
            System.out.println("Successfully logged out!");
            matchPassword();
        }
    }

    //TODO: Done
    private static void saveFile() throws IOException {
        try {

            People people = new People("a", "a");

            Saveable save = new ToDoList(people);

            save.save();
        } catch (WeakPasswordException e) {
            //
        }
    }


    private static void matchPassword() {
        printPeopleList();
        System.out.println("Which profile would you like to login?");
        String inputNumber = userInput.nextLine();
        System.out.println("Enter password: ");
        String inputPassword = userInput.next();
        while (!collaboratorList.get(Integer.parseInt(inputNumber) - 1).getPassword().equals(inputPassword)) {
            System.out.println("Wrong Password, try again: ");
            System.out.println("Enter password: ");
            inputPassword = userInput.next();
        }
        System.out.println("Matched, yay!");
        currentProfile = collaboratorList.get(Integer.parseInt(inputNumber) - 1);
    }


    // EFFECTS: Prints out options to choose from
    private static void ask() {
        System.out.println("What would you like to do?");
        System.out.println("[1] Make a new todo-list");
        System.out.println("[2] Login/Logout");
        System.out.println("[3] Exit program");

    }

    private static void askExtended() {
        System.out.println("[1] Add items to ToDoList");
        System.out.println("[2] Cross off an item");
        System.out.println("[3] Show all the items");
        System.out.println("[4] Save data");
        System.out.println("[5] Exit program");
        System.out.println("[6] Print map");
    }


    private static void whichItem() {
        System.out.println("[1] Regular item");
        System.out.println("[2] Urgent item");
        String inputNumber = userInput.nextLine();
        if (inputNumber.equals("1")) {
            regularItemAdder();
        } else if (inputNumber.equals("2")) {
            urgentItemAdder();
        }
    }


    // MODIFIES: Item
    // EFFECTS: Add the an new item with name inputItem in itemsList

    private static void regularItemAdder() {


        if (currentProfile != null) {

            System.out.println("Enter the text for regular item: ");
            String inputItem = userInput.nextLine();
            Item item = new RegularItem(inputItem);
//            try {
            currentProfile.getToDoList().addItemToList(item);
//            } catch (TooManyThingsToDoExceptions e) {
//                //e.printStackTrace();
//                System.out.println("You got too many items on the list");
//            } finally {
//                System.out.println("Reminder: Complete your todo-list items!!");
//            }

        } else {
            System.out.println("Please login first!");
        }
    }

    private static void urgentItemAdder() {

        if (currentProfile != null) {

            System.out.println("Enter the text for regular item: ");
            String inputItem2 = userInput.nextLine();
            Item item = new UrgentItem(inputItem2);
//            try {
            currentProfile.getToDoList().addItemToList(item);
//            } catch (TooManyThingsToDoExceptions e) {
//                e.printStackTrace();
//                System.out.println("Too many incomplete items");
//            } finally {
//                System.out.println("Complete you todo-list items!!");
//            }

        } else {
            System.out.println("Please login first!");
        }
    }

    // REQUIRES: At least one ToDoList exists
    // MODIFIES: Item
    // EFFECTS: Sets crossedOff status of chosen Item to false

    private static void itemRemover() {
        if (currentProfile != null) {

            //printItemList();
            printList("todo");
            System.out.println("Which item do you want to cross-off?");
            String inputItemNumber = userInput.nextLine();
            currentProfile.getToDoList().removeItemFromList(Integer.parseInt(inputItemNumber));

        } else {
            System.out.println("Please login first!");
        }
    }

    private static void printItemLocation() {
        if (currentProfile != null) {

            //printItemList();
            printList("todo");
            System.out.println("Which item's location do you want to check?'");
            String inputItem = userInput.nextLine();

            Location locationItem = currentProfile.getToDoList().get(Integer.parseInt(inputItem) - 1).getLocation();

            locationItem.printLocation(currentProfile.getToDoList().get(Integer.parseInt(inputItem) - 1));

        } else {
            System.out.println("Please login first!");
        }
    }


    // EFFECTS: Prints printItemList and CrossedOffList

    private static void printBothLists() {

        if (currentProfile != null) {
            System.out.println(currentProfile.getName() + "'s Profile: ");
            System.out.println("");

            System.out.println("Todo list: ");

            //printItemList();
            printList("todo");

            //System.out.println("");

            System.out.println("Crossed off list:");

            //printCrossedOffList();
            printList("cross");
            //System.out.println("");
        } else {
            System.out.println("No data found!");
        }
    }


    // EFFECTS: Prints Items which have crossedOff status as false


//    private static void printItemList() {
//
//        for (int i = 1; i <= currentProfile.getToDoList().size(); i++) {
//
//            if (!currentProfile.getToDoList().get(i - 1).getCrossedOff()) {
//
//                System.out.println(i + "."
//                        + currentProfile.getToDoList().get(i - 1).getItemName());
//            }
//        }
//        System.out.println("");
//    }
//
//    // EFFECTS: Prints Items which have crossedOff status as true
//
//    private static void printCrossedOffList() {
//
//        for (int i = 1; i <= currentProfile.getToDoList().size(); i++) {
//
//            if (currentProfile.getToDoList().get(i - 1).getCrossedOff()) {
//
//                System.out.println(i + "."
//                        + currentProfile.getToDoList().get(i - 1).getItemName());
//            }
//        }
//        System.out.println("");
//    }

//TODO:
    private static void printList(String listName) {

        ArrayList<String> itemListString = new ArrayList<>();
        ArrayList<String> crossedOffListString = new ArrayList<>();

        for (int i = 1; i <= currentProfile.getToDoList().size(); i++) {

            if (!currentProfile.getToDoList().get(i - 1).getCrossedOff()) {

                itemListString.add(i + "."
                        + currentProfile.getToDoList().get(i - 1).getItemName());
            } else {
                crossedOffListString.add(i + "."
                        + currentProfile.getToDoList().get(i - 1).getItemName());
            }
        }

        printHelper(listName, itemListString, crossedOffListString);
//        if (listName.equals("todo")) {
//            for (int i = 1; i <= currentProfile.getToDoList().size(); i++) {
//                System.out.println(itemListString.get(i - 1));
//                //System.out.println("");
//            }
//        } else {
//            for (int i = 1; i <= currentProfile.getToDoList().size(); i++) {
//                System.out.println(crossedOffListString.get(i - 1));
//               // System.out.println("");
//            }
//        }


    }

    private static void printHelper(String listName, ArrayList<String> itemListStr, ArrayList<String> crossedListStr) {
        if (listName.equals("todo")) {
            for (int i = 1; i <= itemListStr.size(); i++) {
                System.out.println(itemListStr.get(i - 1));
                //System.out.println("");
            }
        } else {
            for (int i = 1; i <= crossedListStr.size(); i++) {
                System.out.println(crossedListStr.get(i - 1));
                // System.out.println("");
            }
        }

        System.out.println(" ");
    }




    // EFFECTS: Prints name and email of every people object from collaboratorList
    private static void printPeopleList() {
        for (int i = 1; i <= collaboratorList.size(); i++) {

            System.out.println(i + "." + "Name: " + collaboratorList.get(i - 1).getName());
            // + "Email: " + collaboratorList.get(i - 1).getEmail());
        }
        System.out.println("");
    }

//TODO: DONE
    // MODIFIES: People
    // EFFECTS: Adds people to a list     ------->>>>>> change this
    private static void addPeople()  { //throws WeakPasswordException {
        System.out.println("Enter name of profile: ");
        String inputPersonName = userInput.nextLine();
        System.out.println("Set password: ");
        String inputPersonPassword = userInput.nextLine();
        // TODO; exception moved to People class
//        if (inputPersonPassword.length() < 2) {
//            throw new WeakPasswordException();
//        }
        try {
            People people = new People(inputPersonName, inputPersonPassword);
            collaboratorList.add(people);
            System.out.println("Profile and ToDoList successfully created!");
        } catch (WeakPasswordException e) {
            System.out.println("Password too short");
        }

    }

}

