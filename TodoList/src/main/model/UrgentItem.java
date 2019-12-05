package model;

public class UrgentItem extends Item {

    public UrgentItem(String itemName) {
        super(itemName);
        this.itemName = "!!!" + itemName + "!!!";
    }
}
