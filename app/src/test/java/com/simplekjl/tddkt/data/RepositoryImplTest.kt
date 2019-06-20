package com.simplekjl.tddkt.data

import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.tddkt.RxImmediateSchedulerRule
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class RepositoryImplTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var mockNetwork: Network

    @Mock
    lateinit var mockCache: Cache


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getComments() {
        val expected = listOf(Comment())
        whenever(mockNetwork.getComments())
            .thenReturn(Observable.create {
                it.onNext(listOf(Comment()))
            })
        mockNetwork.getComments()
            .test()
            .assertNoErrors()
            .onNext(expected)
    }

    @Test
    fun getCommentsOnError() {
        whenever(mockNetwork.getComments())
            .thenReturn(Observable.create {
                it.onError(Throwable())
            })
        //assert
        mockNetwork.getComments()
            .test()
            .assertError { true }

    }

    @Test
    fun getCommentsCountByPostId() {
        val list = listOf(Comment())
        whenever(mockNetwork.getCommentsByPostId(1)).thenReturn(Observable.create { it.onNext(list) })
        mockNetwork.getCommentsByPostId(1)
            .test()
            .assertValue(list)
    }

    @Test
    fun getCommentsCountByPostIdError() {
        val error = Throwable()
        whenever(mockNetwork.getCommentsByPostId(1)).thenReturn(Observable.create { it.onError(error) })
        mockNetwork.getCommentsByPostId(1)
            .test()
            .assertError(error)
    }

    @Test
    fun getUsers() {
        val list = listOf(User(), User())
        whenever(mockNetwork.getUsers()).thenReturn(Observable.create { it.onNext(list) })
        mockNetwork.getUsers().test()
    }

    @Test
    fun getUsersError() {
        val error = Throwable()
        whenever(mockNetwork.getUsers()).thenReturn(Observable.create { it.onError(error) })
        mockNetwork.getUsers().test().assertError(error)
    }


    @Test
    fun getUserByIdFromCache() {
        val user = User()
        val user2 = User()
        user2.username = "non valid"

        whenever(mockCache.getUserId(1)).thenReturn(Single.create { it.onSuccess(user) })

        Maybe.concat(mockCache.getUserId(1).toMaybe(), Maybe.empty())
            .test()
            .assertNoErrors()
            .assertValue(user)
    }

    @Test
    fun getUserByIdFromNetwork() {
        val user2 = User()
        user2.username = "non valid"
        whenever(mockNetwork.getUserById(2)).thenReturn(Single.create { it.onSuccess(user2) })

        Maybe.concat(Maybe.empty(), mockNetwork.getUserById(2).toMaybe())
            .firstElement()
            .test()
            .assertValue(user2)
    }

    @Test
    fun getPostsByUserId() {
        val posts = listOf(Post())
        whenever(mockNetwork.getPostsByUserId("12")).thenReturn(Observable.create { it.onNext(posts) })

        mockNetwork.getPostsByUserId("12").test().assertValue(posts)
    }

    @Test
    fun getPostsByUserIdError() {
        val error = Throwable()
        whenever(mockNetwork.getPostsByUserId("12")).thenReturn(Observable.create { it.onError(error) })

        mockNetwork.getPostsByUserId("12").test().assertError(error)
    }

    @Test
    fun getPosts() {
        val posts = listOf(Post())
        whenever(mockNetwork.getPosts()).thenReturn(Observable.create { it.onNext(posts) })

        mockNetwork.getPosts().test().assertValue(posts)
    }

    @Test
    fun getPostsError() {
        val error = Throwable()
        whenever(mockNetwork.getPosts()).thenReturn(Observable.create { it.onError(error) })

        mockNetwork.getPosts().test().assertError(error)
    }

    @Test
    fun getCommentsByPostId() {
        val posts = listOf(Post())
        whenever(mockNetwork.getPostsByUserId("12")).thenReturn(Observable.create { it.onNext(posts) })

        mockNetwork.getPostsByUserId("12").test().assertValue(posts)
    }

    @Test
    fun getCommentsByPostIdError() {
        val error = Throwable()
        whenever(mockNetwork.getPostsByUserId("12")).thenReturn(Observable.create { it.onError(error) })

        mockNetwork.getPostsByUserId("12").test().assertError(error)
    }
}