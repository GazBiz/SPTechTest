package com.example.garethbizley.sptechtest.dagger

import com.example.garethbizley.sptechtest.main.MainActivity
import com.example.garethbizley.sptechtest.repository.AlbumRepository
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Gaz Biz on 31/10/18.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: MainActivity)
    fun inject(target: AlbumViewModel)
    fun inject(target: AlbumRepository)
}