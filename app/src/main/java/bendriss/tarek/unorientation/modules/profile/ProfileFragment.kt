package bendriss.tarek.unorientation.modules.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import bendriss.tarek.unorientation.R
import bendriss.tarek.unorientation.databinding.FragmentLoginBinding
import bendriss.tarek.unorientation.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var mBinding: FragmentProfileBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        //initDataBinding()
        return mBinding?.root
    }


}