package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.User
import io.reactivex.Single

interface Cache {
    fun getUserId(userId: Int): Single<User>
    fun storeUser(user: User)
}