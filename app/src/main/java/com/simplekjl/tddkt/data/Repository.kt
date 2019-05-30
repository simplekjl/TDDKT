package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User


class Repository : DataRepository{
    override fun getUsers(): MutableList<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPosts(): MutableList<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComments(): MutableList<Comment> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}