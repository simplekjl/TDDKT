package com.simplekjl.tddkt.network

import androidx.lifecycle.LiveData
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
GET	/posts
GET	/posts/1
GET	/posts/1/comments
GET	/comments?postId=1
GET	/posts?userId=1
 */
interface NetworkService {

    @GET("users")
    fun getUsers(@Query("q") query: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): LiveData<List<User>>
    @GET("posts?userId={id}")
    fun getPostsByUserId(@Path("id") userId: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): LiveData<List<User>>

    @GET("posts")
    fun getPosts(@Query("q") query: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): LiveData<List<Post>>

    @GET("comments")
    fun getComments(@Query("q") query: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): LiveData<List<Comment>>

    @GET("comments?postId={id}")
    fun getCommentsByPostId(@Path("id") id: String): LiveData<List<Comment>>


    /**
     * Companion object to create the GithubApiService
     */
    companion object Factory {
        fun create(): NetworkService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }
}