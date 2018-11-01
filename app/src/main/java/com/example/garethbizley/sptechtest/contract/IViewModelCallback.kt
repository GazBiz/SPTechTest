package com.example.garethbizley.sptechtest.contract

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IViewModelCallback {

    fun onAlbumsReturned()
    fun onErrorReturned(message: String)
}