package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.CommentAdapter
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.ui.*
import com.simplekjl.tddkt.viewModels.MainViewModel
import kotlinx.android.synthetic.main.generic_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentsFragment : BaseFragment() {


    private var postId: Int = 0
    private var isTwoPanel = false
    private val viewModel: MainViewModel by viewModel()


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
        return inflater.inflate(R.layout.generic_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoader()
//        viewModel.init()
        if (isTwoPanel) {
            render(TwoPanelView)

        } else {
            updateViewWithNewId(postId)
        }

    }

    private fun updateViewWithNewId(id: Int) {
        viewModel.getCommentsByPostId(id).observe(viewLifecycleOwner, Observer<UiState> { state ->
            render(state)
        })
    }

    override fun render(state: UiState) {
        when (state) {
            is TwoPanelView -> {
                showErrorMessage("Click any post to see more results")
                postIdObserver.observe(viewLifecycleOwner, Observer {
                    updateViewWithNewId(it)
                })
            }
            is Loading -> {
                showLoader()
            }
            is Success<*> -> {
                showItems()
                val list = state.data as List<Comment>
                setAdapter(list)
            }
            is ErrorMessage -> {
                showErrorMessage(state.msg)
            }
        }
    }

    private fun setAdapter(comments: List<Comment>) {
        val gridLayoutManager = GridLayoutManager(activity, 1)
        rv_generic.layoutManager = gridLayoutManager
        val adapter = CommentAdapter(comments)
        rv_generic.adapter = adapter

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        super.updateActivityTitle(this)
    }

    override fun onDetach() {
        super.onDetach()
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}
