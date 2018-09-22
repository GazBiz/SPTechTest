package com.example.garethbizley.sptechtest.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.contract.IAlbumRequestListener
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.repository.AlbumRepository

class MainActivity : AppCompatActivity(), IAlbumRequestListener {

    private val TAG = MainActivity::class.java.name

    //region Overridden Activity Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albumRepository = AlbumRepository(this)

        //TODO remove, just a tester for now
        albumRepository.getAlbums()
    }
    //endregion

    //region Overridden IAlbumRequestListener Functions
    override fun onSuccessfulRequest(albumList: List<Album>) {
        //TODO remove, just printing to console for debugging
        for (album in albumList)
            Log.d(TAG, album.title)
    }

    override fun onFailedRequest(errorMessage: String) {
        //log error for debugging
        Log.e(TAG, "Error getting albums: $errorMessage")
        //inform user of error
        Toast.makeText(this, "Something went wrong! Try again?", Toast.LENGTH_SHORT).show()}
    //endregion
}
