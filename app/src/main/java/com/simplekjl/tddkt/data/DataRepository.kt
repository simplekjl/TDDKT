package com.simplekjl.tddkt.data

import androidx.lifecycle.LiveData
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User

/** Communication layer to pass data **/

interface DataRepository {

    //Comments
    fun getComments(): LiveData<List<Comment>>
    fun getCommentsCountByPostId(postId :Int) :LiveData<Int>

    //Users
    fun getUsers(): LiveData<List<User>>

    fun getUserById(userId: Int): LiveData<User>
    //Post
    fun getPostsByUserId(userId: String): LiveData<List<Post>>

    fun getPosts(): LiveData<List<Post>>
    fun getCommentsByPostId(postId: Int): LiveData<List<Comment>>
}