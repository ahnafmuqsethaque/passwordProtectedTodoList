package model;

import java.util.*;

public class Location {

    private Map<Item, ArrayList<String>> location;

    public Location() {
        location = new HashMap<Item, ArrayList<String>>();
    }


    // MODIFIES: this
    // EFFECTS: adds items to this location if it doesn't already contain it

    public void addItemLocation(Item item, People people) { //taken,totake

        ArrayList<String> peopleName;
        if (!location.containsKey(item)) {
            // Need to create a list of people names for the value
            // of the key in the map
            peopleName = new ArrayList<String>();
            peopleName.add(people.getName());
            location.put(item, peopleName);
        } else {
            // The item has location for it,
            // add to the list of people.
            peopleName = location.get(item);
            peopleName.add(people.getName());
        }
    }

//    public void printLocation() {
//        for (Map.Entry<Item, ArrayList<String>> e : location.entrySet()) {
//            ArrayList<String> peopleName = e.getValue();
//            for (String c : peopleName) {
//                System.out.println(e.getKey() + " -> " + c);
//            }
//        }
//    }

    //EFFECTS: prints the location of item it is currently contained in
    public void printLocation(Item item) {

        System.out.println(item.getItemName() + ":");
        for (String s : location.get(item)) {
            System.out.println("     " + s);
        }
    }

    public Map<Item, ArrayList<String>> getMap() {
        return location;
    }








}
