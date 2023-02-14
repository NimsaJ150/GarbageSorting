package dk.itu.garbage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        // define items
        ItemsDB.initialize(this@AddItemActivity)
        itemsDB = ItemsDB.get()

        // Text Fields
        val newWhat = findViewById<EditText>(R.id.what_text)
        val newWhere = findViewById<EditText>(R.id.where_text)

        // define ADD ITEM onClick activity
        val addItem = findViewById<Button>(R.id.add_button)
        addItem.setOnClickListener {
            // get text from input fields
            val whatS: String = newWhat.text.toString().trim { it <= ' ' }
            val whereS: String = newWhere.text.toString().trim { it <= ' ' }

            // check whether input was provided
            if (whatS.isNotEmpty() && whereS.isNotEmpty()) {
                // add item to the itemsDB
                itemsDB?.addItem(whatS, whereS)
                newWhat.setText("")
                newWhere.setText("")
            } else Toast.makeText(this@AddItemActivity, R.string.empty_toast, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        // Model: Database of items
        private var itemsDB: ItemsDB? = null
    }
}