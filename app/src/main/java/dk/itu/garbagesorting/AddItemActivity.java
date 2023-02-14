package dk.itu.garbagesorting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    // Model: Database of items
    private static ItemsDB itemsDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // define items
        ItemsDB.initialize(AddItemActivity.this);
        itemsDB = ItemsDB.get();

        // Text Fields
        EditText newWhat = findViewById(R.id.what_text);
        EditText newWhere = findViewById(R.id.where_text);

        // define ADD ITEM onClick activity
        Button addItem = findViewById(R.id.add_button);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get text from input fields
                String whatS = newWhat.getText().toString().trim();
                String whereS = newWhere.getText().toString().trim();
                // check whether input was provided
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    // add item to the itemsDB
                    itemsDB.addItem(whatS, whereS);
                    newWhat.setText("");
                    newWhere.setText("");
                } else
                    Toast.makeText(AddItemActivity.this, R.string.empty_toast, Toast.LENGTH_LONG).show();
            }
        });

    }
}