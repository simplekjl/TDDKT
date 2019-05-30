package com.simplekjl.tddkt.data

import androidx.lifecycle.LiveData
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User

/** Communication layer to pass data **/

interface DataRepository {
    fun getPosts(): LiveData<List<Post>>
    fun getComments(): LiveData<List<Comment>>
    fun getUsers(): LiveData<List<User>>
    fun getCommentsByPostId(postId: String): LiveData<List<Comment>>
    fun getPostsByUserId(userId: String): LiveData<List<Post>>
}