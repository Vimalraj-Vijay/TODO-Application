package com.vimalraj.todoapplication.multipleviewtype.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.vimalraj.todoapplication.application.database.ListConverter

@Entity
data class MultipleViewsResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("multipleViews")
    @TypeConverters(ListConverter::class)
    val multipleViews: List<MultipleViews>
)
