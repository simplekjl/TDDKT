package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.CommentAdapter
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Comment
import kotlinx.android.synthetic.main.fragment_users.*

class CommentsFragment : BaseFragment() {

    private var listener: CommentAdapter.OnCommentClicked? = null
    private var postId: Int = 0
    private var isTwoPanel = false
    private lateinit var viewModel: MainViewModel

    fun CommentsFragment() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Comments"
        arguments?.let {
            postId = it.getInt("postId")
            isTwoPanel = it.getBoolean("isTwoPanel")
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        showLoader()
        viewModel.init()
        if (isTwoPanel) {
            showErrorMessage("Click any post to see the comments")
            postIdObserver.observe(viewLifecycleOwner, Observer {
                setupView(it)
            })
        } else {
            setupView(postId)
        }

    }

    fun setupView(postId: Int) {
        if (postId == -1) {
            showErrorMessage("Click any post to see the comments")
        } else {
            //avoid leaks using the lifecycle of the fragment
            viewModel.getCommentsByPostId(postId).observe(viewLifecycleOwner, Observer<List<Comment>> {
                if (it.isEmpty()) {
                    showErrorMessage("There is no comments to show")
                } else {
                    showItems()
                    val gridLayoutManager = GridLayoutManager(activity, 1)
                    rv_generic.layoutManager = gridLayoutManager
                    val adapter = CommentAdapter(it, listener)
                    rv_generic.adapter = adapter
                }
            })
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        val postIdObserver: MutableLiveData<Int> = MutableLiveData()

        @JvmStatic
        fun newInstance(twoPanel: Boolean) =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    this.putBoolean("isTwoPanel", twoPanel)
                }
            }
    }
}
