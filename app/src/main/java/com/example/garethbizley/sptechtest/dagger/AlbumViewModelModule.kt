package com.example.garethbizley.sptechtest.dagger

import android.app.Application
import com.example.garethbizley.sptechtest.repository.AlbumRepository
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaz Biz on 31/10/18.
 */
@Module
class AlbumViewModelModule {

    @Provides
    @Singleton
    fun providesAlbumRepository(application: Application): IAlbumRepository = AlbumRepository(application)
}