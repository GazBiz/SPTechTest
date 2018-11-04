package com.example.garethbizley.sptechtest.repository

import com.example.garethbizley.sptechtest.model.Album
import io.reactivex.Single

/**
 * Created by Gaz Biz on 31/10/18.
 */
interface IAlbumRepository {

    fun getAlbums(): Single<List<Album>>
}