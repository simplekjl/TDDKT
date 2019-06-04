package com.simplekjl.tddkt

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.simplekjl.tddkt.fragments.CommentsFragment
import com.simplekjl.tddkt.fragments.PostsFragment
import com.simplekjl.tddkt.fragments.UsersFragment


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
            .commitAllowingStateLoss()
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
            is UsersFragment ->{
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
                setFragment(PostsFragment.newInstance(isTwoPanel))

            }
            R.id.menu_users -> {
                setFragment(UsersFragment.newInstance())
            }
        }
        return true
    }
}
