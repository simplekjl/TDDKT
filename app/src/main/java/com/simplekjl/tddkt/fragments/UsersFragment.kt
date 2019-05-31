package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.UserAdapter
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.User
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : Fragment(), UserAdapter.OnUserClicked {


    private var adapterListener: UserAdapter.OnUserClicked? = this
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
            val gridLayoutManager = LinearLayoutManager(activity)
            rv_generic.layoutManager = gridLayoutManager
            val adapter = UserAdapter(it, adapterListener)
            rv_generic.adapter = adapter
        })
    }

    override fun onUserClicked(position: Int) {
        Toast.makeText(activity, "text", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onDetach() {
        super.onDetach()
//        listener = null
        this.adapterListener = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersFragment.
         */
        // TODO: Rename and change types and number of parameters
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
