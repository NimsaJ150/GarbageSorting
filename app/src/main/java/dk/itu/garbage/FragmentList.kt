package dk.itu.garbage

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

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
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        listThings = v.findViewById<TextView>(R.id.listItems)
        listThings.movementMethod = ScrollingMovementMethod()

        itemsDB.itemsMap.observe(viewLifecycleOwner) {
            listThings.text = "Sorting List:" + itemsDB.listItems()
        }

        return v
    }

}