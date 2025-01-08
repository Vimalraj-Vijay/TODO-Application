package com.vimalraj.todoapplication.todo.remote.data

import com.google.gson.annotations.SerializedName


data class Movies(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("posterURL")
    var posterURL: String? = null,
    @SerializedName("imdbId")
    var imdbId: String? = null
)
