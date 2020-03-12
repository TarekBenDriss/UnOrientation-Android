package bendriss.tarek.unorientation.modules.login;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import bendriss.tarek.unorientation.App;
import bendriss.tarek.unorientation.R;
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams;
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse;
import bendriss.tarek.unorientation.databinding.ActivityLoginBinding;
import bendriss.tarek.unorientation.base.BaseActivity;
import bendriss.tarek.unorientation.util.ActivityUtils;
import bendriss.tarek.unorientation.util.AlertDialogUtils;
import bendriss.tarek.unorientation.util.Constants;
import bendriss.tarek.unorientation.util.Logger;
import bendriss.tarek.unorientation.util.StringUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    private CompositeDisposable mDisposable;
    private Button login;
    private EditText username,password,codeAgence;
    private ActivityLoginBinding mBinding;
    private LoginViewModel mSignInViewModel;


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
                App.initAppComponent2("https://"+".pokmy.net/");

                App.getDataComponent().inject(this);

                doLogin(u, p);
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


    /**
     This function allows to log in and returns if ok or not
     */
    void doLogin(String u,String p)
    {
        mDisposable = new CompositeDisposable();
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        LoginParams lp = new LoginParams(u,p);
        mDisposable.add(mSignInViewModel.signinPok(lp)
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(disposable -> App.showLoader(mContext))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        Logger.e("SigninResponse", response.toString());

                        editor.putString(Constants.TOKEN,response.getToken());
                        editor.apply();

                        App.token = response.getToken();

                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("SigninResponse error", error);
                        App.hideLoader();
                        AlertDialogUtils.showString(mActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null);

                    }
                }));
    }

}
