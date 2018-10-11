package com.example.garethbizley.sptechtest.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.repository.AlbumRepository
import javax.inject.Inject

/**
 * Created by Gaz Biz on 22/9/18.
 */

class AlbumViewModel @Inject constructor(): ViewModel() {

    //todo ideally we don't want a second list here, just use livedata one
    val albumsList = ArrayList<Album>()

    //todo inject this
    private val albumRepository = AlbumRepository()

    //todo move to BG thread for sorting operation
    fun sortAlbumsByTitle(){
        val sortedArrayList = ArrayList(albumRepository.liveAlbums.value?.sortedWith(compareBy({ it.title })))
        albumsList.clear()
        albumsList.addAll(sortedArrayList)
    }

    fun requestAlbums() {
         albumRepository.getAlbums()
    }

    fun getAlbumObservable(): LiveData<List<Album>>{
        return albumRepository.liveAlbums
    }
}