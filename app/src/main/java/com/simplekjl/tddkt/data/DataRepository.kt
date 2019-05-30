package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User

/** Communication layer to pass data **/

interface DataRepository {

    fun getUsers(): MutableList<User>
    fun getPosts(): MutableList<Post>
    fun getComments(): MutableList<Comment>
}