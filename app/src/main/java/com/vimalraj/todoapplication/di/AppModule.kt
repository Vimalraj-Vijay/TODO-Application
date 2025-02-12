package com.vimalraj.todoapplication.di

import com.vimalraj.todoapplication.application.database.AppDatabase
import com.vimalraj.todoapplication.learning.api.LearningAPIClient
import com.vimalraj.todoapplication.learning.repository.LearnRepository
import com.vimalraj.todoapplication.learning.repository.LearnRepositoryImpl
import com.vimalraj.todoapplication.learning.usecase.LearnUseCase
import com.vimalraj.todoapplication.learning.usecase.LearnUseCaseImpl
import com.vimalraj.todoapplication.movies.api.MovieApiClient
import com.vimalraj.todoapplication.movies.repository.remote.MoviesRepository
import com.vimalraj.todoapplication.movies.repository.remote.MoviesRepositoryImpl
import com.vimalraj.todoapplication.movies.usecase.MovieUseCase
import com.vimalraj.todoapplication.movies.usecase.MovieUseCaseImpl
import com.vimalraj.todoapplication.multipleviewtype.api.MultiViewClient
import com.vimalraj.todoapplication.multipleviewtype.repository.MultiViewRepository
import com.vimalraj.todoapplication.multipleviewtype.repository.MultiViewRepositoryImpl
import com.vimalraj.todoapplication.multipleviewtype.usecase.MultiViewUseCase
import com.vimalraj.todoapplication.multipleviewtype.usecase.MultiViewUseCaseImpl
import com.vimalraj.todoapplication.todo.repo.TodoLocalRepository
import com.vimalraj.todoapplication.todo.repo.TodoLocalRepositoryImpl
import com.vimalraj.todoapplication.todo.usecase.TodoUseCase
import com.vimalraj.todoapplication.todo.usecase.TodoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesTodoLocalRepository(appDatabase: AppDatabase): TodoLocalRepository {
        return TodoLocalRepositoryImpl(
            todoDatabase = appDatabase
        )
    }

    @Singleton
    @Provides
    fun providesMoviesRepositoryImpl(
        movieApiClient: MovieApiClient,
        appDatabase: AppDatabase
    ): MoviesRepository {
        return MoviesRepositoryImpl(movieApiClient, appDatabase)
    }

    @Singleton
    @Provides
    fun providesMultiViewRepository(
        multiViewClient: MultiViewClient,
        appDatabase: AppDatabase
    ): MultiViewRepository {
        return MultiViewRepositoryImpl(multiViewClient, appDatabase)
    }

    @Singleton
    @Provides
    fun providesLearnRepository(
        learningAPIClient: LearningAPIClient
    ): LearnRepository {
        return LearnRepositoryImpl(learningAPIClient)
    }

    @Singleton
    @Provides
    fun providesMovieApiClient(retrofit: Retrofit): MovieApiClient {
        return retrofit.create(MovieApiClient::class.java)
    }

    @Singleton
    @Provides
    fun providesMultiTypeClient(retrofit: Retrofit): MultiViewClient {
        return retrofit.create(MultiViewClient::class.java)
    }

    @Singleton
    @Provides
    fun providesLearningAPIClient(retrofit: Retrofit): LearningAPIClient {
        return retrofit.create(LearningAPIClient::class.java)
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

    @Singleton
    @Provides
    fun providesMultiViewUseCase(multiViewRepository: MultiViewRepository): MultiViewUseCase {
        return MultiViewUseCaseImpl(
            multiViewRepository = multiViewRepository
        )
    }

    @Singleton
    @Provides
    fun providesLearnUseCase(learnRepository: LearnRepository): LearnUseCase {
        return LearnUseCaseImpl(
            learnRepository = learnRepository
        )
    }
}