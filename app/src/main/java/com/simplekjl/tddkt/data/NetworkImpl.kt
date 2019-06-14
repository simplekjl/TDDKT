package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.network.NetworkService
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkImpl(private val networkService: NetworkService) : Network {

    override fun getCommentsCountByPostId(postId: Int): Observable<Int> {
        return Observable.create { emitter ->
            networkService.getCommentsByPostId(postId).enqueue(object : Callback<List<Comment>> {
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    emitter.onNext(response.body()?.size ?: 0)
                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    emitter.onNext(0)
                }
            })
        }
    }


    override fun getUserById(userId: Int): Observable<User> {
        return Observable.create { emitter ->
            networkService.getUserById(userId).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    // in case is e,pty return default
                    emitter.onNext(User())
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    emitter.onNext(response.body() as User)
                }
            })
        }
    }

    override fun getPosts(): Observable<List<Post>> {
        return Observable.create { emitter ->
            networkService.getPosts().enqueue(object : Callback<List<Post>> {
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    emitter.onNext(emptyList())
                }

                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    emitter.onNext(response.body() ?: emptyList())
                }
            })
        }
    }


    override fun getUsers(): Observable<List<User>> {
        return Observable.create { emitter ->
            //Network
            networkService.getUsers().enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    emitter.onNext(emptyList())
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    emitter.onNext(response.body() ?: emptyList())
                }
            })
        }
    }

    override fun getCommentsByPostId(postId: Int): Observable<List<Comment>> {
        return Observable.create { emitter ->
            //Network
            networkService.getComments().enqueue(object : Callback<List<Comment>> {
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    emitter.onNext(emptyList())
                }

                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    emitter.onNext(response.body() ?: emptyList())
                }
            })
        }

    }

    override fun getPostsByUserId(userId: String): Observable<List<Post>> {
        return Observable.create { emitter ->
            //Network
            networkService.getPostsByUserId(userId).enqueue(object : Callback<List<Post>> {
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    emitter.onNext(emptyList())
                }
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    emitter.onNext(response.body() ?: emptyList())
                }
            })
        }
    }

    override fun getComments(): Observable<List<Comment>> {
        return Observable.create { emitter ->
            //Network
            networkService.getComments().enqueue(object : Callback<List<Comment>> {
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    emitter.onNext(emptyList())
                }
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    emitter.onNext(response.body() ?: emptyList())
                }
            })
        }
    }
}