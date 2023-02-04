package dk.itu.garbagesorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemsDB {
    private List<Item> ItemsDB= new ArrayList<>();

    public ItemsDB() { }

    public String listItems() {
        String r= "";
        for(Item i: ItemsDB)
            r= r+"\n Buy " + i.toString();
        return r;
    }

    public void addItem(String what, String where){
        ItemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        ItemsDB.add(new Item("coffee", "Food"));
        ItemsDB.add(new Item("carrots", "Food"));
        ItemsDB.add(new Item("milk carton", "Food"));
        ItemsDB.add(new Item("bread", "Food"));
        ItemsDB.add(new Item("butter", "Food"));
        ItemsDB.add(new Item("cheese", "Food"));
        ItemsDB.add(new Item("soup can", "Metal"));
        ItemsDB.add(new Item("plastic bag", "Plastic"));
    }

    public String getItemWhere(String item) {
        // loop over all items in the db
        for (Item i: ItemsDB) {
            // check if "what" of the item matches
            if(Objects.equals(i.getWhat(), item)) {
                // return the "where" value
                return i.getWhere();
            }
        }
        // not match found, then return not found
        return "not found";
    }
}
