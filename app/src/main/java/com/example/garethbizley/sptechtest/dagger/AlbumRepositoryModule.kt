package com.example.garethbizley.sptechtest.dagger

import com.example.garethbizley.sptechtest.network.AlbumService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaz Biz on 31/10/18.
 */
@Module
class AlbumRepositoryModule {

    @Provides
    @Singleton
    fun providesAlbumService(): AlbumService = AlbumService.create()
}