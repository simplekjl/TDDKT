package com.simplekjl.tddkt.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.simplekjl.tddkt.network.NetworkService
import com.simplekjl.tddkt.ui.MainUiModel
import com.simplekjl.tddkt.viewModels.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock

class RepositoryTest {

    //needed for LiveData Looper
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: NetworkService
    //mocks
    private val viewStateObserver: Observer<MainUiModel> = mock()
    private val mockDataRepository: Repository = mock()
//    private val mainViewModel = MainViewModel(mockRepository)


    @Test
    fun getPosts() {
    }

    @Test
    fun getComments() {
    }
}