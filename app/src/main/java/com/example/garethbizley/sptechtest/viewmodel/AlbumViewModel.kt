package com.example.garethbizley.sptechtest.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 22/9/18.
 */
class AlbumViewModel: ViewModel() {

    val albumsList = ArrayList<Album>()

    //Ideally would move to BG thread for sorting operation
    fun sortAlbumsByTitle(){
        val sortedArrayList = ArrayList(albumsList.sortedWith(compareBy({ it.title })))
        albumsList.clear()
        albumsList.addAll(sortedArrayList)
    }
}