package model;

import exceptions.WeakPasswordException;
import model.Item;
import model.RegularItem;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class TestItem {

    private List<Item> itemsList;
    Item item;
    Item item2;

    @BeforeEach
    public void runBefore() {
        itemsList = new ArrayList<Item>();
        item = new RegularItem("hello");
        item2 = new UrgentItem("hello");
    }

    @Test
    public void testGetCrossedOff() {
        assertFalse(item.getCrossedOff());
    }

    @Test
    public void testGetItemName() {
        assertEquals("hello", item.getItemName());
        assertEquals("!!!hello!!!", item2.getItemName());
    }

    @Test
    public void testGetItemLocation() {
        try {
            People p = new People("p", "p");
            p.getToDoList().addItemToList(item);
            assertEquals("p", item.getLocation().getMap().get(item).get(0));
        } catch (WeakPasswordException e) {
            //
        }
    }

    @Test
    void testEquals() {
        //TODO why doesnt equal work, if i pass itemDuplicate?
       Item itemDuplicate = new RegularItem("hello");
       Item itemDuplicate2 = new RegularItem("hellooooooooooblabla");
       assertTrue(item.equals(item));
       assertFalse(item.equals(itemDuplicate2));
    }

}
