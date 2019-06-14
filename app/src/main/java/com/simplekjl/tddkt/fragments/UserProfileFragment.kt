package com.simplekjl.tddkt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.databinding.FragmentUserProfileBinding
import com.simplekjl.tddkt.ui.UiState
import com.simplekjl.tddkt.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : BaseFragment() {
    override fun render(state: UiState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var listener: OnFragmentUserProfilenteractiion? = null
    private var userId: Int = -1
    private var isTwoPanel = false
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt("userId")
            isTwoPanel = it.getBoolean("isTwoPanel")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUserProfileBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_profile, container, false
        )
        showLoader()
//        viewModel.init()
        //getting the user and passing it to the dataBinding
        viewModel.getUserById(userId).observe(viewLifecycleOwner, Observer<User> {
            binding.user = it
            activity?.title = it.username
        })

        childFragmentManager.beginTransaction()
            .replace(R.id.profile_fragment, PostsFragment.newInstance(isTwoPanel, userId))
            .commit()

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        super.updateActivityTitle(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentUserProfilenteractiion) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentUserProfilenteractiion")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentUserProfilenteractiion {
        fun onUserClicks()
    }

    companion object {

        @JvmStatic
        fun newInstance(twoPanel: Boolean, userId: Int) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    this.putInt("userId", userId)
                    this.putBoolean("isTwoPanel", twoPanel)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}
