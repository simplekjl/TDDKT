package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.databinding.FragmentUserProfileBinding
import com.simplekjl.tddkt.ui.ErrorMessage
import com.simplekjl.tddkt.ui.Loading
import com.simplekjl.tddkt.ui.Success
import com.simplekjl.tddkt.ui.UiState
import com.simplekjl.tddkt.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserProfileFragment : BaseFragment() {
    private var userId: Int = -1
    private var isTwoPanel = false
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentUserProfileBinding

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_profile, container, false
        )
        //getting the user and passing it to the dataBinding
        viewModel.getUserById(userId).observe(viewLifecycleOwner, Observer<UiState> { state ->
            render(state)

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


    override fun render(state: UiState) {
        when (state) {
            is Loading -> {
                showLoader()
            }
            is ErrorMessage -> {
                showErrorMessage(state.msg)
            }
            is Success<*> -> {
                val user = state.data as User
                binding.user = user
                activity?.title = user.username
            }
        }
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
