package com.simplekjl.tddkt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.UserAdapter
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.User
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : BaseFragment(), UserAdapter.OnUserClicked {


    private var onUserFragmentListener: OnUsersFragmentListener? = null

    //private var listener: UsersFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.init()
        //avoid leaks using the lifecycle of the fragment
        viewModel.getUsers().observe(viewLifecycleOwner, Observer<List<User>> {
            val linearLayoutManager = LinearLayoutManager(activity)
            rv_generic.layoutManager = linearLayoutManager
            val adapter = UserAdapter(it, this)
            rv_generic.adapter = adapter
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
        if (context is UsersFragment.OnUsersFragmentListener) {
            onUserFragmentListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnUsersFragmentListener ")
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

    companion object {
        @JvmStatic
        fun newInstance() =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    //                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
