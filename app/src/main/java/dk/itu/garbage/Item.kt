package dk.itu.garbage

class Item(what: String, where: String) {
    var what: String
    var where: String

    init {
        this.what= what
        this.where=where
    }

    override fun toString(): String {
        return oneLine()
    }

    private fun oneLine(): String {
        return "$what in: $where"
    }
}