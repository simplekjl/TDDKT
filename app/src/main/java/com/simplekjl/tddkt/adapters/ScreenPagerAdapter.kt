package com.simplekjl.tddkt.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */

class ScreenPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragmentList = mutableListOf<Fragment>()
    fun addFragment(fm: Fragment) {
        fragmentList.add(fm)
    }

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment = fragmentList[position]
}