package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.User

class CacheImpl : Cache {
    //cache
    private lateinit var userCache: Map<Int, User>

    fun init() {
        userCache = emptyMap()
    }

    fun getUserId(userId: Int): User? {
        val user: User? = null
        if (userCache[userId] != null) {
            userCache[userId]
        }
        return user
    }
}