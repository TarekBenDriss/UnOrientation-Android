package bendriss.tarek.unorientation.modules.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseActivity
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.modules.guidpdf.PdfReadderFragment
import bendriss.tarek.unorientation.modules.history.HistoryFragment
import bendriss.tarek.unorientation.modules.jobstats.JobStatsFragment
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.modules.login.LoginViewModel
import bendriss.tarek.unorientation.modules.profile.ProfileFragment
import bendriss.tarek.unorientation.modules.quiz.QuizFragment
import bendriss.tarek.unorientation.modules.score.ScoreFragment
import bendriss.tarek.unorientation.util.ActivityUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.ItemClickEvent
import bendriss.tarek.unorientation.util.Logger
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.io.IOException

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
    private  var menu:ImageView? = null
    private var mDisposable: CompositeDisposable? = null
    var id: String? = null
    var id_mission:kotlin.String? = null
    var company:kotlin.String? = null
    var project:kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        ActivityUtils.hideStatusBar(this)
        var type:String? = ""
        type = if(intent.getStringExtra(Constants.TYPE)==null)
            "kkk"
        else
            intent.getStringExtra(Constants.TYPE)

        //supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, LoginFragment()).addToBackStack(null).commit()

        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences?.edit()

        Logger.e("TYPE",type)
        if(type == Constants.SIGNIN || type =="kkk")
            supportFragmentManager.beginTransaction().replace(R.id.fragment, DashboardFragment()).commit()
        else
        {
            editor!!.putString("firstTime", "true")
            editor!!.apply()
            supportFragmentManager.beginTransaction().replace(R.id.fragment, QuizFragment()).commit()
        }


        EventBus.getDefault().register(this)


        init()

    }

    private fun init() {

        nvDrawer = findViewById(R.id.nvView)
        mDrawer = findViewById(R.id.drawer_layout)
        setupDrawerContent(nvDrawer)
        name = findViewById(R.id.name)
        role = findViewById(R.id.role)
        role = findViewById(R.id.role)
        email = findViewById(R.id.email)
        menu = findViewById(R.id.img)

// Inflate the header view at runtime
        // Inflate the header view at runtime
        var headerLayout: View? = nvDrawer?.inflateHeaderView(R.layout.nav_header)
// We can now look up items within the header if needed
        // We can now look up items within the header if needed
        name = headerLayout?.findViewById(R.id.name)
        role = headerLayout?.findViewById(R.id.role)
        email = headerLayout?.findViewById(R.id.email)

        var x: NavigationView? = nvDrawer as NavigationView?
        menu?.setOnClickListener{
            Logger.e("DRAWER","OPEN")
            x?.let { it1 -> mDrawer?.openDrawer(it1) }
        }

        val mapper = ObjectMapper()
        var jsonUser:String = preferences?.getString("UserObject","")!!
        Log.e("USEROBJECT",jsonUser)
        try {
            // JSON string to Java object
            val user: UserProfile = mapper.readValue(jsonUser, UserProfile::class.java)
            //Log.e("USEROBJECT","xxxxx"+user.toString())
            name?.text = user.nom
            email?.text = user.email


        } catch (e: IOException) {
            e.printStackTrace()
        }





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
            R.id.monprofil -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, ProfileFragment())?.addToBackStack(null)?.commit()
            }
            R.id.score -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, ScoreFragment())?.addToBackStack(null)?.commit()
            }
            R.id.historique -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, HistoryFragment())?.addToBackStack(null)?.commit()
            }
            R.id.guide -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, PdfReadderFragment())?.addToBackStack(null)?.commit()
            }
            R.id.jobstats -> {
                var preferences = PreferenceManager.getDefaultSharedPreferences(this)
                var s:String = preferences?.getString("Keyword","")!!
                if(s == "")
                {
                    val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)
                    var v = window.decorView.findViewById(android.R.id.content) as ConstraintLayout
                    v.startAnimation(shake)

                    val snackbar: Snackbar = Snackbar
                            .make(window.decorView.findViewById(android.R.id.content)!!, "Veuillez remplir le quiz au moins une fois", Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(this?.resources?.getColor(R.color.pokmy)!!)

                    val mView: View = snackbar.view
                    val mTextView = mView.findViewById<View>(R.id.snackbar_text) as TextView

                    val face: Typeface = Typeface.createFromAsset(this.assets,
                            "fonts/comfortaalight.ttf")
                    mTextView.typeface = face
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER else mTextView.gravity = Gravity.CENTER_HORIZONTAL

                    snackbar.show()
                }
                else
                    supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, JobStatsFragment())?.addToBackStack(null)?.commit()
            }
            R.id.quiz -> {
                supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, QuizFragment())?.addToBackStack(null)?.commit()
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe
    fun onEvent(event: ItemClickEvent)
    {
        if(event.date=="DRAWER")
        {
            Logger.e("DRAWER",event.date)
            mDrawer?.openDrawer(Gravity.LEFT)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


}
