package com.simplekjl.tddkt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.simplekjl.tddkt.data.DataRepository
import com.simplekjl.tddkt.data.MainUiModel
import com.simplekjl.tddkt.data.MainViewModel
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Main View model which holds the logic for all the interactions within the repository and the data states
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    //needed for LiveData Looper
    @Rule
    @JvmField
    var instantTaskExecutor = InstantTaskExecutorRule()

    //mocks
    private val viewStateObserver: Observer<MainUiModel> = mock()
    private val mockDataRepository: DataRepository = mock()
    private val mainViewModel = MainViewModel()

    @Before
    fun setupStatesInViewModel() {
        mainViewModel.viewState.observeForever(viewStateObserver)
        mainViewModel.repository = mockDataRepository
    }

    //stubbing
    private fun stubUsersCollection() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("users.json")
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        val json = String(bytes)

        val users: Array<User> = Gson().fromJson(json, object : TypeToken<User>() {}.type)
        whenever(mockDataRepository.getUsers())
            .thenReturn(users)
    }

    private fun stubCommentsCollection() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("comments.json")
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        val json = String(bytes)
        val comments: Array<Comment> = Gson().fromJson(json, object : TypeToken<Comment>() {}.type)
        whenever(mockDataRepository.getComments())
            .thenReturn(comments)
    }

    private fun stubPostCollection() {
        val inputStream = this.javaClass.classLoader.getResourceAsStream("posts.json")
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        val json = String(bytes)
        val posts: Array<Post> = Gson().fromJson(json, object : TypeToken<Post>() {}.type)
        whenever(mockDataRepository.getPosts())
            .thenReturn(posts)
    }

    @Test
    fun initializeReturnUsers() {
        stubUsersCollection()  //arrange
        mainViewModel.getUsers()//act
        verify(mockDataRepository).getUsers()//assert

    }
}
