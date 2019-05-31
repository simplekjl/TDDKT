package com.simplekjl.tddkt

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.simplekjl.tddkt.fragments.UsersFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun setup() {
        //instantiating the fragment
        rule.activity.setFragment(UsersFragment.newInstance())
    }

    @Test
    fun userFragmentIsShown() {
        onView(withId(R.id.fragment))
            .check(matches(isDisplayed()))
    }
}
