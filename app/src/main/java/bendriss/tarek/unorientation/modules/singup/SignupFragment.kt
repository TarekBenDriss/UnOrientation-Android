package bendriss.tarek.unorientation.modules.singup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseFragment
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.data.source.remote.response.SignupResponse
import bendriss.tarek.unorientation.databinding.FragmentSignupBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.login.LoginViewModel
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import bendriss.tarek.unorientation.util.StringUtils
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class SignupFragment : BaseFragment() {


    private var mDisposable: CompositeDisposable? = null
    private var signupBtn: Button? = null
    private var username: EditText? = null
    private  var password: EditText? = null
    private  var nom: EditText? = null
    private  var bac: EditText? = null
    private  var loginTxt: TextView? = null
    private  var moyenne: EditText? = null
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        initDataBinding()

        return mBinding?.root
    }


    private fun initDataBinding() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext)
        editor = preferences?.edit()

        signupBtn = mBinding?.signupBtn
        username = mBinding?.loginEt
        password = mBinding?.passwordEt
        nom = mBinding?.nomEt
        bac = mBinding?.bacEt
        loginTxt = mBinding?.loginTxt
        moyenne = mBinding?.bacMoyEt

        mBinding?.logo?.setOnClickListener{
            val intent = Intent(mActivity, DashboardActivity::class.java)
            intent.putExtra(Constants.TYPE,Constants.SIGNUP)
            startActivity(intent)
            mActivity.finish()
        }

        signupBtn?.setOnClickListener {
            if (isValid()) {
                if(StringUtils.isLength(username?.text.toString(),3)) {
                    if(StringUtils.isLength(password?.text.toString(),4)) {
                        if (isMoyValid()) {
                            val userProfile = UserProfile()
                            userProfile.username = username?.text.toString()
                            userProfile.password = password?.text.toString()
                            userProfile.nom = nom?.text.toString()
                            userProfile.bac = bac?.text.toString()
                            userProfile.moyBac = moyenne?.text.toString().toFloat()
                            App.getDataComponent().inject(this)
                            doSignup(userProfile)
                        } else
                            snackAndShake(mContext, mBinding?.mainLayout as View, mContext.resources.getString(R.string.moy_valid), moyenne as View)
                    } else
                        snackAndShake(mContext, mBinding?.mainLayout as View, mContext.resources.getString(R.string.password_valid), password as View)
                }else
                    snackAndShake(mContext, mBinding?.mainLayout as View, mContext.resources.getString(R.string.username_valid), username as View)
            } else
                snackAndShakeAllEmpty(mContext,mBinding?.mainLayout as View)
        }

        loginTxt?.setOnClickListener {
            mActivity.onBackPressed()
        }
    }

    private fun isValid(): Boolean {
        return (StringUtils.isNotEmpty(username!!.text.toString())
                && StringUtils.isNotEmpty(password!!.text.toString())
                && StringUtils.isNotEmpty(nom!!.text.toString())
                && StringUtils.isNotEmpty(bac!!.text.toString())
                && StringUtils.isNotEmpty(moyenne!!.text.toString())
                )
    }

    private fun isMoyValid(): Boolean {
        return (moyenne!!.text.toString().toFloat()<20 && moyenne!!.text.toString().toFloat()>0)
    }

    private fun snackAndShakeAllEmpty(context:Context, mainView:View)
    {
        if (!StringUtils.isNotEmpty(username!!.text.toString())) {
            onlyShake(context,username as View)
        }
        if (!StringUtils.isNotEmpty(password!!.text.toString())) {
            onlyShake(context,password as View)
        }
        if (!StringUtils.isNotEmpty(nom!!.text.toString())) {
            onlyShake(context,nom as View)
        }
        if (!StringUtils.isNotEmpty(bac!!.text.toString())) {
            onlyShake(context,bac as View)
        }
        if (!StringUtils.isNotEmpty(moyenne!!.text.toString())) {
            onlyShake(context,moyenne as View)
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

    private fun snackAndShake(context:Context, mainView:View, text:String, viewToShake:View)
    {
        val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)

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
        viewToShake?.startAnimation(shake)
    }

    fun doSignup(userProfile: UserProfile?) {

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

                        editor!!.putBoolean(Constants.IS_LOGGED_IN, true)
                        editor!!.apply()

                        val intent = Intent(mActivity, DashboardActivity::class.java)
                        intent.putExtra(Constants.TYPE,Constants.SIGNUP)
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
