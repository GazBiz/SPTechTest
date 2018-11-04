package com.example.garethbizley.sptechtest.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel

import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import com.example.garethbizley.sptechtest.util.Utils
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Gaz Biz on 22/9/18.
 */

class AlbumViewModel @Inject constructor(application: Application): AndroidViewModel(application), IAlbumViewModel {

    override val albumsList = ArrayList<Album>()

    @Inject
    lateinit var albumRepository: IAlbumRepository

    init {
        (application as AlbumsApplication).appComponent.inject(this)
    }

    //region IAlbumViewModel functions
    override fun requestAlbums(): Single<List<Album>> {

        val albumsSingle = albumRepository.getAlbums()
        albumsSingle.doOnSuccess {
             updateAlbumList(it)
         }
                 .subscribe()

        return albumsSingle
    }
    //endregion

    private fun updateAlbumList(returnedList: List<Album>){
        albumsList.clear()
        albumsList.addAll(returnedList)
        Utils.sortAlbumsListByTitle(albumsList)
    }
}