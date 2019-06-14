package com.simplekjl.tddkt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.PostAdapter
import com.simplekjl.tddkt.adapters.PostAdapter.OnPostClicked
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.ui.UiState
import com.simplekjl.tddkt.viewModels.MainViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : BaseFragment(), OnPostClicked {
    override fun render(state: UiState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var isTwoPanel = false
    private var userId: Int = -1
    private var onInteractionPostFragment: OnInteractionPostFragment? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Posts"
        arguments?.let {
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
        showLoader()
//        viewModel.init()
        if (userId != -1) {
            viewModel.getPostsByUserID(userId).observe(viewLifecycleOwner, Observer<List<Post>> {
                setAdapter(it)
            })
        } else {
            //avoid leaks using the lifecycle of the fragment
            viewModel.getPosts().observe(viewLifecycleOwner, Observer<List<Post>> {
                setAdapter(it)
            })
        }

    }

    private fun setAdapter(it: List<Post>) {
        if (it.isEmpty()) {
            showErrorMessage("There is no post to show")
        } else {
            showItems()
            val gridLayoutManager = GridLayoutManager(activity, 1)
            rv_generic.layoutManager = gridLayoutManager
            val adapter =
                activity?.let { currentActivity ->
                    PostAdapter(
                        it,
                        viewLifecycleOwner,
                        this,
                        viewModel,
                        currentActivity
                    )
                }
            rv_generic.adapter = adapter
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // place to update the view given the fragment lifecycle
        super.updateActivityTitle(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PostsFragment.OnInteractionPostFragment) {
            onInteractionPostFragment = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnInteractionPostFragment")
        }
    }


    override fun onDetach() {
        super.onDetach()
//        listener = null
    }

    override fun onPostClicked(postId: Int) {
        onInteractionPostFragment?.onClickedItemPostFragment(postId)
    }


    interface OnInteractionPostFragment {
        fun onClickedItemPostFragment(postId: Int)
    }

    companion object {
        val userIdObserver: MutableLiveData<Int> = MutableLiveData()
        @JvmStatic
        fun newInstance(twoPanel: Boolean, userId: Int) =
            PostsFragment().apply {
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
