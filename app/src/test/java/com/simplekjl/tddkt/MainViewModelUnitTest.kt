package com.simplekjl.tddkt

import com.simplekjl.tddkt.data.Repository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations


/**
 * Main View model which holds the logic for all the interactions within the repository and the data states
 * This test doesn't need UI so we can Unit test
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelUnitTest {


    @Mock
    lateinit var mockRepository: Repository

    @Before
    fun setupStatesInViewModel() {
        MockitoAnnotations.initMocks(this)
    }


}
