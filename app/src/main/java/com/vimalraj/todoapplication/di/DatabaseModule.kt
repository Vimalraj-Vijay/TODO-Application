package com.vimalraj.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.vimalraj.todoapplication.todo.database.TodoDatabase
import com.vimalraj.todoapplication.todo.database.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            TodoDatabase::class.java.name
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

}