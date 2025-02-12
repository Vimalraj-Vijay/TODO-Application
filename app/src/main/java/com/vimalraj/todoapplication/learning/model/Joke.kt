package com.vimalraj.todoapplication.learning.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("joke")
    val joke: String = ""

)