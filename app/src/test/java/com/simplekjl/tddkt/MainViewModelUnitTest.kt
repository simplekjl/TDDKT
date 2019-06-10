package com.simplekjl.tddkt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.ui.MainUiModel
import com.simplekjl.tddkt.viewModels.MainViewModel
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
    private val mockDataRepository: Repository = mock()
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
        val data: MutableLiveData<List<User>> = MutableLiveData()
        data.value = users
        whenever(mockDataRepository.getUsers())
            .thenReturn(data)
    }

    private fun stubCommentsCollection() {
        var comments: MutableList<Comment> = mutableListOf()
        for (i in 0..10) {
            comments.add(Comment())
        }
        val data: MutableLiveData<List<Comment>> = MutableLiveData()
        data.value = comments
        whenever(mockDataRepository.getComments())
            .thenReturn(data)
    }

    private fun stubPostCollection() {
        var posts: MutableList<Post> = mutableListOf()
        for (i in 0..10) {
            posts.add(Post())
        }
        val data: MutableLiveData<List<Post>> = MutableLiveData()
        data.value = posts
        whenever(mockDataRepository.getPosts())
            .thenReturn(data)
    }

    @Test
    fun initializeReturnUsers() {
        stubUsersCollection()  //arrange
        mainViewModel.getUsers()//act
        verify(mockDataRepository).getUsers()//assert

    }

    @Test
    fun initializeReturnPosts() {
        stubPostCollection()
        mainViewModel.getPosts() //act
        verify(mockDataRepository).getPosts()

    }

    @Test
    fun initializeReturnComments() {
        stubPostCollection()
        mainViewModel.getComments()
        verify(mockDataRepository).getComments()
    }
}
