package com.example.garethbizley.sptechtest.repository

import android.arch.lifecycle.MutableLiveData
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.network.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Gaz Biz on 22/9/18.
 */
class AlbumRepository {

    //todo inject this
    private val albumService = AlbumService.create()
    val liveAlbums = MutableLiveData<List<Album>>()

    fun getAlbums(){
        val call = albumService.getAlbums()
        call.enqueue(object : Callback<List<Album>> {

            override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                //TODO Handle request failure :(
            }

            override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {
                //TODO handle empty response with a retry or at least inform user
                liveAlbums.value = response?.body()
            }
        })
    }
}