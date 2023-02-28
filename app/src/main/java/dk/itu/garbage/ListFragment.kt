package dk.itu.garbage

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(), Observer {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

    override fun update(observable: Observable?, data: Any?) {
        listThings.text = "Shopping List" + itemsDB.listItems()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // define items
        itemsDB = ItemsDB.get()
        itemsDB.addObserver(this);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        listThings = v.findViewById<TextView>(R.id.listItems)
        listThings.movementMethod = ScrollingMovementMethod()
        listThings.text = "Shopping List" + itemsDB.listItems()

        return v
    }

}