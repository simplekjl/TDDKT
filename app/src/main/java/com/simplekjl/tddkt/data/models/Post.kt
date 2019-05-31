package com.simplekjl.tddkt.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var userId: Int = 0,
    var id: Int = 0,
    var title: String? = null,
    var body: String? = null
) : Parcelable