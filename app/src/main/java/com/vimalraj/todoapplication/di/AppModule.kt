package com.vimalraj.todoapplication.di

import com.vimalraj.network.APIExecutor
import com.vimalraj.todoapplication.todo.database.TodoDatabase
import com.vimalraj.todoapplication.todo.remote.repo.MoviesRepository
import com.vimalraj.todoapplication.todo.remote.repo.MoviesRepositoryImpl
import com.vimalraj.todoapplication.todo.remote.usecase.MovieUseCase
import com.vimalraj.todoapplication.todo.remote.usecase.MovieUseCaseImpl
import com.vimalraj.todoapplication.todo.repo.TodoLocalRepository
import com.vimalraj.todoapplication.todo.repo.TodoLocalRepositoryImpl
import com.vimalraj.todoapplication.todo.usecase.TodoUseCase
import com.vimalraj.todoapplication.todo.usecase.TodoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesTodoLocalRepository(todoDatabase: TodoDatabase): TodoLocalRepository {
        return TodoLocalRepositoryImpl(
            todoDatabase = todoDatabase
        )
    }

    @Singleton
    @Provides
    fun providesMoviesRepositoryImpl(apiExecutor: APIExecutor): MoviesRepository {
        return MoviesRepositoryImpl(apiExecutor)
    }

    @Singleton
    @Provides
    fun providesTodoUseCase(todoLocalRepository: TodoLocalRepository): TodoUseCase {
        return TodoUseCaseImpl(
            todoLocalRepository = todoLocalRepository
        )
    }

    @Singleton
    @Provides
    fun providesMovieUseCaseImpl(moviesRepository: MoviesRepository): MovieUseCase {
        return MovieUseCaseImpl(
            moviesRepository = moviesRepository
        )
    }
}