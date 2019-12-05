package model;

public class MonitorItem implements Observer {


    // EFFECTS: prints out the updated number of items in todoList
    @Override
    public void update(int sizeBefore, int sizeAfter) {
        System.out.println("Previously you had " + sizeBefore + " items in todoList.");
        System.out.println("Now you have " + sizeAfter + " items in todoList.");
    }
}
