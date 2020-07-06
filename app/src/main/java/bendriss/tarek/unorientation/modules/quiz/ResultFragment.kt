package bendriss.tarek.unorientation.modules.quiz

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.data.source.remote.params.QuizParam
import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse
import bendriss.tarek.unorientation.databinding.FragmentResultBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.dashboard.DashboardFragment
import bendriss.tarek.unorientation.modules.jobstats.JobStatsFragment
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private var mBinding: FragmentResultBinding? = null
    private var mDisposable: CompositeDisposable? = null
    private var mQuizViewModel: QuizViewModel? = null
    private var resultTxt:TextView? = null

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_result, container, false)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        initDataBinding()
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = preferences?.edit()

        var quizParam = QuizParam()
        quizParam.name = sharedpreferences.getString("quizName", "")
        quizParam.finalQuiz = "true"
        quizParam.userId = sharedpreferences.getInt(Constants.USER_ID, 0)
        getQuiz(quizParam)


        mBinding?.homeBtn?.setOnClickListener(View.OnClickListener {
            //activity?.onBackPressed()
            fragmentManager?.beginTransaction()?.replace(R.id.fragment,DashboardFragment())?.commit()
        })

        mBinding?.offresBtn?.setOnClickListener(View.OnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, JobStatsFragment())?.addToBackStack(null)?.commit()
""        })


        return mBinding?.root
    }

    private fun initDataBinding()
    {
        resultTxt = mBinding?.resultTxt
    }

    private fun getQuiz(quizParam: QuizParam)
    {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        quizParam.userId = sharedpreferences.getInt(Constants.USER_ID, 0)
        //Logger.e("Get Quiz Response", response.toString())

        mDisposable = CompositeDisposable()
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        mQuizViewModel?.getQuizV2(quizParam)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(context) }
                ?.doOnSuccess { Handler().postDelayed({ App.hideLoader()} , 500) }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<QuizResponse?>() {
                    override fun onSuccess(response: QuizResponse) {
                        Handler().postDelayed({
                            Logger.e("Get Quiz Response", response.toString())
                            resultTxt?.text = response.question
                            //resultTxt?.text = response.id
                            mBinding?.code?.text = response.name
                            mBinding?.rang?.text = response.answersSize.toString()
                            mBinding?.votreRang?.text = response.answers[0].toString()

                            Glide.with(context!!).applyDefaultRequestOptions(RequestOptions().disallowHardwareConfig())
                                    .load("http://5.135.52.75/img/"+response.name+".png")
                                    .apply(RequestOptions.circleCropTransform())
                                    //.placeholder(R.drawable.empty)
                                    .into(mBinding?.resultImg!!)

                            editor?.putString("Keyword",response.id)
                            editor?.apply()

                        } , 500)

                    }

                    override fun onError(error: Throwable) {
                        Logger.e("Get Quiz error", error.toString())
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }


}