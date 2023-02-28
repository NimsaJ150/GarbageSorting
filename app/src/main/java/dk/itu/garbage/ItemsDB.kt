package dk.itu.garbage

import android.content.Context
import java.util.Observable
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class ItemsDB private constructor(context: Context): Observable() {
    private val itemsMap = HashMap<String, String?>()

    init {
        fillItemsDB(context)
    }

    fun listItems(): String {
        val r = StringBuilder()
        for ((key, value) in itemsMap) r.append("\n ").append(key).append(" in: ").append(value)
        return r.toString()
    }

    private fun fillItemsDB(context: Context) {
        try {
            val reader = BufferedReader(
                InputStreamReader(context.assets.open("garbage.txt"))
            )
            var line = reader.readLine()
            while (line != null) {
                val gItem: List<String> = line.split(",")
                itemsMap[gItem[0]] = gItem[1]
                line = reader.readLine()
            }
            this.setChanged()
            this.notifyObservers()
        } catch (ignored: IOException) {
        }
    }

    fun addItem(what: String, where: String?) {
        itemsMap[what] = where
        this.setChanged()
        this.notifyObservers()

    }

    fun removeItem(what: String) {
        if (itemsMap[what] != null) itemsMap.remove(what)
        this.setChanged()
        this.notifyObservers()
    }

    fun getItemWhere(what: String?): String {
        val where: String? = itemsMap[what]
        // if no match found, then return not found
        return where ?: "not found"
    }

    fun size(): Int {
        return itemsMap.size
    }

    companion object {
        private var sItemsDB: ItemsDB? = null

        fun initialize(context: Context) {
            if (sItemsDB == null) {
                sItemsDB = ItemsDB(context)
            }
        }

        fun get(): ItemsDB {
            return sItemsDB ?: throw IllegalStateException("ItemsDB must be initializes")
        }
    }
}