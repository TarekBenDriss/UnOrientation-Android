package bendriss.tarek.unorientation.modules.login

import android.content.Context
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
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse
import bendriss.tarek.unorientation.databinding.FragmentLoginBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.landingpage.FragmentDelegate
import bendriss.tarek.unorientation.modules.singup.SignupFragment
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import bendriss.tarek.unorientation.util.StringUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class LoginFragment : BaseFragment() {

    var delegate: FragmentDelegate? = null



    private var mDisposable: CompositeDisposable? = null
    private var login: Button? = null
    private var username: EditText? = null
    private  var password:EditText? = null
    private  var codeAgence:EditText? = null
    private var mBinding: FragmentLoginBinding? = null
    private var mSignInViewModel: LoginViewModel? = null


    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_login, container, false)

        initDataBinding()
        return view
    }


    private fun initDataBinding() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences?.edit()
        mBinding = DataBindingUtil.setContentView(mActivity, R.layout.fragment_login)
        login = mBinding?.loginBtn
        username = mBinding?.loginEt
        password = mBinding?.passwordEt
        codeAgence = mBinding?.codeAgence
        login?.setOnClickListener(View.OnClickListener { view1: View? ->
            val u: String
            val p: String
            u = username?.getText().toString()
            p = password!!.text.toString()
            if (isValid()) { //App.initAppComponent2("https://"+".pokmy.net/");
                App.getDataComponent().inject(this)
                doLogin(u, p)
            } else AlertDialogUtils.show(mContext, mContext.resources.getString(R.string.required_fields))
        })
        mBinding?.registerTxt?.setOnClickListener {
            delegate?.loginFragmentButtonRegister(this)
            //mActivity.supportFragmentManager.beginTransaction().replace(R.id.fragment, SignupFragment()).addToBackStack("Login").commit()
            childFragmentManager.beginTransaction().replace(R.id.fragmentLanding, SignupFragment()).addToBackStack("Login").commit()
            //fragmentManager?.beginTransaction()?.replace(R.id.fragment,SignupFragment())?.addToBackStack("Login")?.commit()
            //val intent = Intent(mActivity, SignupActivity::class.java)
            //startActivity(intent)
        }
    }

    private fun isValid(): Boolean {
        return (StringUtils.isNotEmpty(username!!.text.toString())
                && StringUtils.isNotEmpty(password!!.text.toString()))
    }

    fun doLogin(u: String?, p: String?) {
        mDisposable = CompositeDisposable()
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val lp = LoginParams(u, p)
        mSignInViewModel?.signin(lp)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { disposable: Disposable? -> App.showLoader(mContext) }
                ?.doOnSuccess { disposable: LoginResponse? -> App.hideLoader() }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<LoginResponse?>() {
                    override fun onSuccess(response: LoginResponse) {
                        Logger.e("SigninResponse", response.toString())
                        editor!!.putString(Constants.TOKEN, response.token)
                        editor!!.apply()
                        App.token = response.token
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



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentDelegate) {
            delegate = context
        }
    }

}
