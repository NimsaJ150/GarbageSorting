package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Model: Database of items
    private ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define items
        itemsDB = new ItemsDB();
        itemsDB.fillItemsDB();

        // define text input
        EditText insert_item= findViewById(R.id.insert_item);

        // define on Click activity
        Button whereItem= findViewById(R.id.where_button);
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
    }
}