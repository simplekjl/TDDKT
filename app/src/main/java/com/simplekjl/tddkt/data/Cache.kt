package com.simplekjl.tddkt.data

import com.simplekjl.tddkt.data.models.User

interface Cache {
    fun getUserId(userId: Int): User
}