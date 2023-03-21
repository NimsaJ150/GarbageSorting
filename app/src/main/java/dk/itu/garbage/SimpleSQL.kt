package dk.itu.garbage
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase


class SimpleSQL(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE 'Items' (" +
                    "'what' TEXT," +
                    "'whereC' TEXT" +
                    ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val VERSION = 1
        const val DATABASE_NAME = "garbageBase.db"
    }

}