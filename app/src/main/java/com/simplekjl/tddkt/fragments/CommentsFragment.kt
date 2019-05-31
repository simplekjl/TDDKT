package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.CommentAdapter
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Comment
import kotlinx.android.synthetic.main.fragment_users.*

class CommentsFragment : Fragment() {


    private var listener: CommentAdapter.OnCommentClicked? = null
    private var postId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Comments"
        arguments?.let {
            postId = it.getInt("postId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.init()
        //avoid leaks using the lifecycle of the fragment
        viewModel.getCommentsByPostId(postId).observe(viewLifecycleOwner, Observer<List<Comment>> {
            val gridLayoutManager = GridLayoutManager(activity, 1)
            rv_generic.layoutManager = gridLayoutManager
            val adapter = CommentAdapter(it, listener)
            rv_generic.adapter = adapter
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    //                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
