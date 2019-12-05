package model;

import exceptions.WeakPasswordException;
import model.People;
import model.ToDoList;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPeople {

    private People people;

    @BeforeEach
    public void runBefore() {
        try {
            people = new People("John", "aaa");
        } catch (WeakPasswordException e) {
            //
        }


    }

    @Test
    public void testGetPeopleName() {
        assertEquals("John", people.getName());
        assertEquals("aaa", people.getPassword());
    }

    @Test
    public void testGetToDol() {
        assertEquals(people.getToDoList(), people.getToDoList());
    }


    @Test
    public void testGetPeoplePassword() {
        assertEquals("aaa", people.getPassword());
    }
}
