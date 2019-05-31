package com.simplekjl.tddkt

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.simplekjl.tddkt.fragments.CommentsFragment
import com.simplekjl.tddkt.fragments.PostsFragment


class MainActivity : AppCompatActivity(), PostsFragment.OnInteractionPostFragment {

    private var isTwoPanel = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            isTwoPanel = true
            setupTwoPanelView()
        } else {
            setFragment(PostsFragment.newInstance(isTwoPanel))
            isTwoPanel = false
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (isTwoPanel) {
            CommentsFragment.postIdObserver.value = -1
            if (CommentsFragment.postIdObserver.value == -1){
                super.onBackPressed()
            }
        }
        else{
            if (count == 0) {
                super.onBackPressed()
            } else {
                supportFragmentManager.popBackStack()
                val f = supportFragmentManager.findFragmentById(R.id.fragment)
                if (f != null) {
                    updateTitleAndDrawer(f)
                }
            }
        }
    }

    fun setFragment(newInstance: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, newInstance)
            .addToBackStack(null)
            .commit()
    }

    private fun setupTwoPanelView() {

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragment2, PostsFragment.newInstance(isTwoPanel))
            .commit()

        fragmentManager.beginTransaction()
            .replace(R.id.fragment3, CommentsFragment.newInstance(isTwoPanel))
            .commit()
    }

    override fun onClickedItemPostFragment(postId: Int) {
        if (isTwoPanel) {
            CommentsFragment.postIdObserver.value = postId
        } else {
            val bundle = Bundle()
            bundle.putInt("postId", postId)
            val fragment = CommentsFragment.newInstance(isTwoPanel)
            fragment.arguments = bundle
            setFragment(fragment)
        }
    }

    private fun updateTitleAndDrawer(fragment: Fragment) {
        if (fragment is PostsFragment) {
            supportActionBar?.title = "Posts"
            title = getString(R.string.posts_title)
            //set selected item position, etc
        } else if (fragment is CommentsFragment) {
            supportActionBar?.title = "Comments"
            setTitle("Comments")
        }
    }
}
