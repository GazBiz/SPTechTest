package com.example.garethbizley.sptechtest.viewmodel


import com.example.garethbizley.sptechtest.model.Album
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.contract.IRepositoryCallback
import com.example.garethbizley.sptechtest.contract.IViewModelCallback
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import javax.inject.Inject


/**
 * Created by Gaz Biz on 22/9/18.
 */

class AlbumViewModel @Inject constructor(application: Application): AndroidViewModel(application), IAlbumViewModel, IRepositoryCallback {

    override val albumsList = ArrayList<Album>()
    lateinit var callback: IViewModelCallback

    @Inject
    lateinit var albumRepository: IAlbumRepository

    init {
        (application as AlbumsApplication).appComponent.inject(this)
        albumRepository.setRepoCallbackListener(this)
    }

    //todo move to BG thread for sorting operation
    private fun sortAlbumsListByTitle(){
        val sortedArrayList = ArrayList(albumsList.sortedWith(compareBy({ it.title })))
        albumsList.clear()
        albumsList.addAll(sortedArrayList)
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

    private fun updateAlbumList(albumsList: List<Album>){
        this.albumsList.clear()
        this.albumsList.addAll(albumsList)
        sortAlbumsListByTitle()
    }
}