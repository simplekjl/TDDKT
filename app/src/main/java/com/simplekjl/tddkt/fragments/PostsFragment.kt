package com.simplekjl.tddkt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.PostAdapter
import com.simplekjl.tddkt.adapters.PostAdapter.OnPostClicked
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Post
import kotlinx.android.synthetic.main.fragment_users.*

class PostsFragment : Fragment(), OnPostClicked{

    private  var onInteractionPostFragment : OnInteractionPostFragment? =null
    override fun onPostClicked(postId: Int) {
        onInteractionPostFragment?.OnClickedItemPostFragment(postId)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Post"
        arguments?.let {

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
        viewModel.getPosts().observe(viewLifecycleOwner, Observer<List<Post>> {
            val gridLayoutManager = GridLayoutManager(activity, 1)
            rv_generic.layoutManager = gridLayoutManager
            val adapter = PostAdapter(it,viewLifecycleOwner,this)
            rv_generic.adapter = adapter
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PostsFragment.OnInteractionPostFragment) {
            onInteractionPostFragment = context
        } else {
            throw RuntimeException(context.toString() + " must implement UsersFragmentInteractionListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
//        listener = null
    }

    interface OnInteractionPostFragment{
        fun OnClickedItemPostFragment(postId: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PostsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
