package bendriss.tarek.unorientation.modules.quiz


import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.base.BaseFragment
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse
import bendriss.tarek.unorientation.databinding.FragmentQuizBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.ItemClickEvent
import bendriss.tarek.unorientation.util.Logger
import com.transitionseverywhere.Slide
import com.transitionseverywhere.TransitionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class QuizFragment : BaseFragment(), Animation.AnimationListener{

    private var mDisposable: CompositeDisposable? = null
    private var mQuizViewModel: QuizViewModel? = null

    private var quizRecycler: RecyclerView? = null
    private var answersList: List<String>? = null
    private var quizAdapter: QuizAdapter? = null

    private var mLayoutManager: LinearLayoutManager? = null
    private var mSlideInUpAnimator: SlideInUpAnimator? = null

    private var mBinding: FragmentQuizBinding? = null
    private var swipeBtn: ImageView? = null
    private var questionTxt: TextView? = null

    private var transitionsContainer: ViewGroup? = null

    private var i:Int = 1
    private var questionName:String =""
    private var firstTime:Boolean = true
    private var isFinal:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        initDataBinding()

        //var view:View =  inflater.inflate(R.layout.fragment_quiz, container, false)

        //initDataBinding()
        initRecyclerView()

        EventBus.getDefault().register(this)

        var quizParam:QuizParam = QuizParam()
        questionName = ""
        quizParam.name = ""
        quizParam.finalQuiz = "false"
        //getQuiz("0")
        getQuiz(quizParam)
        return mBinding?.root

        //return view
    }


    private fun initDataBinding() {
       // mBinding = DataBindingUtil.setContentView(mActivity, R.layout.fragment_quiz)

        swipeBtn = mBinding?.swipeBtn
        transitionsContainer = mBinding?.transitionsContainer
        questionTxt = mBinding?.questionTxt

        quizRecycler = mBinding?.quizRecycler


        var quizParam:QuizParam = QuizParam()


    }

    private fun getQuiz(quizParam:QuizParam)
    {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        quizParam.userId = sharedpreferences.getInt(Constants.USER_ID, 0)
        //Logger.e("Get Quiz Response", response.toString())
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()

        mDisposable = CompositeDisposable()
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        mQuizViewModel?.getQuizV2(quizParam)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(context) }
                ?.doOnSuccess { Handler().postDelayed({App.hideLoader()} , 500) }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<QuizResponse?>() {
                    override fun onSuccess(response: QuizResponse) {
                        Handler().postDelayed({
                            Logger.e("Get Quiz Response", response.toString())
                            questionTxt?.text = response.question
                            i=response.answersSize
                            answersList=ArrayList<String>()
                            answersList=response.answers
                            quizAdapter?.change(answersList)
                            quizAdapter?.notifyDataSetChanged()


                            questionName = response.name


                            isFinal = response.final
                            /*
                            if (response.final==true)
                            {
                                editor!!.putString("quizName", response.name)
                                editor!!.apply()
                                fragmentManager?.beginTransaction()?.replace(R.id.fragment, ResultFragment())?.commit()
                            }
                             */
                            generateQuestion(response.answersSize)
                        } , 500)

                    }

                    override fun onError(error: Throwable) {
                        Logger.e("Get Quiz error", error.toString())
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }

    private fun getQuizV0(name:String)
    {
        mDisposable = CompositeDisposable()
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        mQuizViewModel?.getQuiz(name)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(context) }
                ?.doOnSuccess { Handler().postDelayed({App.hideLoader()} , 500) }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<QuizResponse?>() {
                    override fun onSuccess(response: QuizResponse) {
                        Handler().postDelayed({
                            Logger.e("SigninResponse", response.toString())
                            questionTxt?.text = response.question
                            i=response.answersSize
                            answersList=ArrayList<String>()
                            answersList=response.answers
                            quizAdapter?.change(answersList)
                            quizAdapter?.notifyDataSetChanged()

                            generateQuestion(response.answersSize)
                        } , 500)

                    }

                    override fun onError(error: Throwable) {
                        Logger.e("SigninResponse error", error)
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }

    private fun initRecyclerView() {
        quizAdapter = QuizAdapter()
        mLayoutManager = LinearLayoutManager(mContext)
        quizRecycler?.setLayoutManager(mLayoutManager)
        quizRecycler?.setHasFixedSize(true)
        quizRecycler?.setAdapter(quizAdapter)
        mSlideInUpAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        mSlideInUpAnimator?.setAddDuration(1000)
        quizRecycler?.setItemAnimator(mSlideInUpAnimator)
    }


    private fun generateQuestion(i:Int)
    {
        questionTxt?.visibility = View.INVISIBLE
        quizRecycler?.visibility = View.INVISIBLE

        val anim: Animation? =   AnimationUtils.loadAnimation(mContext, R.anim.anim_txt)
        anim?.setAnimationListener(this)

        //questionTxt?.startAnimation(anim)
        //quizRecycler?.startAnimation(anim)
        Handler().postDelayed({
            questionTxt?.startAnimation(anim)
            quizRecycler?.startAnimation(anim)
            Handler().postDelayed({} , 300)
        } , 500)
    }

    override fun onAnimationEnd(arg0: Animation?) {
        // TODO Auto-generated method stub
        questionTxt?.clearAnimation()
        quizRecycler?.clearAnimation()

        questionTxt?.visibility=View.VISIBLE
        quizRecycler?.visibility=View.VISIBLE
}

    companion object {
        @JvmStatic
        fun answerClicker(pos:Int) {
            //getQuiz()
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe
    fun onEvent(event:ItemClickEvent )
    {

        if(firstTime)
        {
            questionName+=event.heure.toString()
            firstTime=false
        }
        else
        {
            if(questionName.last()=='#')
                questionName+=event.heure.toString()
            else
                questionName+="#"+event.heure.toString()
        }
        questionName.replace("\\u00E9","é")
        var quizParam = QuizParam()
        quizParam.name = questionName
        quizParam.finalQuiz = "false"
        //getQuizV0(questionName)

        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        quizParam.userId = sharedpreferences.getInt(Constants.USER_ID, 0)
        //Logger.e("Get Quiz Response", response.toString())
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()
        if(isFinal==true)
        {
            editor!!.putString("quizName", questionName)
            editor!!.apply()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, ResultFragment())?.commit()
        }
        else getQuiz(quizParam)
        Logger.e("EVENT",event.heure)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onAnimationRepeat(arg0: Animation?) {
    }

    override fun onAnimationStart(arg0: Animation?) {
    }




}
