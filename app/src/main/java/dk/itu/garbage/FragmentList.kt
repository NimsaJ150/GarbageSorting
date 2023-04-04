package dk.itu.garbage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsViewModel
    private lateinit var itemList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Shared data
        itemsDB = ViewModelProvider(requireActivity())[ItemsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        // get itemList view
        itemList = v.findViewById<RecyclerView>(R.id.listItems)
        itemList.layoutManager = LinearLayoutManager(context)

        // create and set the appropriate adaptor
        val mAdapter = ItemAdapter()
        itemList.adapter = mAdapter

        // observe changes in the itemsDB and notify adaptor
        itemsDB.getValue().observe(viewLifecycleOwner) {
            mAdapter.notifyDataSetChanged()
        }

        return v
    }

    /**
     * Called every time one row appears on the screen
     */
    private inner class ItemHolder(itemView: View, _itemsDB: ItemsViewModel) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        // define the textviews of one row in the recyclerview
        private val mNoTextView: TextView
        private val mWhatTextView: TextView
        private val mWhereTextView: TextView

        init {
            // get the textviews of one row in the recyclerview
            mNoTextView = itemView.findViewById(R.id.item_no)
            mWhatTextView = itemView.findViewById(R.id.item_what)
            mWhereTextView = itemView.findViewById(R.id.item_where)
            itemView.setOnClickListener(this)
        }

        /**
         * Delete item when user clicks on the particular row
         */
        override fun onClick(v: View) {
            val what = (v.findViewById(R.id.item_what)// The what TextView
                    as TextView).text // Read the contents of the TextView
                    as String // Cast to String type
            itemsDB.removeItem(what)
        }

        fun bind(item: Item, position: Int) {
            // set the textviews of one row in the recyclerview
            mNoTextView.text = position.toString()
            mWhatTextView.text = item.what
            mWhereTextView.text = item.where
        }
    }

    /**
     * Link between data and layout
     */
    private inner class ItemAdapter : RecyclerView.Adapter<ItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            val layoutInflater = LayoutInflater.from(context)
            val v = layoutInflater.inflate(R.layout.one_row, parent, false)
            return ItemHolder(v, itemsDB)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val item = itemsDB.getList()[position]!!
            holder.bind(item, position)
        }

        override fun getItemCount(): Int {
            return itemsDB.size()
        }
    }

}