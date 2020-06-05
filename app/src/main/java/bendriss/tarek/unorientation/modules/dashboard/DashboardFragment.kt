package bendriss.tarek.unorientation.modules.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.databinding.FragmentDashboardBinding
import bendriss.tarek.unorientation.modules.guidpdf.PdfReadderFragment
import bendriss.tarek.unorientation.modules.login.LoginFragment
import bendriss.tarek.unorientation.modules.quiz.QuizFragment
import bendriss.tarek.unorientation.util.ItemClickEvent
import bendriss.tarek.unorientation.util.Logger
import org.greenrobot.eventbus.EventBus

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
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, QuizFragment())?.addToBackStack(null)?.commit()

        }

        mBinding?.cv3?.setOnClickListener{
            fragmentManager?.beginTransaction()?.replace(R.id.fragment, PdfReadderFragment())?.addToBackStack(null)?.commit()

        }
    }


}
