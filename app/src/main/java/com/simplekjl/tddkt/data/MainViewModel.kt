package com.simplekjl.tddkt.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class MainViewModel : ViewModel(){


    val viewState: MutableLiveData<MainUiModel> = MutableLiveData()
    lateinit var repository: DataRepository

    fun getUsers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getPosts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getComments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}