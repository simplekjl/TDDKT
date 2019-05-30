package com.simplekjl.tddkt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User


class MainViewModel : ViewModel(){


    val viewState: MutableLiveData<MainUiModel> = MutableLiveData()
    lateinit var repository: DataRepository

    fun init()
    {
        repository = Repository()
    }
    fun getUsers() : LiveData<List<User>> {
        return repository.getUsers()
    }

    fun getPosts() : LiveData<List<Post>>{
        return repository.getPosts()
    }

    fun getComments() : LiveData<List<Comment>> {
        return repository.getComments()
    }
}