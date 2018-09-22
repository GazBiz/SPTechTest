package com.example.garethbizley.sptechtest.contract

import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 22/9/18.
 */
interface IAlbumRequestListener {

    fun onSuccessfulRequest(albumList: List<Album>)

    fun onFailedRequest(errorMessage: String)
}