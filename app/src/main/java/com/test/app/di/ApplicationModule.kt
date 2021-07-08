package com.test.app.di

import com.test.data.GameHandler
import com.test.data.repo.GameRepoImpl
import com.test.domain.GameRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideGameRepository(gameRepo: GameRepoImpl): GameRepo = gameRepo

    @Provides
    @Singleton
    fun provideGameHandler(): GameHandler {
        return GameHandler()
    }
}