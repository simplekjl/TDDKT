package com.simplekjl.tddkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.simplekjl.tddkt.fragments.CommentsFragment
import com.simplekjl.tddkt.fragments.PostsFragment

class MainActivity : AppCompatActivity(), PostsFragment.OnInteractionPostFragment {

    private val TAG_POST = "POSTS"
    private val TAG_COMMENTS = "POSTS"
    override fun OnClickedItemPostFragment(postId: Int) {
        val bundle = Bundle()
        bundle.putInt("postId", postId)
        val fragment = CommentsFragment.newInstance()
        fragment.arguments = bundle
        setFragment(fragment, TAG_COMMENTS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(PostsFragment.newInstance(), TAG_POST)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    fun setFragment(newInstance: Fragment, tag: String) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, newInstance)
            .addToBackStack(null)
            .commit()
    }
}
