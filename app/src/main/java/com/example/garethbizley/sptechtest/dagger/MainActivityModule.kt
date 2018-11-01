package com.example.garethbizley.sptechtest.dagger

import android.app.Application
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel
import com.example.garethbizley.sptechtest.viewmodel.IAlbumViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaz Biz on 31/10/18.
 */
@Module
class MainActivityModule {

    @Provides
    @Singleton
    fun providesAlbumViewModel(application: Application): IAlbumViewModel = AlbumViewModel(application)
}