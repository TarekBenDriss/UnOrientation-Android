package bendriss.tarek.unorientation.modules.landingpage;

import androidx.appcompat.app.AppCompatActivity;
import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.base.BaseActivity;
import bendriss.tarek.unorientation.modules.login.LoginFragment;

import android.os.Bundle;

public class LandingPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLanding,new LoginFragment()).commit();
    }
}
