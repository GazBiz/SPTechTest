package com.example.garethbizley.sptechtest.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.contract.IAlbumRequestListener
import com.example.garethbizley.sptechtest.model.Album

class MainActivity : AppCompatActivity(), IAlbumRequestListener {

    //region Overridden Activity Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //endregion

    //region Overridden IAlbumRequestListener Functions
    override fun onSuccesfulRequest(albumList: List<Album>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailedRequest(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}
