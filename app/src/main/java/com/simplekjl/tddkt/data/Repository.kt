package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import io.reactivex.Observable

/** Communication layer to pass data **/

interface Repository {
    fun init()
    //Comments
    fun getComments(): Observable<List<Comment>>

    fun getCommentsCountByPostId(postId: Int): Observable<Int>
    //Users
    fun getUsers(): Observable<List<User>>

    fun getUserById(userId: Int): Observable<User>
    //Post
    fun getPostsByUserId(userId: String): Observable<List<Post>>

    fun getPosts(): Observable<List<Post>>
    fun getCommentsByPostId(postId: Int): Observable<List<Comment>>
}