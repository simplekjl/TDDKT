@file:Suppress("DEPRECATION")

package com.simplekjl.tddkt.data

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import com.simplekjl.tddkt.data.models.User
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CacheImplTest {

    @Mock
    lateinit var cache: MutableMap<Int, User>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUserId() {
        val user = User()
        whenever(cache[1]).thenReturn(user)
        assertEquals(user, cache[1])
    }

    @Test
    fun getUserNotFound() {
        whenever(cache[1]).thenReturn(null)
        assertNull(cache[1])
    }

    @Test
    fun storeUserTest() {
        val user = User()
        whenever(cache.set(1, user)).doAnswer { }
        whenever(cache.size).thenReturn(1)
        assert(cache.size == 1)
    }
}