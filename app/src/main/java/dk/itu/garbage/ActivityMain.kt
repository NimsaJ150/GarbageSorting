package dk.itu.garbage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ItemsDB.initialize(this@ActivityMain)

        setUpFragments()
    }

    private fun setUpFragments() {

        val fm: FragmentManager = supportFragmentManager
        var fragmentUI = fm.findFragmentById(R.id.container_ui)
        var fragmentList = fm.findFragmentById(R.id.container_list)
        if (fragmentUI == null && fragmentList == null) {
            fragmentUI = FragmentWhere()
            fragmentList = FragmentList()
            fm.beginTransaction()
                .add(R.id.container_ui, fragmentUI)
                .commit()
            fm.beginTransaction()
                .add(R.id.container_list, fragmentList)
                .commit()
        }
    }
}