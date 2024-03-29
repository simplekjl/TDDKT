package com.simplekjl.tddkt.fragments

import android.view.View
import androidx.fragment.app.Fragment
import com.simplekjl.tddkt.MainActivity
import com.simplekjl.tddkt.ui.UiState
import kotlinx.android.synthetic.main.generic_fragment.*

abstract class BaseFragment : Fragment() {


    fun showErrorMessage(msg: String) {
        error_message?.visibility = View.VISIBLE
        error_message?.text = msg
        rv_generic?.visibility = View.INVISIBLE
        progressBar?.visibility = View.INVISIBLE
    }

    fun showLoader() {
        error_message?.visibility = View.INVISIBLE
        rv_generic?.visibility = View.INVISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    fun showItems() {
        error_message?.visibility = View.INVISIBLE
        rv_generic?.visibility = View.VISIBLE
        progressBar?.visibility = View.INVISIBLE
    }

    fun updateActivityTitle(fragment: Fragment) {
        (activity as? MainActivity)?.updateTitleAndDrawer(fragment)
    }

    abstract fun render(state: UiState)
}