package dk.itu.garbage.database

class ItemsDbSchema {
  object ItemTable {
    const val NAME = "Items"
    object Cols {
      const val WHAT = "what"
      const val WHERE = "whereC" //where is a keyword in SQL
    }
  }
}