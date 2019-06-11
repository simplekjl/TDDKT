package com.simplekjl.tddkt.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(repository: Repository) : ViewModel() {

    var compositeDisposable: CompositeDisposable
    var repository: Repository = repository

    init {
        repository.init()
        compositeDisposable = CompositeDisposable()
    }

    fun getUsers(): LiveData<List<User>> {
        val data: MutableLiveData<List<User>> = MutableLiveData()
        compositeDisposable.add(
            repository.getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { listUsers -> data.value = listUsers },
                    { data.value = emptyList() })
        )
        return data

    }

    fun getPosts(): LiveData<List<Post>> {
        val data: MutableLiveData<List<Post>> = MutableLiveData()
        compositeDisposable.add(
            repository.getPosts().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe { listPosts ->
                    data.value = listPosts
                }
        )
        return data
    }

    fun getComments(): LiveData<List<Comment>> {
        val data: MutableLiveData<List<Comment>> = MutableLiveData()

        compositeDisposable.add(
            repository.getComments().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { listComments ->
                        data.value = listComments
                    },
                    {
                        //onError lambda
                        data.value = emptyList()
                    })
        )

        return data
    }

    fun getCommentsByPostId(postId: Int): LiveData<List<Comment>> {
        val data: MutableLiveData<List<Comment>> = MutableLiveData()
        compositeDisposable.add(
            repository.getCommentsByPostId(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({ listComments ->
                    data.value = listComments
                }, { data.value = emptyList() })
        )

        return data
    }

    fun getUserById(userId: Int): LiveData<User> {
        val data: MutableLiveData<User> = MutableLiveData()

        compositeDisposable.add(
            repository.getUserById(userId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { user -> data.value = user },
                    { data.value = null })
        )

        return data
    }

    fun getPostsByUserID(userId: Int): LiveData<List<Post>> {
        val data: MutableLiveData<List<Post>> = MutableLiveData()
        compositeDisposable.add(
            repository.getPostsByUserId(userId.toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { posts -> data.value = posts },
                    { data.value = emptyList() })
        )

        return data
    }

    fun getCommentsCountByPostId(postId: Int): LiveData<Int> {
        val data: MutableLiveData<Int> = MutableLiveData()

        compositeDisposable.add(
            repository.getCommentsCountByPostId(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { count -> data.value = count },
                    { data.value = 0 })
        )
        return data
    }

    fun clear() {
        compositeDisposable.clear()
    }
}