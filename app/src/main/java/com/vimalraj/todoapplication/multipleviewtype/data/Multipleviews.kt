package com.vimalraj.todoapplication.multipleviewtype.data

import com.google.gson.annotations.SerializedName

data class MultipleViews(
    @SerializedName("sectionId")
    val sectionId: String? = null,
    @SerializedName("sectionName")
    val sectionName: String? = null,
    @SerializedName("sectionType")
    val sectionType: String? = null,
    @SerializedName("features")
    val features: List<Features>? = emptyList()
)
