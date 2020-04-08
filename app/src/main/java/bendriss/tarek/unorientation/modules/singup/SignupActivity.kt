package bendriss.tarek.unorientation.modules.singup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseActivity
import bendriss.tarek.unorientation.util.ActivityUtils

class SignupActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        ActivityUtils.hideStatusBar(mActivity)
    }
}
