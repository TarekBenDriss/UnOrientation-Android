package bendriss.tarek.unorientation.modules.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseFragment
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse
import bendriss.tarek.unorientation.databinding.FragmentLoginBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.dashboard.DashboardFragment
import bendriss.tarek.unorientation.modules.landingpage.FragmentDelegate
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.modules.singup.SignupFragment
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import bendriss.tarek.unorientation.util.StringUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_login.*
import java.io.IOException


class LoginFragment : BaseFragment() {

    var delegate: FragmentDelegate? = null

    private var mDisposable: CompositeDisposable? = null
    private var login: Button? = null
    private var registerTxt: TextView? = null
    private var username: EditText? = null
    private var password:EditText? = null
    private var codeAgence:EditText? = null
    private var logoImg:ImageView? = null
    private var mBinding: FragmentLoginBinding? = null
    private var mSignInViewModel: LoginViewModel? = null


    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        initDataBinding()
        return mBinding?.root
    }

    private fun initDataBinding() {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = preferences?.edit()
        login = mBinding?.loginBtn
        username = mBinding?.loginEt
        password = mBinding?.passwordEt
        codeAgence = mBinding?.codeAgence
        registerTxt = mBinding?.registerTxt
        logoImg = mBinding?.logo
        login?.setOnClickListener { view1: View? ->
            val u: String
            val p: String
            u = username?.getText().toString()
            p = password!!.text.toString()
            if (isValid()) { //App.initAppComponent2("https://"+".pokmy.net/");
                App.getDataComponent().inject(this)
                doLogin(u, p)
            } else
            snackAndShakeAllEmpty(mContext,mBinding?.mainLayout as View)

        }
        registerTxt?.setOnClickListener {

            //mActivity.supportFragmentManager.beginTransaction().addSharedElement(logoImg as ImageView,logoImg?.transitionName!!).replace(R.id.fragmentLanding,SignupFragment()).addToBackStack(null).commit()

            sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transition)
            exitTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

            val fragment: Fragment = SignupFragment()
            fragment.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transition)
            fragment.enterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

            mActivity.supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding,fragment).addToBackStack(null)
                    .addSharedElement(logoImg as ImageView, logoImg!!.transitionName)
                    .addSharedElement(loginEt as EditText, loginEt.transitionName)
                    .addSharedElement(passwordEt as EditText, passwordEt.transitionName)
                    .addSharedElement(login as Button, login!!.transitionName)
                    .addSharedElement(registerTxt as TextView, registerTxt!!.transitionName)
                    .commit()

        }
    }

    private fun isValid(): Boolean {
        return (StringUtils.isNotEmpty(username!!.text.toString())
                && StringUtils.isNotEmpty(password!!.text.toString()))
    }

    private fun snackAndShakeAllEmpty(context:Context, mainView:View)
    {
        if (!StringUtils.isNotEmpty(username!!.text.toString())) {
            onlyShake(context,username as View)
        }
        if (!StringUtils.isNotEmpty(password!!.text.toString())) {
            onlyShake(context,password as View)
        }
        onlySnack(mContext,mBinding?.mainLayout as View,mContext.resources.getString(R.string.required_fields))
    }


    private fun onlyShake(context:Context, viewToShake:View)
    {
        val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        viewToShake?.startAnimation(shake)
    }

    private fun onlySnack(context:Context, mainView:View, text:String)
    {
        val snackbar: Snackbar = Snackbar
                .make(mainView, text, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(context.resources.getColor(R.color.pokmy))

        val mView: View = snackbar.view
        val mTextView = mView.findViewById<View>(R.id.snackbar_text) as TextView

        val face: Typeface = Typeface.createFromAsset(context.assets,
                "fonts/comfortaalight.ttf")
        mTextView.typeface = face
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER else mTextView.gravity = Gravity.CENTER_HORIZONTAL

        snackbar.show()
    }


    fun doLogin(u: String?, p: String?) {
        mDisposable = CompositeDisposable()
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val lp = LoginParams(u, p)
        mSignInViewModel?.signin(lp)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { disposable: Disposable? -> App.showLoader(context) }
                ?.doOnSuccess { disposable: LoginResponse? -> App.hideLoader() }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<LoginResponse?>() {
                    override fun onSuccess(response: LoginResponse) {
                        Logger.e("SigninResponse", response.toString())
                        editor!!.putInt(Constants.USER_ID, response.user.id)
                        editor!!.putString(Constants.TOKEN, response.user.token)
                        editor!!.putBoolean(Constants.IS_LOGGED_IN, true)

                        val mapper = ObjectMapper()
                        try {
                            // Java objects to JSON string - compact-print
                            val jsonString = mapper.writeValueAsString(response.user)
                            editor!!.putString("UserObject", jsonString)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        editor!!.apply()
                        App.token = response.user.token
                        val intent = Intent(activity as LandingPageActivity, DashboardActivity::class.java)
                        intent.putExtra(Constants.TYPE,Constants.SIGNIN)



                        /*
                        sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transition)
                        exitTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

                        val fragment: Fragment = DashboardFragment()
                        fragment.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transition)
                        fragment.enterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

                        mActivity.supportFragmentManager.beginTransaction().replace(R.id.fragmentLanding,fragment).addToBackStack(null)
                                .addSharedElement(logoImg as ImageView, logoImg!!.transitionName)
                                .addSharedElement(loginEt as EditText, loginEt.transitionName)
                                .addSharedElement(passwordEt as EditText, passwordEt.transitionName)
                                .addSharedElement(login as Button, login!!.transitionName)
                                .addSharedElement(registerTxt as TextView, registerTxt!!.transitionName)
                                .commit()
                        */

                        startActivity(intent)
                        activity?.finish()
                    }

                    override fun onError(error: Throwable) {
                        Logger.e("SigninResponse error", error)
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as LandingPageActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
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
