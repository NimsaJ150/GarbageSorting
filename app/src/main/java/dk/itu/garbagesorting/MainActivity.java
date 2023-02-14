package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define items
        ItemsDB.initialize(MainActivity.this);
        itemsDB = ItemsDB.get();

        // define text input
        EditText insert_item = findViewById(R.id.insert_item);

        // define WHERE onClick activity
        Button whereItem = findViewById(R.id.where_button);
        whereItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get text from input
                String item_text = String.valueOf(insert_item.getText());
                // retrieve location from ItemsDB
                String item_where = itemsDB.getItemWhere(item_text);
                // output result
                insert_item.append(String.format(" should be placed in: %s", item_where));
            }
        });

        // define ADD ITEM onClick activity
        Button addItem = findViewById(R.id.add_button);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start new activity
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }
}