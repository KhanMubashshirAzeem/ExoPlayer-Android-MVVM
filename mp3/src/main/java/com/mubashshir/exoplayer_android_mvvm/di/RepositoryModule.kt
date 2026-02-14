package com.mubashshir.exoplayer_android_mvvm.di

import com.mubashshir.exoplayer_android_mvvm.data.repository.SongRepository
import com.mubashshir.exoplayer_android_mvvm.data.repository.SongRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSongRepository(
        songRepositoryImpl: SongRepositoryImpl
    ): SongRepository
}