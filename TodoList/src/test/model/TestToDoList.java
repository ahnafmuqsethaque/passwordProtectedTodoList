package model;

import exceptions.TooManyThingsToDoExceptions;
import exceptions.WeakPasswordException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ui.Main.collaboratorList;

public class TestToDoList {

    private ToDoList load;
    private Saveable save;
    private Item item;


    @BeforeEach
    public void runBefore() {
        try {
            People people = new People("a", "a");
            load = new ToDoList(people);
            save = new ToDoList(people);
            item = new RegularItem("hello");
        } catch (WeakPasswordException e) {
            //
        }
    }


    @Test
    public void testSize() {
        assertEquals(0, load.size());
    }


    @Test
    public void testLoad() throws IOException {

//        ahnaf bleb false a
//        muqset cleb true b
//        ahnaf fleb false a


        try {
        load.load();
        } catch (Exception tooManyThingsToDoExceptions) {
            System.out.println("Too many items");
        }

        assertEquals("ahnaf", collaboratorList.get(0).getName());
        assertEquals("bleb", collaboratorList.get(0).getToDoList().get(0).getItemName());
        assertFalse(collaboratorList.get(0).getToDoList().get(0).getCrossedOff());
        assertEquals("a", collaboratorList.get(0).getPassword());

        assertEquals("muqset", collaboratorList.get(1).getName());
        assertEquals("cleb", collaboratorList.get(1).getToDoList().get(0).getItemName());
        assertTrue(collaboratorList.get(1).getToDoList().get(0).getCrossedOff());
        assertEquals("b", collaboratorList.get(1).getPassword());

    }

    @Test
    public void testSave() throws IOException {

        try {


            People people1 = new People("ahnaf", "a");
            People people2 = new People("muqset", "b");


        RegularItem item1 = new RegularItem("bleb");
        RegularItem item2 = new RegularItem("cleb");
        RegularItem item3 = new RegularItem("fleb");
        item2.setCrossedOff(true);
//        try {
        people1.getToDoList().addItemToList(item1);
        people2.getToDoList().addItemToList(item2);
        people1.getToDoList().addItemToList(item3);
//        } catch (TooManyThingsToDoExceptions tooManyThingsToDoExceptions) {
//            System.out.println("too many items");
//        }

        collaboratorList.add(people1);
        collaboratorList.add(people2);

        save.save();
        } catch (WeakPasswordException e) {
            fail();
        }

        //at some point call this
        //load.load();

//        assertEquals("ahnaf", collaboratorList.get(0).getName());
//        assertEquals("bleb", collaboratorList.get(0).getToDoList().get(0).getItemName());
//        assertFalse(collaboratorList.get(0).getToDoList().get(0).getCrossedOff());
//        assertEquals("a", collaboratorList.get(0).getPassword());

//        assertEquals("muqset", collaboratorList.get(1).getName());
//        assertEquals("cleb", collaboratorList.get(1).getToDoList().get(0).getItemName());
//        assertTrue(collaboratorList.get(1).getToDoList().get(0).getCrossedOff());
//        assertEquals("b", collaboratorList.get(1).getPassword());

    }


    @Test
    public void testRemoveItemFromList() {

//        try {
        load.addItemToList(item);
//        } catch (TooManyThingsToDoExceptions tooManyThingsToDoExceptions) {
//            System.out.println("too many items");
//        }
        assertFalse(load.get(0).getCrossedOff());
        load.removeItemFromList(1);
        assertTrue(load.get(0).getCrossedOff());

    }

    @Test
    public void testTooManyThingsNothingThrown() {

//        try {
        load.addItemToList(item);
        load.addItemToList(item);
        load.addItemToList(item);
//        } catch (TooManyThingsToDoExceptions tooManyThingsToDoExceptions) {
//            fail("Did not expect exception here, but it was thrown");
//        }
    }

//    @Test
//    public void testTooManyThingsExceptionThrown() {
//
//        try {
//            load.addItemToList(item);
//            load.addItemToList(item);
//            load.addItemToList(item);
//            load.addItemToList(item);
//            load.addItemToList(item);
//            fail("Exception not thrown");
//        } catch (TooManyThingsToDoExceptions tooManyThingsToDoExceptions) {
//            tooManyThingsToDoExceptions.printStackTrace();
//        }
//    }
    @Test
    public void testGetOwner() {
        assertEquals("a",load.getOwner());
    }
//
    @Test
    public void testWeakPassWordException() {
        try {
            People p = new People("a","a");
        } catch(WeakPasswordException e) {
            fail();
        }

        try {
            People p = new People("a","asdfghjklzkkk");
            fail();
        } catch(WeakPasswordException e) {
            //expected
        }

    }


}
