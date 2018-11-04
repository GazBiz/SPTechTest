package com.example.garethbizley.sptechtest.dagger

import android.app.Application
import com.example.garethbizley.sptechtest.network.AlbumService
import com.example.garethbizley.sptechtest.repository.AlbumRepository
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel
import com.example.garethbizley.sptechtest.viewmodel.IAlbumViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaz Biz on 31/10/18.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Application = app

    @Provides
    @Singleton
    fun providesAlbumService(): AlbumService = AlbumService.create()

    @Provides
    @Singleton
    fun providesAlbumRepository(): IAlbumRepository = AlbumRepository(app)

    @Provides
    @Singleton
    fun providesAlbumViewModel(): IAlbumViewModel = AlbumViewModel(app)
}