package com.vimalraj.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.vimalraj.todoapplication.application.database.AppDatabase
import com.vimalraj.todoapplication.movies.repository.local.MoviesDao
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
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase::class.java.name
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesTodoDao(todoDatabase: AppDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Singleton
    @Provides
    fun providesMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }

}