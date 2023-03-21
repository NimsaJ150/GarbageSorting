package dk.itu.garbage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentWhere.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentWhere : Fragment() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // define items
        itemsDB = ViewModelProvider(requireActivity())[ItemsDB::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_where, container, false)

        // define text input
        val insertItem = v.findViewById<EditText>(R.id.insert_item)

        // define WHERE onClick activity
        val whereItem = v.findViewById<Button>(R.id.where_button)
        whereItem.setOnClickListener {
            // get text from input
            val itemText = insertItem.text.toString()
            // retrieve location from ItemsDB
            val itemWhere = itemsDB.getItemWhere(itemText)

            // output result
            insertItem.append(String.format(" should be placed in: %s", itemWhere))
        }

        // define ADD ITEM onClick activity
        val addItem = v.findViewById<Button>(R.id.modify_button)
        addItem.setOnClickListener { // start new activity
            /*
            val intent = Intent(context, ActivityModify::class.java)
            startActivity(intent)
             */

            // replace fragment
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container_ui,
                    FragmentModify()
                ).commit()
        }

        return v
    }
}