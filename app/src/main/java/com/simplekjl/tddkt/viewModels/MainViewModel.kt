package com.simplekjl.tddkt.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.models.AlbumImage
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.ui.ErrorMessage
import com.simplekjl.tddkt.ui.Loading
import com.simplekjl.tddkt.ui.Success
import com.simplekjl.tddkt.ui.UiState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(var repository: Repository) : ViewModel() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUsersAndStoreInCache(): LiveData<UiState> {
//        List<User>
        val data: MutableLiveData<UiState> = MutableLiveData()
        compositeDisposable.add(
            repository.getUsers().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { listUsers ->
                        for (user in listUsers) repository.storeUser(user)
                        data.value = Success(listUsers as List<User>)
                    },
                    { data.value = ErrorMessage("") },
                    { /* On Complete */ },
                    { data.value = Loading })
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

    fun getCommentsByPostId(postId: Int): LiveData<UiState> {
        val data: MutableLiveData<UiState> = MutableLiveData()
        compositeDisposable.add(
            repository.getCommentsByPostId(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(
                    { listComments -> data.value = Success(listComments as List<Comment>) },
                    { data.value = ErrorMessage("Something went wrong ") },
                    { /** complete action **/ },
                    { data.value = Loading })
        )

        return data
    }

    fun getUserById(userId: Int): LiveData<UiState> {
        val data: MutableLiveData<UiState> = MutableLiveData()
        compositeDisposable.add(
            repository.getUserById(userId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(

                    { user -> data.value = Success(user) },
                    { data.value = ErrorMessage("Something went wrong") }
//                    { /** complete action **/ },
//                    { data.value = Loading })
                ))
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

    fun getImages(): LiveData<UiState> {
        val data: MutableLiveData<UiState> = MutableLiveData()
        compositeDisposable.add(
            repository.getImages().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(
                    { list -> data.value = Success<List<AlbumImage>>(list) },
                    { data.value = ErrorMessage("We couldn't reach our servers, please try again later.") },
                    { /* nothing to do */ },
                    { data.value = Loading })
        )
        return data
    }

    fun clear() {
        compositeDisposable.clear()
    }
}