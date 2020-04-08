package bendriss.tarek.unorientation.modules.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseActivity
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.modules.login.LoginViewModel
import bendriss.tarek.unorientation.util.ActivityUtils
import bendriss.tarek.unorientation.util.Constants
import com.google.android.material.navigation.NavigationView
import io.reactivex.disposables.CompositeDisposable

class DashboardActivity : BaseActivity() {

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var mDrawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var nvDrawer: NavigationView? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var mSignInViewModel: LoginViewModel? = null
    private var name: TextView? = null
    private  var role:TextView? = null
    private  var email:TextView? = null
    private var mDisposable: CompositeDisposable? = null
    var id: String? = null
    var id_mission:kotlin.String? = null
    var company:kotlin.String? = null
    var project:kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        ActivityUtils.hideStatusBar(this)
        supportFragmentManager.beginTransaction().replace(R.id.fragment, DashboardFragment()).commit()
        init()

    }

    private fun init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences?.edit()
        nvDrawer = findViewById(R.id.nvView)
        mDrawer = findViewById(R.id.drawer_layout)
        setupDrawerContent(nvDrawer)
        name = findViewById(R.id.name)
        role = findViewById(R.id.role)
        email = findViewById(R.id.email)

// Inflate the header view at runtime
        // Inflate the header view at runtime
        var headerLayout: View? = nvDrawer?.inflateHeaderView(R.layout.nav_header)
// We can now look up items within the header if needed
        // We can now look up items within the header if needed
        name = headerLayout?.findViewById<TextView>(R.id.name)
        role = headerLayout?.findViewById<TextView>(R.id.role)
        email = headerLayout?.findViewById<TextView>(R.id.email)


    }

    private fun setupDrawerContent(navigationView: NavigationView?) {
        navigationView?.setNavigationItemSelectedListener(
                object : NavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        selectDrawerItem(item)
                        return true
                    }
                }
        )
    }


    fun selectDrawerItem(menuItem: MenuItem) {
        val fragment: Fragment? = null
        var fragmentClass: Class<*>
        when (menuItem.itemId) {
            R.id.nav_first_fragment -> {
                editor!!.putBoolean(Constants.IS_LOGGED_IN, false)
                //editor.putString(Constants.TOKEN,response.getToken());
                editor!!.apply()
                finish()
                val intent = Intent(mActivity, LandingPageActivity::class.java)
                startActivity(intent)
            }
            else -> {
            }
        }
        try { //fragment = (Fragment) fragmentClass.newInstance();
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        //fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        menuItem.isChecked = true
        //setTitle(menuItem.getTitle());
        mDrawer?.closeDrawers()
    }

}
