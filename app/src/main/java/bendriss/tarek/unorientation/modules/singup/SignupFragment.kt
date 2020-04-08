package bendriss.tarek.unorientation.modules.singup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseFragment
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse
import bendriss.tarek.unorientation.databinding.FragmentLoginBinding
import bendriss.tarek.unorientation.databinding.FragmentSignupBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.login.LoginViewModel
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import bendriss.tarek.unorientation.util.StringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SignupFragment : BaseFragment() {


    private var mDisposable: CompositeDisposable? = null
    private var login: Button? = null
    private var username: EditText? = null
    private  var password: EditText? = null
    private  var codeAgence: EditText? = null
    private var mBinding: FragmentSignupBinding? = null
    private var mSignInViewModel: LoginViewModel? = null


    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View =  inflater.inflate(R.layout.fragment_signup, container, false)

        initDataBinding()


        return view
    }


    private fun initDataBinding() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences?.edit()
        mBinding = DataBindingUtil.setContentView(mActivity, R.layout.fragment_signup)
        login = mBinding?.loginBtn
        username = mBinding?.loginEt
        password = mBinding?.passwordEt
        login?.setOnClickListener(View.OnClickListener { view1: View? ->
            val userProfile = UserProfile()
            val u: String
            val p: String
            u = username?.getText().toString()
            p = password!!.text.toString()
            if (isValid()) { //App.initAppComponent2("https://"+".pokmy.net/");
                App.getDataComponent().inject(this)
                doSignup(userProfile)
            } else AlertDialogUtils.show(mContext, mContext.resources.getString(R.string.required_fields))
        })
    }

    private fun isValid(): Boolean {
        return (StringUtils.isNotEmpty(username!!.text.toString())
                && StringUtils.isNotEmpty(password!!.text.toString()))
    }

    fun doSignup(userProfile: UserProfile?) {
        userProfile?.username="van"
        userProfile?.password="0000"

        mDisposable = CompositeDisposable()
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        mSignInViewModel?.signup(userProfile)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { disposable: Disposable? -> App.showLoader(mContext) }
                ?.doOnSuccess { disposable: SignupResponse? -> App.hideLoader() }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<SignupResponse?>() {
                    override fun onSuccess(response: SignupResponse) {
                        Logger.e("SignupResponse", response.toString())

                        val intent = Intent(mActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        mActivity.finish()
                    }

                    override fun onError(error: Throwable) {
                        Logger.e("SigninResponse error", error)
                        App.hideLoader()
                        AlertDialogUtils.showString(mActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }



}
