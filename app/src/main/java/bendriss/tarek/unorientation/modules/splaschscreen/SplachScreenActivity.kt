package bendriss.tarek.unorientation.modules.splaschscreen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import bendriss.tarek.unorientation.modules.main.MainActivity
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.modules.landingpage.KotlinLandingPageActivity
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.util.Constants

class SplachScreenActivity : AppCompatActivity() {
    private var preferences: SharedPreferences? = null
    private val SPLASH_DISPLAY_LENGTH = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        preferences = PreferenceManager.getDefaultSharedPreferences(baseContext)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        Handler().postDelayed({
            //val mainIntent2 = Intent(this, LandingPageActivity::class.java)
            val mainIntent2 = Intent(this, KotlinLandingPageActivity::class.java)
            val dhasboard2Intent = Intent(this, MainActivity::class.java)
            val pref = preferences
            if (pref?.getBoolean(Constants.IS_LOGGED_IN, false) == false) this.startActivity(mainIntent2) else  //SplashScreenActivity.this.startActivity(dhasboardIntent);
            {
                this.startActivity(dhasboard2Intent)
            }
            this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
