package com.simplekjl.tddkt.network

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
GET	/posts
GET	/posts/1
GET	/posts/1/comments
GET	/comments?postId=1
GET	/posts?userId=1
 */
interface NetworkService {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts?userId={id}")
    fun getPostsByUserId(@Path("id") userId: String): Call<List<Post>>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("comments")
    fun getComments(): Call<List<Comment>>

    @GET("comments?postId={id}")
    fun getCommentsByPostId(@Path("id") postId: String): Call<List<Comment>>


    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): NetworkService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }
}