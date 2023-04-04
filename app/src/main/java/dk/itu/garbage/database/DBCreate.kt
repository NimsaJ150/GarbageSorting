package dk.itu.garbage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dk.itu.garbage.database.ItemsDbSchema.ItemTable

class DBCreate(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    companion object {
        private const val VERSION = 1
        const val DATABASE_NAME = "garbage.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "create table " + ItemTable.NAME + "(" +
                    ItemTable.Cols.WHAT + ", " + ItemTable.Cols.WHERE + ")"
        )

        //For testing purposes add some items to the database
        addItem(db, "coffee", "Irma")
        addItem(db, "carrots", "Netto")
        addItem(db, "milk", "Netto")
        addItem(db, "bread", "bakery")
        addItem(db, "butter", "Irma")
    }

    private fun addItem(db: SQLiteDatabase, what: String, where: String) {
        db.execSQL("INSERT INTO Items (what, whereC) VALUES ('$what', '$where')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}