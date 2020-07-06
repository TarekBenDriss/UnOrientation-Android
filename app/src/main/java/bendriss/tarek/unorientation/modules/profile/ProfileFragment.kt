package bendriss.tarek.unorientation.modules.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.data.source.local.entity.UserProfile
import bendriss.tarek.unorientation.databinding.FragmentLoginBinding
import bendriss.tarek.unorientation.databinding.FragmentProfileBinding
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException


class ProfileFragment : Fragment() {

    private var mBinding: FragmentProfileBinding? = null
    private var preferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val mapper = ObjectMapper()
        var user: UserProfile = UserProfile()
        var jsonUser:String = preferences?.getString("UserObject","")!!
        Log.e("USEROBJECT",jsonUser)
        try {
            user = mapper.readValue(jsonUser, UserProfile::class.java)
            //mBinding?.nameTV?.text = user.username
            mBinding?.nameTV?.text = "Youcef"
            mBinding?.bacV?.text = user.bac
            mBinding?.moyenneV?.text = ""+user.moyBac
            mBinding?.rangV?.text = ""+user.rang


        } catch (e: IOException) {
            e.printStackTrace()
        }

        //initDataBinding()
        return mBinding?.root
    }


}