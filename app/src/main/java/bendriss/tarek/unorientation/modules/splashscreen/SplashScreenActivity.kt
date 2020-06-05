package bendriss.tarek.unorientation.modules.splashscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.util.AnimationUtils
import bendriss.tarek.unorientation.util.Constants

class SplashScreenActivity : AppCompatActivity() {
    private var preferences: SharedPreferences? = null
    private val SPLASH_DISPLAY_LENGTH = 4000
    private var handler: Handler? = null
    private var run: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splach_screen)

        preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        var logo:ImageView = findViewById(R.id.logoImg)
        AnimationUtils.setAlphaAnimation(logo, 0.0f, 1.0f, 3400, 0);




        var optionsCompat: ActivityOptionsCompat? = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SplashScreenActivity, logo, "logoTransition")

        run = Runnable { val mainIntent2 = Intent(this, LandingPageActivity::class.java)
            val dhasboard2Intent = Intent(this, DashboardActivity::class.java)
            val pref = preferences
            if (pref?.getBoolean(Constants.IS_LOGGED_IN, false) == false)
                //this.startActivity(mainIntent2,optionsCompat?.toBundle())
                this.startActivity(mainIntent2)
            else  //SplashScreenActivity.this.startActivity(dhasboardIntent);
            {
                //this.startActivity(dhasboard2Intent,optionsCompat?.toBundle())
                this.startActivity(dhasboard2Intent)
            }
            this.finish()
        }

        handler = Handler()
        handler?.postDelayed(
            run
        , SPLASH_DISPLAY_LENGTH.toLong())
    }



    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(run);
    }

    override fun onStop() {
        super.onStop()
        handler?.removeCallbacks(run);
    }

    override fun onResume() {
        super.onResume()
        handler?.postDelayed(run, SPLASH_DISPLAY_LENGTH.toLong())
    }

    override fun onRestart() {
        super.onRestart()
        handler?.postDelayed(run, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
