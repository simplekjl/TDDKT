package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User

/** Communication layer to pass data **/

interface DataRepository {

    fun getUsers(): Array<User>
    fun getPosts(): Array<Post>
    fun getComments(): Array<Comment>
}