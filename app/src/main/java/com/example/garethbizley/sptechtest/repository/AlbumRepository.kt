package com.example.garethbizley.sptechtest.repository

import com.example.garethbizley.sptechtest.contract.IAlbumRequestListener
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.network.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Gaz Biz on 22/9/18.
 */
class AlbumRepository (val albumRequestListener: IAlbumRequestListener) {

    private val albumService = AlbumService.create()

    fun getAlbums(){

        val call = albumService.getAlbums()
        call.enqueue(object : Callback<List<Album>> {

            override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                albumRequestListener.onFailedRequest(t?.message ?: "No error message returned :( ")
            }

            override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {
                //TODO handle empty response with a retry or at least inform user
                albumRequestListener.onSuccessfulRequest(response?.body() ?: listOf())
            }
        })
    }

}