package com.example.garethbizley.sptechtest.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IAlbumViewModel {

    val albumsList: ArrayList<Album>
    var errorString: String?
    fun requestAlbums()
    fun getObservableState(): MutableLiveData<Int>
}