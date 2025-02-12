package com.vimalraj.todoapplication.learning.model

import com.google.gson.annotations.SerializedName

data class Facts(
    @SerializedName("fact")
    val fact: String = ""

)