package bendriss.tarek.unorientation.modules.jobstats

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bendriss.tarek.unorientation.App
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.data.source.remote.params.ScrapParam
import bendriss.tarek.unorientation.data.source.remote.response.ScrapResponse
import bendriss.tarek.unorientation.databinding.FragmentJobStatsBinding
import bendriss.tarek.unorientation.modules.dashboard.DashboardActivity
import bendriss.tarek.unorientation.util.AlertDialogUtils
import bendriss.tarek.unorientation.util.Constants
import bendriss.tarek.unorientation.util.Logger
import bendriss.tarek.unorientation.util.StringUtils
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class JobStatsFragment : Fragment() {

    var mBinding:FragmentJobStatsBinding? = null

    private var mDisposable: CompositeDisposable? = null
    private var mScrapViewModel: ScrapViewModel? = null

    private var recyclerView: RecyclerView? = null
    private var jobsAdapter:JobsAdapter? = null

    private var mLayoutManager: LinearLayoutManager? = null
    private var mSlideInUpAnimator: SlideInUpAnimator? = null

    private var filterPopup:PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_job_stats, container, false)

        initDataBinding()

        initRecyclerView()


        var preferences = PreferenceManager.getDefaultSharedPreferences(context)
        var et:EditText = mBinding?.searchET!!
        var s:String = preferences?.getString("Keyword","")!!
        mBinding?.searchET?.text = Editable.Factory.getInstance().newEditable(s)
        //var param:ScrapParam = ScrapParam("android")
        var param:ScrapParam = ScrapParam(preferences.getString("Keyword","enseignant"))
        getScrap(param)
        return mBinding?.root
    }



    private fun initDataBinding() {


        recyclerView = mBinding?.recyclerView



        mBinding?.infoTn?.setOnClickListener(View.OnClickListener {

            //CustomAddDoseDialog alert = new CustomAddDoseDialog(d1,d2,d3);
            val alert = CustomInfoDialog(activity)
            alert.showDialog(activity,"tn")

        })

        mBinding?.infoWrld?.setOnClickListener(View.OnClickListener {

            //CustomAddDoseDialog alert = new CustomAddDoseDialog(d1,d2,d3);
            val alert = CustomInfoDialog(activity)
            alert.showDialog(activity,"wrld")

        })


        mBinding?.searchIcon?.setOnClickListener(View.OnClickListener {
            var param = ScrapParam(mBinding?.searchET?.text.toString())

            if(!mBinding?.searchET?.text.toString().equals("") && mBinding?.searchET?.text.toString().isNotEmpty())
            getScrap(param)
            else
            {
                val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                mBinding?.searchET?.startAnimation(shake)

                val snackbar: Snackbar = Snackbar
                        .make(mBinding?.root!!, "Veuillez remplir le champ", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(context?.resources?.getColor(R.color.pokmy)!!)

                val mView: View = snackbar.view
                val mTextView = mView.findViewById<View>(R.id.snackbar_text) as TextView

                val face: Typeface = Typeface.createFromAsset(context?.assets,
                        "fonts/comfortaalight.ttf")
                mTextView.typeface = face
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER else mTextView.gravity = Gravity.CENTER_HORIZONTAL

                snackbar.show()
            }

        })

                /*
        mBinding?.infoTn?.setOnClickListener(View.OnClickListener {
            dismissPopup()
            filterPopup = showAlertFilter()
            filterPopup?.isOutsideTouchable = true
            filterPopup?.isFocusable = true
            filterPopup?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            filterPopup?.showAsDropDown(mBinding?.infoTn)
        }
        )

                 */

    }





    private fun initRecyclerView() {

        jobsAdapter = JobsAdapter()
        mLayoutManager = LinearLayoutManager(context)
        val layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerView?.setLayoutManager(layoutManager)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setAdapter(jobsAdapter)
        //mSlideInUpAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        mSlideInUpAnimator?.setAddDuration(1000)
        recyclerView?.setItemAnimator(mSlideInUpAnimator)

    }

    private fun getScrap(param:ScrapParam)
    {
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val userId= sharedpreferences.getInt(Constants.USER_ID, 0)
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()

        mDisposable = CompositeDisposable()
        mScrapViewModel = ViewModelProviders.of(this).get(ScrapViewModel::class.java)
        mScrapViewModel?.getScrap(param)
                ?.subscribeOn(Schedulers.computation())
                ?.doOnSubscribe { App.showLoader(context) }
                ?.doOnSuccess { Handler().postDelayed({ App.hideLoader()} , 500) }
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<ScrapResponse>() {
                    override fun onSuccess(response: ScrapResponse) {
                        Logger.e("scrap response", "tunisia "+response.nbrTunisie + "  world  "+response.nbrMonde)
                        mBinding?.textView2?.text = response.nbrTunisie + " jobs"
                        mBinding?.textView3?.text = response.nbrMonde + " jobs"
                        for(item : Job in response.jobs)
                        {
                            Logger.e("scrap response", item.toString())
                        }

                        jobsAdapter?.change(response.jobs)
                        jobsAdapter?.notifyDataSetChanged()
                    }

                    override fun onError(error: Throwable) {
                        Logger.e("Get historique error", error.toString())
                        App.hideLoader()
                        AlertDialogUtils.showString(activity as DashboardActivity, R.string.error_incorrect_password, getString(R.string.error_incorrect_password_msg), null)
                    }
                })?.let { mDisposable?.add(it) }
    }


}