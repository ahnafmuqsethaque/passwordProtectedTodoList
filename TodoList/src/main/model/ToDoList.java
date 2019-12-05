package model;

import exceptions.TooManyThingsToDoExceptions;
import exceptions.WeakPasswordException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import static ui.Main.collaboratorList;
//TODO: change whereever collaborator list used to mainFxGUI.CollaboratorList
import static ui.MainFxGUI.collaboratorList;

public class ToDoList extends Observerable implements Loadable, Saveable {

    private List<Item> itemsList;
    private People owner;

    public ToDoList(People ownerPeople) {
        itemsList = new ArrayList<>();
        owner = ownerPeople;
        addObserver(new MonitorItem());

    }

    // REQUIRES: There is atleast one item
    // MODIFIES: this
    // EFFECTS: removes item from itemsList

    public void removeItemFromList(int inputItemNumber) {
        itemsList.get(inputItemNumber - 1).setCrossedOff(true);
    }


    // MODIFIES: this
    // EFFECTS: add item to itemsList
    public void addItemToList(Item item) { //throws TooManyThingsToDoExceptions {
//        if (itemsList.size() > 5) {
//            throw new TooManyThingsToDoExceptions();
//        }
        itemsList.add(item);
        notifyObserver(itemsList.size() - 1, itemsList.size());
        // New stuff(i.e mapping) added here:

        item.getLocation().addItemLocation(item, getOwner());

    }

    public int size() {
        return itemsList.size();
    }

    public Item get(int i) {
        return itemsList.get(i);
    }

    // REQUIRES: There is some data in text file
    // MODIFIES: this
    // EFFECTS: loads items from a text file to this todolist
    @Override
    public void load() throws IOException, WeakPasswordException { //, TooManyThingsToDoExceptions{
        List<String> lines = Files.readAllLines(Paths.get("./data/outputs.txt"));

        String matchPerson = "";

        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Item item = new RegularItem(partsOfLine.get(1));   //Todo: changed to 1-1
            item.setCrossedOff(Boolean.valueOf(partsOfLine.get(2)));
            if (!matchPerson.equals(partsOfLine.get(0))) {

                People people = new People(partsOfLine.get(0), partsOfLine.get(3));
//                try {
                people.getToDoList().addItemToList(item);
//                } catch (TooManyThingsToDoExceptions tooManyThingsToDo) {
//                    System.out.println("too many items");
//                }
                collaboratorList.add(people);
                matchPerson = people.getName();

            } else {
                int size = collaboratorList.size();
//                try {
                collaboratorList.get(size - 1).getToDoList().addItemToList(item);
//                } catch (TooManyThingsToDo tooManyThingsToDo) {
//                    System.out.println("too many items");
//                }
            }
        }
    }


    // EFFECTS: saves items from this todolist to text file
    @Override
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("./data/outputs.txt", "UTF-8");
        ArrayList<String> itemsInfo = new ArrayList<>();

        for (int i = 1; i < (collaboratorList.size() + 1); i++) {
            List<Item> specificItemsList = collaboratorList.get(i - 1).getToDoList().itemsList;
            for (Item item : specificItemsList) {

                itemsInfo.add(collaboratorList.get(i - 1).getName() + " "
                        + item.getItemName() + " " + item.getCrossedOff() + " "
                        + collaboratorList.get(i - 1).getPassword());
            }

        }

        for (String line : itemsInfo) {
            writer.println(line);
        }
        writer.close();

    }


    // EFFECTS: returns an arrayList of Strings that was split from text file
    public ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public People getOwner() {
        return owner;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

}


