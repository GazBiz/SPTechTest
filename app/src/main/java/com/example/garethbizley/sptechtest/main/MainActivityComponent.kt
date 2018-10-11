package com.example.garethbizley.sptechtest.main

import dagger.Component

/**
 * Created by Gaz Biz on 10/10/18.
 */

@Component
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)
}