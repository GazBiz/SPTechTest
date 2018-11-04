package com.example.garethbizley.sptechtest.viewmodel

import com.example.garethbizley.sptechtest.model.Album
import io.reactivex.Single

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IAlbumViewModel {

    val albumsList: ArrayList<Album>
    fun requestAlbums(): Single<List<Album>>
}