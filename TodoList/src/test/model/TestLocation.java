package model;

import exceptions.WeakPasswordException;
import model.Item;
import model.Location;
import model.People;
import model.RegularItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLocation {

    @Test
    void testPrint() throws WeakPasswordException {

        Item item = new RegularItem("testItem");
        People p =  new People("a", "a");
        p.getToDoList().addItemToList(item);
        Location l = new Location();
        l.addItemLocation(item, p);
        l.printLocation(item);
        //test passses based on human verification
    }

    @Test
    void testAddItemLocationDoesNotContainAlready() throws WeakPasswordException{
        Item item = new RegularItem("testItem");
        Location l = new Location();
        People people = new People("a", "a");
        List<String> peopleName = new ArrayList<String>();
        peopleName.add("a");
        //Map<Item, ArrayList<String>> location = new HashMap<Item, ArrayList<String>>();
        l.addItemLocation(item,people);

        assertEquals("a",l.getMap().get(item));
    }

    @Test
    void testAddItemLocationContainsAlready() throws WeakPasswordException{
        Item item = new RegularItem("testItem");
        Location l = new Location();
        People people = new People("a", "a");
        People people2 = new People("b", "b");
        List<String> peopleName = new ArrayList<String>();
        peopleName.add("a");
        l.addItemLocation(item,people);


        assertEquals("a",l.getMap().get(item).get(0));
        l.addItemLocation(item,people2);
        assertEquals("b",l.getMap().get(item).get(1));
    }
}
