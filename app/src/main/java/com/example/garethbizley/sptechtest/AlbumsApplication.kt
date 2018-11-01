package com.example.garethbizley.sptechtest


import android.app.Application
import com.example.garethbizley.sptechtest.dagger.AppComponent
import com.example.garethbizley.sptechtest.dagger.AppModule
import com.example.garethbizley.sptechtest.dagger.DaggerAppComponent
/**
 * Created by Gaz Biz on 31/10/18.
 */
class AlbumsApplication: Application() {
    lateinit var appComponent: AppComponent

    private fun initDagger(app: AlbumsApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }
}