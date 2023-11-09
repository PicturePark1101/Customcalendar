package ddwucom.moblie.customcalendar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import ddwucom.moblie.customcalendar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    lateinit var homeFragment: HomeFragment
    lateinit var calendarTotalFragment: CalendarTotalFragment
    lateinit var myInfoFragment: MyInfoFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        homeFragment = HomeFragment()
        calendarTotalFragment = CalendarTotalFragment()
        myInfoFragment = MyInfoFragment()

        replaceFragment(homeFragment)
        mainBinding.bottomNavi.setOnItemSelectedListener(
            object : NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    var selectedFragment: Fragment? = null
                    when (item.itemId) {
                        R.id.page_1 -> {
                            replaceFragment(homeFragment)
                        }
                        R.id.page_2 -> {
                            replaceFragment(calendarTotalFragment)
                        }
                        R.id.page_3 -> {
                            replaceFragment(myInfoFragment)
                        }
                        else -> return false
                    }
                    return true
                }
            },
        )
    }

    private fun replaceFragment(fragment: Fragment?) {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.cl_main, fragment)
                .commit()
        }
    }
}
