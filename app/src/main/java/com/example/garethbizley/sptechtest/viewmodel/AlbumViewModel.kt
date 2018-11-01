package com.example.garethbizley.sptechtest.viewmodel


import com.example.garethbizley.sptechtest.model.Album
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.contract.IRepositoryCallback
import com.example.garethbizley.sptechtest.contract.IViewModelCallback
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import com.example.garethbizley.sptechtest.util.Utils
import javax.inject.Inject

/**
 * Created by Gaz Biz on 22/9/18.
 */

class AlbumViewModel @Inject constructor(application: Application): AndroidViewModel(application), IAlbumViewModel, IRepositoryCallback {

    override val albumsList = ArrayList<Album>()
    private lateinit var callback: IViewModelCallback

    @Inject
    lateinit var albumRepository: IAlbumRepository

    init {
        (application as AlbumsApplication).appComponent.inject(this)
        albumRepository.setRepoCallbackListener(this)
    }

    //region IAlbumViewModel functions
    override fun requestAlbums() {
         albumRepository.getAlbumsFromApi()
    }

    override fun setCallbackListener(callbackListener: IViewModelCallback) {
        callback = callbackListener
    }
    //endregion

    //region IRepositoryCallback functions
    override fun onAlbumsReturned(albumList: List<Album>) {
        updateAlbumList(albumList)
        callback.onAlbumsReturned()
    }

    override fun onErrorReturned(message: String) {
        callback.onErrorReturned(message)
    }
    //endregion

    private fun updateAlbumList(returnedList: List<Album>){
        albumsList.clear()
        albumsList.addAll(returnedList)
        Utils.sortAlbumsListByTitle(albumsList)
    }
}