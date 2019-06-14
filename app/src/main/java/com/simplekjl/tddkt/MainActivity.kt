package com.simplekjl.tddkt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.simplekjl.tddkt.fragments.CommentsFragment
import com.simplekjl.tddkt.fragments.PostsFragment
import com.simplekjl.tddkt.fragments.UserProfileFragment
import com.simplekjl.tddkt.fragments.UsersFragment


class MainActivity : AppCompatActivity(),
    PostsFragment.OnInteractionPostFragment,
    UsersFragment.OnUsersFragmentListener {

    private var isTwoPanel = false
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            isTwoPanel = true
            setupTwoPanelView()
        } else {
            setFragment(PostsFragment.newInstance(isTwoPanel, userId))
            isTwoPanel = false
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (isTwoPanel) {
            CommentsFragment.postIdObserver.value = -1
            if (CommentsFragment.postIdObserver.value == -1) {
                super.onBackPressed()
            }
        } else {
            if (count == 1) {
                finish()
            } else {
                supportFragmentManager.popBackStack()
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

        //TODO update the navigation for landscape when using profile fragment
        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragment2, PostsFragment.newInstance(isTwoPanel, userId))
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

    override fun onMoreDetailsCliked(userId: Int) {
        if (isTwoPanel) {
            PostsFragment.userIdObserver.value = userId
        } else {
            val bundle = Bundle()
            bundle.putInt("userId", userId)
            val fragment = UserProfileFragment.newInstance(isTwoPanel, userId)
            fragment.arguments = bundle
            setFragment(fragment)
        }
    }

    fun updateTitleAndDrawer(fragment: Fragment) {
        when (fragment) {
            is PostsFragment -> {
                supportActionBar?.title = "Posts"
                title = getString(R.string.posts_title)
            }
            is CommentsFragment -> {
                supportActionBar?.title = "Comments"
                title = "Comments"
            }
            is UsersFragment -> {
                supportActionBar?.title = "User"
                title = "Users"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_close -> {
                finish()
            }
            R.id.menu_posts -> {
                setFragment(PostsFragment.newInstance(isTwoPanel, userId))

            }
            R.id.menu_users -> {
                setFragment(UsersFragment.newInstance())
            }
        }
        return true
    }

}
