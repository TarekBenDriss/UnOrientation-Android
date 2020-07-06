package bendriss.tarek.unorientation.modules.dashboard

import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
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
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.data.source.remote.response.HistoriqueResponse
import bendriss.tarek.unorientation.databinding.FragmentDashboardBinding
import bendriss.tarek.unorientation.modules.guidpdf.PdfReadderFragment
import bendriss.tarek.unorientation.modules.history.HistoryFragment
import bendriss.tarek.unorientation.modules.jobstats.JobStatsFragment
import bendriss.tarek.unorientation.modules.login.LoginFragment
import bendriss.tarek.unorientation.modules.profile.ProfileFragment
import bendriss.tarek.unorientation.modules.quiz.QuizFragment
import bendriss.tarek.unorientation.modules.quiz.ResultFragment
import bendriss.tarek.unorientation.modules.score.ScoreFragment
import bendriss.tarek.unorientation.util.ItemClickEvent
import bendriss.tarek.unorientation.util.Logger
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {


    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    private var mBinding: FragmentDashboardBinding? = null
    private var img:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        initDatabinding()

        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()
            editor!!.putString("quizName", "")
            editor!!.apply()

        //EventBus.getDefault().register(this)

        return mBinding?.root
    }

    private fun initDatabinding()
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = preferences?.edit()

        img=mBinding?.menu
        img?.setOnClickListener {
            val item = ItemClickEvent()
            item.date = "DRAWER"
            EventBus.getDefault().post(item)
            Logger.e("DRAWER","OPEN")
        }

        mBinding?.cv1?.setOnClickListener{
            //throw RuntimeException("Test Crash") // Force a crash
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, QuizFragment())?.addToBackStack(null)?.commit()
        }


        mBinding?.cv2?.setOnClickListener{
            // job stats
            var preferences = PreferenceManager.getDefaultSharedPreferences(context)
            var s:String = preferences?.getString("Keyword","")!!
            if(s == "")
            {
                val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
                mBinding?.cv2?.startAnimation(shake)

                val snackbar: Snackbar = Snackbar
                        .make(mBinding?.root!!, "Veuillez remplir le quiz au moins une fois", Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(context?.resources?.getColor(R.color.pokmy)!!)

                val mView: View = snackbar.view
                val mTextView = mView.findViewById<View>(R.id.snackbar_text) as TextView

                val face: Typeface = Typeface.createFromAsset(context?.assets,
                        "fonts/comfortaalight.ttf")
                mTextView.typeface = face
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER else mTextView.gravity = Gravity.CENTER_HORIZONTAL

                snackbar.show()
            }
            else
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, JobStatsFragment())?.addToBackStack(null)?.commit()
        }

        mBinding?.cv3?.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, PdfReadderFragment())?.addToBackStack(null)?.commit()
        }

        mBinding?.cv4?.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, HistoryFragment())?.addToBackStack(null)?.commit()
        }

        mBinding?.cv5?.setOnClickListener{
            // my profile
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, ProfileFragment())?.addToBackStack(null)?.commit()
        }

        mBinding?.cv6?.setOnClickListener{
            // my profile
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, ScoreFragment())?.addToBackStack(null)?.commit()
        }



        val mapper = ObjectMapper()
        var jsonUser:String = preferences?.getString("UserObject","")!!
        Log.e("USEROBJECT",jsonUser)
        try {
            // JSON string to Java object
            val user: UserProfile = mapper.readValue(jsonUser, UserProfile::class.java)
            //Log.e("USEROBJECT","xxxxx"+user.toString())
            mBinding?.menuTxt?.text = user.username


        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedpreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var editor: SharedPreferences.Editor? = sharedpreferences?.edit()
        editor!!.putString("quizName", "")
        editor!!.apply()
    }

}
