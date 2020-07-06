package bendriss.tarek.unorientation.modules.score

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseFragment
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.data.source.remote.params.LoginParams
import bendriss.tarek.unorientation.data.source.remote.params.ScoreParam
import bendriss.tarek.unorientation.data.source.remote.response.LoginResponse
import bendriss.tarek.unorientation.data.source.remote.response.ObjectResponse
import bendriss.tarek.unorientation.databinding.FragmentScoreBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.landingpage.LandingPageActivity
import bendriss.tarek.unorientation.modules.login.LoginViewModel
import bendriss.tarek.unorientation.modules.singup.ActionsDelegate
import bendriss.tarek.unorientation.modules.singup.CustomBacDialog
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
import java.io.IOException

class ScoreFragment : BaseFragment(), ActionsDelegate{

    private var mBinding: FragmentScoreBinding? = null
    private var bac:Button? = null
    private var ma1:EditText? = null
    private var ma2:EditText? = null
    private var ma3:EditText? = null
    private var ma4:EditText? = null
    private var ma5:EditText? = null
    private var ma6:EditText? = null
    private var ma7:EditText? = null
    private var mg:EditText? = null
    private var calculBtn:Button? = null
    private var thisFragment:ScoreFragment? = null

    private var mDisposable: CompositeDisposable? = null
    private var mSignInViewModel: LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //val view= inflater.inflate(R.layout.fragment_score, container, false)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        initDataBinding()

        return mBinding?.root
    }

    private fun initDataBinding()
    {
        thisFragment = this

        bac = mBinding?.bacEt
        mg = mBinding?.moyG
        ma1 = mBinding?.loginEt
        ma2 = mBinding?.passwordEt
        ma3 = mBinding?.nomEt
        ma4 = mBinding?.bacMoyEt
        ma5 = mBinding?.rankEt
        ma6 = mBinding?.rankEt2
        ma7 = mBinding?.rankEt3
        calculBtn = mBinding?.calculBtn

        mg?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma1?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma2?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma3?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma4?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma5?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma6?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))
        ma7?.filters = arrayOf<InputFilter>(InputFilterMinMax("0.0", "20.00"))


        calculBtn?.setOnClickListener {
            if (isValid()) {
                getScore()
                            } else
                snackAndShakeAllEmpty(mContext,mBinding?.mainLayout as View)
        }

        bac?.setOnClickListener{
            val alert = CustomBacDialog(activity,this)
            alert.showDialog(activity,this,"wrld")
        }
    }


    private fun getScore()
    {
        mDisposable = CompositeDisposable()
        mSignInViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val lp = prepareObject()

        mSignInViewModel?.getScore(lp)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { disposable: Disposable? -> App.showLoader(context) }
                ?.doOnSuccess { disposable: ObjectResponse? -> App.hideLoader() }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<ObjectResponse?>() {
                    override fun onSuccess(response: ObjectResponse) {
                        Logger.e("SigninResponse", response.toString())
                        val alert = CustomScoreDialog(activity,thisFragment)
                        alert.showDialog(activity,thisFragment,response.responseMessage)
                    }

                    override fun onError(error: Throwable) {
                        Logger.e("SigninResponse error", error)
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_connection, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }

    private fun isValid(): Boolean {
        if(bac?.text == "Informatique" || bac?.text == "Sport")
        return (StringUtils.isNotEmpty(mg!!.text.toString())
                && StringUtils.isNotEmpty(ma1!!.text.toString())
                && StringUtils.isNotEmpty(ma2!!.text.toString())
                && StringUtils.isNotEmpty(ma3!!.text.toString())
                && StringUtils.isNotEmpty(ma4!!.text.toString())
                && StringUtils.isNotEmpty(ma5!!.text.toString())
                && StringUtils.isNotEmpty(ma6!!.text.toString())
                && StringUtils.isNotEmpty(ma7!!.text.toString())
                )
        else if(bac?.text == "Economie" )
        {
            return (StringUtils.isNotEmpty(mg!!.text.toString())
                    && StringUtils.isNotEmpty(ma1!!.text.toString())
                    && StringUtils.isNotEmpty(ma2!!.text.toString())
                    && StringUtils.isNotEmpty(ma3!!.text.toString())
                    && StringUtils.isNotEmpty(ma4!!.text.toString())
                    && StringUtils.isNotEmpty(ma5!!.text.toString())
                    && StringUtils.isNotEmpty(ma6!!.text.toString())
                    )
        }
        else if(bac?.text == "Sciences" || bac?.text == "Maths"|| bac?.text == "Lettres"|| bac?.text == "Techniques")
        {
            return (StringUtils.isNotEmpty(mg!!.text.toString())
                    && StringUtils.isNotEmpty(ma1!!.text.toString())
                    && StringUtils.isNotEmpty(ma2!!.text.toString())
                    && StringUtils.isNotEmpty(ma3!!.text.toString())
                    && StringUtils.isNotEmpty(ma4!!.text.toString())
                    && StringUtils.isNotEmpty(ma5!!.text.toString())
                    )
        }
        else return true
    }


    fun prepareObject():ScoreParam{
        var score = ScoreParam()
        score.bac = bac?.text.toString()
        score.mg = mg?.text.toString().toFloat()
        score.f = ma1?.text.toString().toFloat()
        score.ang = ma2?.text.toString().toFloat()
        if(bac?.text=="Informatique")
        {
            score.m = ma3?.text.toString().toFloat()
            score.algo = ma4?.text.toString().toFloat()
            score.sp = ma5?.text.toString().toFloat()
            score.tic = ma6?.text.toString().toFloat()
            score.bd = ma7?.text.toString().toFloat()
        }
        else if(bac?.text=="Economie")
        {
            score.m = ma3?.text.toString().toFloat()
            score.ec = ma4?.text.toString().toFloat()
            score.ge = ma5?.text.toString().toFloat()
            score.hg = ma6?.text.toString().toFloat()

        }else if(bac?.text=="Sciences")
        {
            score.m = ma3?.text.toString().toFloat()
            score.sp = ma4?.text.toString().toFloat()
            score.svt = ma5?.text.toString().toFloat()

        }else if(bac?.text=="Maths")
        {
            score.m = ma3?.text.toString().toFloat()
            score.sp = ma4?.text.toString().toFloat()
            score.svt = ma5?.text.toString().toFloat()
        }else if(bac?.text=="Lettres")
        {
            score.a = ma3?.text.toString().toFloat()
            score.ph = ma4?.text.toString().toFloat()
            score.hg = ma5?.text.toString().toFloat()

        }else if(bac?.text=="Sport")
        {
            score.svt = ma3?.text.toString().toFloat()
            score.spsport = ma4?.text.toString().toFloat()
            score.ep = ma5?.text.toString().toFloat()
            score.sp = ma6?.text.toString().toFloat()
            score.ph = ma7?.text.toString().toFloat()

        }else if(bac?.text=="Techniques")
        {
            score.m = ma3?.text.toString().toFloat()
            score.te = ma4?.text.toString().toFloat()
            score.sp = ma5?.text.toString().toFloat()
        }
        return score
    }

    override fun choisirBac(bacString: String?) {
        this.bac?.setText(bacString)
        if(bacString=="Informatique")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            ma6?.visibility = View.VISIBLE
            ma7?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Maths")
            ma4?.setHint("Algorithmiques")
            ma5?.setHint("Sciences physiques")
            ma6?.setHint("TIC")
            ma7?.setHint("Base de données")
        }
        else if(bacString=="Economie")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            ma6?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Maths")
            ma4?.setHint("Economie")
            ma5?.setHint("Gestion")
            ma6?.setHint("Histoire Géo")
        }else if(bacString=="Sciences")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Maths")
            ma4?.setHint("Sciences physiques")
            ma5?.setHint("SVT")

        }else if(bacString=="Maths")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Maths")
            ma4?.setHint("Sciences physiques")
            ma5?.setHint("SVT")
        }else if(bacString=="Lettres")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Arabe")
            ma4?.setHint("Philosophie")
            ma5?.setHint("Histoire Géo")

        }else if(bacString=="Sport")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            ma6?.visibility = View.VISIBLE
            ma7?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("SVT")
            ma4?.setHint("Spécialité sportive")
            ma5?.setHint("Education physique")
            ma6?.setHint("Sciences physique")
            ma7?.setHint("Philosophie")
        }else if(bacString=="Techniques")
        {
            mg?.visibility = View.VISIBLE
            ma1?.visibility = View.VISIBLE
            ma2?.visibility = View.VISIBLE
            ma3?.visibility = View.VISIBLE
            ma4?.visibility = View.VISIBLE
            ma5?.visibility = View.VISIBLE
            calculBtn?.visibility = View.VISIBLE



            ma1?.setHint("Francais")
            ma2?.setHint("Anglais")
            ma3?.setHint("Maths")
            ma4?.setHint("Technique")
            ma5?.setHint("Sciences physiques")
        }

    }


    private fun snackAndShakeAllEmpty(context: Context, mainView:View)
    {
        if (!StringUtils.isNotEmpty(ma1!!.text.toString())) {
            onlyShake(context,ma1 as View)
        }
        if (!StringUtils.isNotEmpty(ma2!!.text.toString())) {
            onlyShake(context,ma2 as View)
        }
        if (!StringUtils.isNotEmpty(ma3!!.text.toString())) {
            onlyShake(context,ma3 as View)
        }
        if (!StringUtils.isNotEmpty(bac!!.text.toString())) {
            onlyShake(context,bac as View)
        }
        if (!StringUtils.isNotEmpty(ma4!!.text.toString())) {
            onlyShake(context,ma4 as View)
        }
        if (!StringUtils.isNotEmpty(ma5!!.text.toString())) {
            onlyShake(context,ma5 as View)
        }
        if (!StringUtils.isNotEmpty(ma6!!.text.toString())) {
            onlyShake(context,ma5 as View)
        }
        if (!StringUtils.isNotEmpty(ma7!!.text.toString())) {
            onlyShake(context,ma5 as View)
        }

        onlySnack(mContext,mBinding?.mainLayout as View,mContext.resources.getString(R.string.required_fields))
    }


    private fun onlyShake(context: Context, viewToShake:View)
    {
        val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        viewToShake?.startAnimation(shake)
    }

    private fun onlySnack(context: Context, mainView:View, text:String)
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

    private fun snackAndShake(context: Context, mainView:View, text:String, viewToShake:View)
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



}

class InputFilterMinMax : InputFilter {
    private var min: Float
    private var max: Float

    constructor(min: Float, max: Float) {
        this.min = min
        this.max = max
    }

    constructor(min: String, max: String) {
        this.min = min.toFloat()
        this.max = max.toFloat()
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        try {
            val input: Float = (dest.toString().toString() + source.toString()).toFloat()
            if (isInRange(min, max, input)) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isInRange(a: Float, b: Float, c: Float): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}