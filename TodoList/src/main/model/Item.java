package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Item {
    protected String itemName;
    protected boolean crossedOff;
    protected Location itemLocation;
    //protected ArrayList<People> location

    public Item(String itemName) {
        this.itemName = itemName;
        crossedOff = false;
        itemLocation = new Location();
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getCrossedOff() {
        return crossedOff;
    }

    public void setCrossedOff(boolean crossedOff) {
        this.crossedOff = crossedOff;
    }

    public Location getLocation() {
        return itemLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return crossedOff == item.crossedOff
                && Objects.equals(itemName, item.itemName)
                && Objects.equals(itemLocation, item.itemLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, crossedOff, itemLocation);
    }
}
