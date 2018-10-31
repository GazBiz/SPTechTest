package com.example.garethbizley.sptechtest.repository

import com.example.garethbizley.sptechtest.contract.IRepositoryCallback

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IAlbumRepository {

    fun getAlbumsFromApi()

    fun setRepoCallbackListener(callbackListener: IRepositoryCallback)
}