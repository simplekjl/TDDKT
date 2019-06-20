package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.User
import io.reactivex.Single

class CacheImpl : Cache {

    //cache
    private var userCache: MutableMap<Int, User> = mutableMapOf()

    override fun getUserId(userId: Int): Single<User> {
        return Single.create { emitter ->
            run {
                if (userCache[userId] != null) {
                    emitter.onSuccess(userCache[userId] as User)
                    System.out.println("getting the user from cache")
                } else {

                    emitter.onError(Throwable())
                }
            }
        }
    }
    override fun storeUser(user: User) {
        val userId = user.id
        userCache[userId] = user
    }

}