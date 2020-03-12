package bendriss.tarek.unorientation.modules.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Logger;

import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.databinding.ActivityLoginBinding;
import bendriss.tarek.unorientation.modules.base.BaseActivity;
import bendriss.tarek.unorientation.modules.util.ActivityUtils;
import bendriss.tarek.unorientation.modules.util.AlertDialogUtils;
import bendriss.tarek.unorientation.modules.util.StringUtils;

public class LoginActivity extends BaseActivity {


    private Button login;
    private EditText username,password,codeAgence;
    private ActivityLoginBinding mBinding;


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initDataBinding();
        ActivityUtils.hideStatusBar(this);

    }


    /**
     this function allows to initiate variables and view...
     */
    private void initDataBinding()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        if(mBinding==null)
            Log.i("Binding Tag","null");
        else
            Log.i("Binding Tag","not null");
        login = mBinding.loginBtn;
        username = mBinding.loginEt;
        password = mBinding.passwordEt;
        codeAgence = mBinding.codeAgence;

        login.setOnClickListener(view1 -> {
            String u,p;
            u= username.getText().toString();
            p= password.getText().toString();
            if(isValid())
            {
                editor.putString("CODEAGENCE",codeAgence.getText().toString());
                editor.apply();


                //doLogin(u, p);
            }
            else
                AlertDialogUtils.show(mContext, mContext.getResources().getString(R.string.required_fields));
        });
    }

    /**
     this function verify if all required fields are not empty
     */
    private boolean isValid() {
        return StringUtils.isNotEmpty(username.getText().toString())
                && StringUtils.isNotEmpty(password.getText().toString());
    }
}
