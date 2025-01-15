package com.vimalraj.todoapplication.multipleviewtype.data

import com.google.gson.annotations.SerializedName

data class MultipleViewsResponse(
    @SerializedName("multipleViews")
    val multipleViews: List<MultipleViews>
)
