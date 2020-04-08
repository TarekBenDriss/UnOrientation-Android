package bendriss.tarek.unorientation.modules.landingpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseActivity
import bendriss.tarek.unorientation.modules.dashboard.DashboardFragment
import bendriss.tarek.unorientation.modules.login.LoginFragment
import bendriss.tarek.unorientation.modules.singup.SignupFragment
import bendriss.tarek.unorientation.util.ActivityUtils

class KotlinLandingPageActivity : BaseActivity() ,FragmentDelegate{

    var frameLayout: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        ActivityUtils.hideStatusBar(this)
        //supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, LoginFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, SignupFragment()).commit()
    }

    override fun loginFragmentButtonRegister(fragment: LoginFragment) {
        //var frame:FrameLayout = findViewById(R.id.fragmentLanding) as FrameLayout

        //frame.visibility= View.INVISIBLE

        //supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, SignupFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, SignupFragment()).commit()
        //supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, SignupFragment()).addToBackStack("Login").commit()
    }


}



interface FragmentDelegate {
    fun loginFragmentButtonRegister(fragment: LoginFragment)
}