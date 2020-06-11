package bendriss.tarek.unorientation.modules.history

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse
import bendriss.tarek.unorientation.databinding.FragmentHistoryBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.modules.quiz.QuizViewModel
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class HistoryFragment : Fragment() {

    private var mDisposable: CompositeDisposable? = null
    private var mQuizViewModel: QuizViewModel? = null
    private var historiqueRecycler: RecyclerView? = null
    private var historyAdapter: HistoryAdapter? = null

    private var mLayoutManager: LinearLayoutManager? = null
    private var mSlideInUpAnimator: SlideInUpAnimator? = null

    private var mBinding: FragmentHistoryBinding? = null
    private var historique:ArrayList<History>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_history, container, false)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        initDataBinding()
        initRecyclerView()

        getHistorique()


        return mBinding?.root
    }

    private fun initDataBinding() {
        historiqueRecycler = mBinding?.historique
        historique = ArrayList<History>()
        // mBinding = DataBindingUtil.setContentView(mActivity, R.layout.fragment_quiz)
    }


    private fun initRecyclerView() {
        historyAdapter = HistoryAdapter()
        mLayoutManager = LinearLayoutManager(context)
        historiqueRecycler?.setLayoutManager(mLayoutManager)
        historiqueRecycler?.setHasFixedSize(true)
        historiqueRecycler?.setAdapter(historyAdapter)
        mSlideInUpAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        mSlideInUpAnimator?.setAddDuration(1000)
        historiqueRecycler?.setItemAnimator(mSlideInUpAnimator)
    }

    private fun getHistorique()
    {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val userId= sharedpreferences.getInt(Constants.USER_ID, 0)
        //Logger.e("Get Quiz Response", response.toString())
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()

        mDisposable = CompositeDisposable()
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        mQuizViewModel?.getHistorique(userId)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(context) }
                ?.doOnSuccess { Handler().postDelayed({ App.hideLoader()} , 500) }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<List<HistoriqueResponse>>() {
                    override fun onSuccess(response: List<HistoriqueResponse>) {
                        for(item : HistoriqueResponse in response)
                        {
                            var history:History = History()
                            history.id = item.id
                            history.idUser = item.idUser
                            history.resultName = item.resultName
                            history.date = item.date
                            //historique?.toMutableList()?.add(history)
                            historique?.add(history)
                            //historique.(history)
                            Logger.e("Historique response", item.resultName)
                        }

                        for(item : History in historique?.toMutableList()!!)
                        {
                            Logger.e("Historique response2", item.resultName)
                        }
                        historyAdapter?.change(historique)
                    }

                    override fun onError(error: Throwable) {
                        Logger.e("Get historique error", error.toString())
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }
}