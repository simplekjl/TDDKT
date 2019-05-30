package com.simplekjl.tddkt.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var userId: Float = 0.toFloat(),
    var id: Float = 0.toFloat(),
    var title: String? = null,
    var body: String? = null
) : Parcelable