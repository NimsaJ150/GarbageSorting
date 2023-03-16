package dk.itu.garbage

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
class FragmentModify : Fragment() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // define items
        itemsDB = ViewModelProvider(requireActivity())[ItemsDB::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_modify, container, false)

        // define items
        itemsDB = ViewModelProvider(requireActivity())[ItemsDB::class.java]

        // Text Fields
        val newWhat = v.findViewById<EditText>(R.id.what_text)
        val newWhere = v.findViewById<EditText>(R.id.where_text)

        // define ADD ITEM onClick activity
        val addItem = v.findViewById<Button>(R.id.add_button)
        addItem.setOnClickListener {
            // get text from input fields
            val whatS: String = newWhat.text.toString().trim { it <= ' ' }
            val whereS: String = newWhere.text.toString().trim { it <= ' ' }

            // check whether input was provided
            if (whatS.isNotEmpty() && whereS.isNotEmpty()) {
                // add item to the itemsDB
                itemsDB.addItem(whatS, whereS)
                newWhat.setText("")
                newWhere.setText("")
                newWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                newWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            } else Toast.makeText(requireActivity(), R.string.empty_toast, Toast.LENGTH_LONG)
                .show()
        }

        // define DELETE ITEM onClick activity
        val deleteItem = v.findViewById<Button>(R.id.delete_button)
        deleteItem.setOnClickListener {
            // get text from input fields
            val whatS: String = newWhat.text.toString().trim { it <= ' ' }
            val whereS: String = newWhere.text.toString().trim { it <= ' ' }

            // check whether input was provided
            if (whatS.isNotEmpty() && whereS.isEmpty()) {
                // add item to the itemsDB
                itemsDB.removeItem(whatS)
                newWhat.setText("")
                newWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                newWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            } else Toast.makeText(requireActivity(), R.string.empty_toast, Toast.LENGTH_LONG)
                .show()
        }

        return v
    }
}