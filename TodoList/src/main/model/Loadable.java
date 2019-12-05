package model;

import exceptions.TooManyThingsToDoExceptions;
import exceptions.WeakPasswordException;

import java.io.IOException;

public interface Loadable {
    void load() throws IOException, TooManyThingsToDoExceptions, WeakPasswordException;
}
