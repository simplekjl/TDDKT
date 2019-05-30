package com.simplekjl.tddkt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository : DataRepository {
    override fun getPosts(): LiveData<List<Post>> {
        val posts: MutableLiveData<List<Post>> = MutableLiveData()
        //Database

        //Network
        NetworkService.create().getPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                posts.value = emptyList()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts.value = response.body()
            }
        })

        return posts
    }

    override fun getUsers(): LiveData<List<User>> {
        val users: MutableLiveData<List<User>> = MutableLiveData()
        //Database

        //Network
        NetworkService.create().getUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                users.value = emptyList()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                users.value = response.body()
            }
        })

        return users
    }

    override fun getCommentsByPostId(postId: String): LiveData<List<Comment>> {
        val comments: MutableLiveData<List<Comment>> = MutableLiveData()
        //Database

        //Network
        NetworkService.create().getComments().enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                comments.value = emptyList()
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                comments.value = response.body()
            }
        })

        return comments
    }

    override fun getPostsByUserId(userId: String): LiveData<List<Post>> {
        val posts: MutableLiveData<List<Post>> = MutableLiveData()
        //Database

        //Network
        NetworkService.create().getPostsByUserId(userId).enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                posts.value = emptyList()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                posts.value = response.body()
            }
        })

        return posts
    }

    override fun getComments(): MutableLiveData<List<Comment>> {
        val comments: MutableLiveData<List<Comment>> = MutableLiveData()
        //Database

        //Network
        NetworkService.create().getComments().enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                comments.value = emptyList()
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                comments.value = response.body()
            }
        })

        return comments
    }

}