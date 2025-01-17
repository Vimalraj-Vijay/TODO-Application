package com.vimalraj.todoapplication.multipleviewtype.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vimalraj.todoapplication.multipleviewtype.data.MultipleViewsResponse

@Dao
interface MultiViewTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleViews(multipleViewsResponse: MultipleViewsResponse)

    @Query("SELECT * FROM MultipleViewsResponse")
    suspend fun getMultipleViewsFromLocal(): MultipleViewsResponse?
}