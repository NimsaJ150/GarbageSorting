package dk.itu.garbage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class ActivityModify : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        ItemsDB.initialize(this@ActivityModify)

        setUpFragments()
    }

    private fun setUpFragments() {

        val fm: FragmentManager = supportFragmentManager
        var fragmentModify = fm.findFragmentById(R.id.container_modify)
        var fragmentList = fm.findFragmentById(R.id.container_list)
        if (fragmentModify == null && fragmentList == null) {
            fragmentModify = FragmentModify()
            fragmentList = FragmentList()
            fm.beginTransaction()
                .add(R.id.container_modify, fragmentModify)
                .commit()
            fm.beginTransaction()
                .add(R.id.container_list, fragmentList)
                .commit()
        }
    }
}