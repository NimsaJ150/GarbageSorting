package dk.itu.garbage

class Item (what: String, where: String) {
    val mWhat: String = what
    val mWhere: String = where

    private fun oneLine(pre: String, post: String): String {
        return pre + mWhat + post + mWhere
    }

    override fun toString(): String {
        return oneLine("", " in: ")
    }
}