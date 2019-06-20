package com.simplekjl.tddkt

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.simplekjl.tddkt.adapters.ScreenPagerAdapter
import com.simplekjl.tddkt.fragments.*
import com.simplekjl.tddkt.utils.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    PostsFragment.OnInteractionPostFragment,
    UsersFragment.OnUsersFragmentListener {

    private lateinit var mPager: ViewPager
    private lateinit var  pagerAdapter : ScreenPagerAdapter
    private var isTwoPanel = false
    var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPager = findViewById(R.id.pager)
        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        //Lets create the navigation bar
        bottom_navigation?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_users -> {
                    mPager.setCurrentItem(0, true)
                    updateTitleAndDrawer(pagerAdapter.getItem(0))
                }
                R.id.action_posts -> {
                    mPager.setCurrentItem(1, true)
                    updateTitleAndDrawer(pagerAdapter.getItem(1))
                }
                R.id.action_gallery -> {
                    mPager.setCurrentItem(2, true)
                    updateTitleAndDrawer(pagerAdapter.getItem(2))
                }
            }
            true
        }
    }

    private fun setupViewPager() {
        pagerAdapter = ScreenPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(UsersFragment())
        pagerAdapter.addFragment(PostsFragment())
        pagerAdapter.addFragment(ImagesFragment())
        mPager.adapter = pagerAdapter
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    bottom_navigation?.menu?.getItem(0)?.isChecked = false
                }
                bottom_navigation?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = bottom_navigation?.menu?.getItem(position)
                updateTitleAndDrawer(pagerAdapter.getItem(position))
            }
        })
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }

    }


    override fun onClickedItemPostFragment(postId: Int) {
        if (isTwoPanel) {
            CommentsFragment.postIdObserver.value = postId
        } else {
            val bundle = Bundle()
            bundle.putInt("postId", postId)
            val fragment = CommentsFragment.newInstance(isTwoPanel)
            fragment.arguments = bundle
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
        }
    }

    fun updateTitleAndDrawer(fragment: Fragment) {
        when (fragment) {
            is PostsFragment -> {
                supportActionBar?.title = "Posts"
                title = getString(R.string.posts_title)
            }
            is ImagesFragment -> {
                supportActionBar?.title = "Images"
                title = "Images"
            }
            is UsersFragment -> {
                supportActionBar?.title = "User"
                title = "Users"
            }
        }
    }

}
