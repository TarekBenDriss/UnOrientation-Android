package bendriss.tarek.unorientation.modules.landingpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseActivity
import bendriss.tarek.unorientation.modules.dashboard.DashboardFragment
import bendriss.tarek.unorientation.modules.login.LoginFragment
import bendriss.tarek.unorientation.modules.quiz.QuizFragment
import bendriss.tarek.unorientation.modules.singup.ActionsDelegate
import bendriss.tarek.unorientation.modules.singup.SignupFragment
import bendriss.tarek.unorientation.util.ActivityUtils

class LandingPageActivity : BaseActivity() ,FragmentDelegate,ActionsDelegate{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        ActivityUtils.hideStatusBar(this)
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding, LoginFragment()).addToBackStack(null).commit()
        //loadFragment(LoginFragment())

    }

    override fun loginFragmentButtonRegister(fragment: LoginFragment) {
               loadFragment(SignupFragment())
    }


    private fun loadFragment(fragment: Fragment)
    {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLanding, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun choisirBac(bac: String?) {
    }
}



interface FragmentDelegate {
    fun loginFragmentButtonRegister(fragment: LoginFragment)
}