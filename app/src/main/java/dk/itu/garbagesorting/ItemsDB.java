package dk.itu.garbagesorting;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private final HashMap<String, String> itemsMap = new HashMap<String, String>();

    private ItemsDB(Context context) {
        fillItemsDB(context);
    }

    public static void initialize(Context context) {
        if (sItemsDB == null) sItemsDB = new ItemsDB(context);
    }

    public static ItemsDB get() {
        if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
        return sItemsDB;
    }

    public String listItems() {
        StringBuilder r = new StringBuilder();
        for (HashMap.Entry<String, String> item : itemsMap.entrySet())
            r.append("\n ").append(item.getKey()).append(" in: ").append(item.getValue());
        return r.toString();
    }

    public void fillItemsDB(Context context) {
        try {
            String what, where;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("items.txt")));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
            }
        } catch (IOException ignored) {

        }
    }

    public void addItem(String what, String where) {
        itemsMap.put(what, where);
    }

    public void removeItem(String what) {
        if (itemsMap.get(what) != null) itemsMap.remove(what);
    }

    public String getItemWhere(String what) {
        String where;
        where = itemsMap.get(what);
        // not match found, then return not found
        if (where == null) return "not found";
        return where;
    }

    public int size() {
        return itemsMap.size();
    }
}
