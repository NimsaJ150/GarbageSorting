package dk.itu.garbage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {
    private var items: MutableLiveData<ItemsDB> = MutableLiveData()

    init {
        items.value = ItemsDB()
    }

    fun initialize(context: Context) {
        items.value!!.initialize(context)
    }

    fun getList(): List<Item?> {
        return items.value!!.getValues()
    }

    fun getValue(): MutableLiveData<ItemsDB> {
        return items
    }

    fun size(): Int {
        return items.value!!.size()
    }

    /**
     * Adds a new item and its place to the database, if the item already exists, updates the entry
     */
    fun addItem(what: String?, where: String?) {
        val temp = items.value

        if (findItem(what) == null) {
            temp!!.addItem(what!!, where!!)
        } else {
            temp!!.updateItem(what!!, where!!)
        }
        items.value = temp
    }

    fun removeItem(what: String?) {
        val temp = items.value
        temp!!.removeItem(what!!)
        items.value = temp
    }

    fun findItem(what: String?): String? {
        for (item in getList()) {
            if (item!!.what == what) {
                return item.where
            }
        }

        return null
    }
}