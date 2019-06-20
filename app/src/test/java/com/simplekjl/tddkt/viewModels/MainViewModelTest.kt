package com.simplekjl.tddkt.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.tddkt.RxImmediateSchedulerRule
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.models.Comment
import com.simplekjl.tddkt.data.models.Post
import com.simplekjl.tddkt.data.models.User
import com.simplekjl.tddkt.ui.ErrorMessage
import com.simplekjl.tddkt.ui.Success
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()
    @Rule
    @JvmField
    val ruleForLiveData = InstantTaskExecutorRule()

    @Mock
    lateinit var mockLiveDataObserver: Observer<Any>

    private var mockRepository: Repository = mock()

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(mockRepository)
    }


    @Test
    fun getRepository() {
        val repository: Repository? = null
        assertNull(repository)
    }

    @Test
    fun setRepository() {
        assertNotNull(viewModel.repository)
    }

    @Test
    fun getCompositeDisposable() {
        assertNotNull(viewModel.compositeDisposable)
    }

    @Test
    fun setCompositeDisposable() {
        val compositeDisposable = CompositeDisposable()
        viewModel.compositeDisposable = compositeDisposable
        assertNotNull(viewModel.compositeDisposable)
    }

    @Test
    fun getUsers_giveBackLivedData() {
        whenever(mockRepository.getUsers())
            .thenReturn(Observable.just(arrayListOf(User(), User())))

        viewModel.getUsersAndStoreInCache().observeForever(mockLiveDataObserver)

        assertNotNull(viewModel.getUsersAndStoreInCache())
    }

    @Test
    fun `Given Repository returns data, when getUsers() called,then update live data`() {
        val list: List<User> = arrayListOf(User(), User())
        //setting how the mock behaves
        whenever(mockRepository.getUsers())
            .thenReturn(Observable.just(list))
//            .thenReturn(Observable.create { emitter -> emitter.onNext(list)})
        //fire the test method
        val result = viewModel.getUsersAndStoreInCache().value as Success<*>
        assertEquals(list, result.data)

    }

    @Test
    fun `Given that Repository return Exception, when getUsers() called, then emptyList() in live Data`() {

        whenever(mockRepository.getUsers())
            .thenReturn(Observable.error(Throwable()))
        val result = viewModel.getUsersAndStoreInCache().value as ErrorMessage
        //assert
        assertNotNull(result)

    }

    @Test
    fun `Given the repository returns data, when getPosts() called, then update live data`() {
        val list: List<Post> = arrayListOf(Post(), Post())
        whenever(mockRepository.getPosts())
            .thenReturn(Observable.just(list))

        assertEquals(list, viewModel.getPosts().value)
    }

    @Test
    fun `When getComments() is called,return values to live data`() {
        val list: List<Comment> = arrayListOf(Comment(), Comment())
        whenever(mockRepository.getComments()).thenReturn(Observable.just(list))
        //assert
        assertEquals(list, viewModel.getComments().value)
    }

    @Test
    fun `When getComments() is called,return emptyList() to live data`() {
        val list: List<Comment> = emptyList()
        whenever(mockRepository.getComments()).thenReturn(Observable.error(Throwable()))
        //subscribe
        viewModel.getComments().observeForever(mockLiveDataObserver)
        //verify
        assertEquals(list, viewModel.getComments().value)
    }

    @Test
    fun `When getCommentsByPostId() called and it returns data to live data`() {
        val list: List<Comment> = arrayListOf(Comment(), Comment())
        whenever(mockRepository.getCommentsByPostId(1)).thenReturn(Observable.just(list))
        val state = viewModel.getCommentsByPostId(1).value as Success<*>
        assertEquals(list, state.data)
    }

    @Test
    fun `When getCommentsByPostId() called and return error `() {
        whenever(mockRepository.getCommentsByPostId(1)).thenReturn(Observable.error(Throwable()))
        val state = viewModel.getCommentsByPostId(1).value as ErrorMessage
        //assert
        assertNotNull(state)
    }

    @Test
    fun `When getUserById() is called and it replies with an User to Live Data`() {
        val user = User()
        whenever(mockRepository.getUserById(123)).thenReturn(Maybe.just(user))
        val result = viewModel.getUserById(123).value as Success<*>
        // assert
        assertEquals(user, result.data as User)
    }

    @Test
    fun `When getUserById() is called and returns onError with an empty User`() {
        whenever(mockRepository.getUserById(123)).thenReturn(Maybe.error(Throwable()))
        val result = viewModel.getUserById(123).value as ErrorMessage

        // assert
        assertNotNull(result)
    }

    @Test
    fun `When getPostsByUserID() is called and replies with a list to liveData`() {
        val list: List<Post> = arrayListOf(Post(), Post())
        whenever(mockRepository.getPostsByUserId("123")).thenReturn(Observable.just(list))
        //assert
        assertEquals(list, viewModel.getPostsByUserID(123).value)
    }

    @Test
    fun `When getPostsByUserID() is called and replies onError with emptyList to Live Data`() {
        val list: List<Post> = emptyList()
        whenever(mockRepository.getPostsByUserId("123")).thenReturn(Observable.error(Throwable()))

        assertEquals(list, viewModel.getPostsByUserID(123).value)
    }

    @Test
    fun `When getCommentsCountByPostId() is called and return an int to live data`() {
        val commentsCount = 6
        whenever(mockRepository.getCommentsCountByPostId(1)).thenReturn(Observable.just(commentsCount))
        assertEquals(commentsCount, viewModel.getCommentsCountByPostId(1).value)
    }

    @Test
    fun `When getCommentsCountByPostId() is called and return an error to live data`() {
        val commentCount = 6
        whenever(mockRepository.getCommentsCountByPostId(1)).thenReturn(Observable.error(Throwable()))
        assertNotEquals(commentCount, viewModel.getCommentsCountByPostId(1).value)
    }

    @Test
    fun clear() {
        viewModel.compositeDisposable = CompositeDisposable()
        viewModel.compositeDisposable.add(Observable.just("").subscribe())
        assertEquals(1, viewModel.compositeDisposable.size())
        viewModel.clear()
        assertEquals(0, viewModel.compositeDisposable.size())
    }
}