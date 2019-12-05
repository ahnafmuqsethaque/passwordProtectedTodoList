package model;


import exceptions.WeakPasswordException;

public class People {
    private String name;
    //private String email;
    private String password;
    private ToDoList todolist;


//:TODO weak pass exp. thrown here instead of main.addPeople
    public People(String name, String password) throws WeakPasswordException {
        this.name = name;
        //this.email = email;
       // TODO: change the condition below, its wrong
        if (password.length() > 25) {
            throw new WeakPasswordException();
        }
        this.password = password;

        this.todolist = new ToDoList(this);
    }

    // REQUIRES: At least one People exists in collaboratorList
    // EFFECTS: Returns name of People object
    public String getName() {
        return name;
    }

    // REQUIRES: At least one People exists in collaboratorList
    // MODIFIES: this
    // EFFECTS: Returns password of People object

    public String getPassword() {
        return password;
    }


    // MODIFIES: this
    // EFFECTS: Returns todolist of People object

    public ToDoList getToDoList() {
        return todolist;
    }

}
