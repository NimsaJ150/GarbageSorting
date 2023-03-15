package dk.itu.garbage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

//class ItemsDB private constructor(context: Context) : ViewModel() {
class ItemsDB : ViewModel() {
    val itemsMap: MutableLiveData<HashMap<String, String?>> = MutableLiveData()

    init {
        itemsMap.value= HashMap<String, String?>()
        //fillItemsDB(context)
        fillItemsDB()
    }

    fun listItems(): String {
        val r = StringBuilder()
        for ((key, value) in itemsMap.value!!) r.append("\n ").append(key).append(" in: ").append(value)
        return r.toString()
    }

    //private fun fillItemsDB(context: Context) {
    private fun fillItemsDB() {
        addItem("test", "test")
        addItem("test1", "test1")
        try {val x=5
        } catch (ignored: IOException) {
        }
    }

    fun addItem(what: String, where: String?) {
        val temp= itemsMap.value
        temp!![what] = where
        itemsMap.value= temp

    }

    fun removeItem(what: String) {
        val temp= itemsMap.value
        if (temp!![what] != null) temp.remove(what)
        itemsMap.value= temp
    }

    fun getItemWhere(what: String?): String {
        val where: String? = itemsMap.value!![what]
        // if no match found, then return not found
        return where ?: "not found"
    }

    fun size(): Int {
        return itemsMap.value!!.size
    }

    companion object {
        private var sItemsDB: ItemsDB? = null

        fun initialize(context: Context) {
            if (sItemsDB == null) {
                //sItemsDB = ItemsDB(context)
                sItemsDB = ItemsDB()
            }
        }

        fun get(): ItemsDB {
            return sItemsDB ?: throw IllegalStateException("ItemsDB must be initializes")
        }
    }
}