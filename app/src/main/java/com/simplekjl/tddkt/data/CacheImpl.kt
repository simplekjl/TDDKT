package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.User

class CacheImpl : Cache {
    //cache
    private var userCache: Map<Int, User> = emptyMap()

    override fun getUserId(userId: Int): User {

        if (userCache[userId] != null) {
           return  userCache[userId] as User
        }
        return User()
    }
}