package com.simplekjl.tddkt.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    var postId: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
) : Parcelable