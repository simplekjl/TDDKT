package com.simplekjl.tddkt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.simplekjl.tddkt.R
import com.simplekjl.tddkt.adapters.AlbumImageAdapter
import com.simplekjl.tddkt.data.models.AlbumImage
import com.simplekjl.tddkt.ui.ErrorMessage
import com.simplekjl.tddkt.ui.Loading
import com.simplekjl.tddkt.ui.Success
import com.simplekjl.tddkt.ui.UiState
import com.simplekjl.tddkt.viewModels.MainViewModel
import kotlinx.android.synthetic.main.generic_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImagesFragment : BaseFragment() {


    private val viewModel: MainViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.generic_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImages().observe(this, Observer { state ->
            render(state)
        })
    }

    override fun render(state: UiState) {
        when (state) {
            is Loading -> {
                showLoader()
            }
            is Success<*> -> {
                showItems()
                val list = state.data as List<AlbumImage>
                setAdapter(list)
            }
            is ErrorMessage -> {
                showErrorMessage(state.msg)
            }
        }
    }

    private fun setAdapter(images: List<AlbumImage>) {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        rv_generic.layoutManager = gridLayoutManager
        val adapter = AlbumImageAdapter(images)
        rv_generic.adapter = adapter

    }
}