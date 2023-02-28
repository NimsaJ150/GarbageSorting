package dk.itu.garbage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {

    // Model: Database of items
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ItemsDB.initialize(this@MainActivity)
        itemsDB = ItemsDB.get()
        setUpFragments()

    }

    private fun setUpFragments() {

        val fm: FragmentManager = supportFragmentManager
        var fragmentUI = fm.findFragmentById(R.id.container_ui)
        var fragmentList = fm.findFragmentById(R.id.container_list)
        if (fragmentUI == null && fragmentList == null) {
            fragmentUI = WhereFragment()
            fragmentList = ListFragment()
            fm.beginTransaction()
                .add(R.id.container_ui, fragmentUI)
                .commit()
            fm.beginTransaction()
                .add(R.id.container_list, fragmentList)
                .commit()
        }
    }
}