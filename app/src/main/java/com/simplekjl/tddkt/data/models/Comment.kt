package com.simplekjl.tddkt.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    var postId: Float = 0.toFloat(),
    var id: Float = 0.toFloat(),
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
) : Parcelable