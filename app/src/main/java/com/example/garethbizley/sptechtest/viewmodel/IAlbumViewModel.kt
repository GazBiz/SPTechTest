package com.example.garethbizley.sptechtest.viewmodel

import com.example.garethbizley.sptechtest.contract.IViewModelCallback
import com.example.garethbizley.sptechtest.model.Album

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IAlbumViewModel {

    val albumsList: ArrayList<Album>
    fun requestAlbums()
    fun setCallbackListener(callbackListener: IViewModelCallback)
}