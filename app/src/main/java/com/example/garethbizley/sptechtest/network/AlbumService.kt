package com.example.garethbizley.sptechtest.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Gaz Biz on 22/9/18.
 */
interface AlbumService {

    //TODO replace string with Serialized album object
    @GET("albums")
    fun getAlbums() : Call<List<String>>

    companion object {
        fun create(): AlbumService {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(AlbumService::class.java)
        }
    }

}