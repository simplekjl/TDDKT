package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.AlbumImage
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import io.reactivex.Maybe
import io.reactivex.Observable

/**
 *  Repository decides where to take data, it can be Database, Cache or Network
 */
class RepositoryImpl constructor(private val cache: Cache, private val network: Network) : Repository {
    override fun getImages(): Observable<List<AlbumImage>> {
        return network.getImages()
    }

    override fun storeUser(user: User) {
        cache.storeUser(user)
    }

    override fun getComments(): Observable<List<Comment>> {
        return network.getComments()
    }

    override fun getCommentsCountByPostId(postId: Int): Observable<Int> {
        return network.getCommentsCountByPostId(postId)
    }

    override fun getUsers(): Observable<List<User>> {
        return network.getUsers()
    }

    override fun getUserById(userId: Int): Maybe<User> {
        //checking from Cache
        return Maybe.concat(
            cache.getUserId(userId).toMaybe(),
            network.getUserById(userId).toMaybe()
        ).firstElement()

    }

    override fun getPostsByUserId(userId: String): Observable<List<Post>> {
        return network.getPostsByUserId(userId)
    }

    override fun getPosts(): Observable<List<Post>> {
        return network.getPosts()
    }

    override fun getCommentsByPostId(postId: Int): Observable<List<Comment>> {
        return network.getCommentsByPostId(postId)
    }

}