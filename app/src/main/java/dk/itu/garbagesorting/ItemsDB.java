package dk.itu.garbagesorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private List<Item> itemsDB = new ArrayList<>();

    private ItemsDB() {
        fillItemsDB();
    }

    public static void initialize() {
        if (sItemsDB == null) sItemsDB = new ItemsDB();
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    public String listItems() {
        String r = "";
        for (Item i : itemsDB)
            r = r + "\n Buy " + i.toString();
        return r;
    }

    public void addItem(String what, String where) {
        itemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        itemsDB.add(new Item("coffee", "Food"));
        itemsDB.add(new Item("carrots", "Food"));
        itemsDB.add(new Item("milk carton", "Food"));
        itemsDB.add(new Item("bread", "Food"));
        itemsDB.add(new Item("butter", "Food"));
        itemsDB.add(new Item("cheese", "Food"));
        itemsDB.add(new Item("soup can", "Metal"));
        itemsDB.add(new Item("plastic bag", "Plastic"));
    }

    public String getItemWhere(String item) {
        // loop over all items in the db
        for (Item i : itemsDB) {
            // check if "what" of the item matches
            if (Objects.equals(i.getWhat(), item)) {
                // return the "where" value
                return i.getWhere();
            }
        }
        // not match found, then return not found
        return "not found";
    }
}
