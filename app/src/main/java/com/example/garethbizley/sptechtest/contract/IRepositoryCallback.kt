package com.example.garethbizley.sptechtest.contract

import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IRepositoryCallback {
    fun onAlbumsReturned(albumList: List<Album>)
    fun onErrorReturned(message: String)
}