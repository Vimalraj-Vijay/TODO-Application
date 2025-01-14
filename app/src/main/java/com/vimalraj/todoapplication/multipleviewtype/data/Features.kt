package com.vimalraj.todoapplication.multipleviewtype.data

import com.google.gson.annotations.SerializedName
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.IconType
import com.vimalraj.todoapplication.multipleviewtype.viewmodel.ViewType

data class Features(
    @SerializedName("featureId")
    val featureId: String? = null,
    @SerializedName("iconId")
    val iconId: IconType? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("buttonText")
    val buttonText: String? = null,
    @SerializedName("viewType")
    val viewType: ViewType? = null
)
