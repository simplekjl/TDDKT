package com.simplekjl.tddkt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.UserAdapter
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.ui.ErrorMessage
import com.simplekjl.tddkt.ui.Loading
import com.simplekjl.tddkt.ui.Success
import com.simplekjl.tddkt.ui.UiState
import com.simplekjl.tddkt.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UsersFragment : BaseFragment(), UserAdapter.OnUserClicked {

    private var onUserFragmentListener: OnUsersFragmentListener? = null
    private val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // params in case we need
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //avoid leaks using the lifecycle of the fragment
        viewModel.getUsersAndStoreInCache().observe(viewLifecycleOwner, Observer<UiState> { state ->
            render(state)
        })
    }

    override fun onUserClicked(position: Int) {
        //launch the profile fragment
        onUserFragmentListener?.onMoreDetailsCliked(position)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        super.updateActivityTitle(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnUsersFragmentListener) {
            onUserFragmentListener = context
        } else {
            throw RuntimeException("$context must implement OnUsersFragmentListener ")
        }
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
                showItems()
                createAdapter(state.data as List<User>)
            }
        }
    }

    private fun createAdapter(data: List<User>) {
        activity?.let {
            val linearLayoutManager = LinearLayoutManager(activity)
            rv_generic.layoutManager = linearLayoutManager
            val adapter = UserAdapter(data, this)
            rv_generic.adapter = adapter
        }
    }

    override fun onDetach() {
        super.onDetach()
//        listener = null
        this.onUserFragmentListener = null
    }

    interface OnUsersFragmentListener {
        fun onMoreDetailsCliked(userId: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

}
