package dk.itu.garbage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // define items
        ItemsDB.initialize(this@MainActivity)
        itemsDB = ItemsDB.get()

        // define text input
        val insertItem = findViewById<EditText>(R.id.insert_item)

        // define WHERE onClick activity
        val whereItem = findViewById<Button>(R.id.where_button)
        whereItem.setOnClickListener {
            // get text from input
            val itemText = insertItem.text.toString()
            // retrieve location from ItemsDB
            val itemWhere = itemsDB.getItemWhere(itemText)

            // output result
            insertItem.append(String.format(" should be placed in: %s", itemWhere))
        }

        // define ADD ITEM onClick activity
        val addItem = findViewById<Button>(R.id.add_button)
        addItem.setOnClickListener { // start new activity
            val intent = Intent(this@MainActivity, AddItemActivity::class.java)
            startActivity(intent)
        }
    }
}