package com.simplekjl.tddkt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.tddkt.data.DataRepository
import com.simplekjl.tddkt.data.MainUiModel
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.network.NetworkService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Main View model which holds the logic for all the interactions within the repository and the data states
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {

    //needed for LiveData Looper
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: NetworkService
    //mocks
    private val viewStateObserver: Observer<MainUiModel> = mock()
    private val mockDataRepository: DataRepository = mock()
    private val mainViewModel = MainViewModel()

    @Before
    fun setupStatesInViewModel() {
        MockitoAnnotations.initMocks(this)
        mainViewModel.viewState.observeForever(viewStateObserver)
        mainViewModel.repository = mockDataRepository
    }

    //stubbing
    private fun stubUsersCollection() {
        var users: MutableList<User> = mutableListOf()
        for (i in 0..10) {
            users.add(User())
        }
        whenever(mockDataRepository.getUsers())
            .thenReturn(users)
    }

    private fun stubCommentsCollection() {
        var comments: MutableList<Comment> = mutableListOf()
        for (i in 0..10) {
            comments.add(Comment())
        }
        whenever(mockDataRepository.getComments())
            .thenReturn(comments)
    }

    private fun stubPostCollection() {
        var posts: MutableList<Post> = mutableListOf()
        for (i in 0..10) {
            posts.add(Post())
        }
        whenever(mockDataRepository.getPosts())
            .thenReturn(posts)
    }

    @Test
    fun initializeReturnUsers() {
        stubUsersCollection()  //arrange
        mainViewModel.getUsers()//act
        verify(mockDataRepository).getUsers()//assert

    }

    @Test
    fun initializeReturnPosts(){
        stubPostCollection()
        mainViewModel.getPosts() //act
        verify(mockDataRepository).getPosts()

    }

    @Test
    fun initializeReturnComments(){
        stubPostCollection()
        mainViewModel.getComments()
        verify(mockDataRepository).getComments()
    }
}
