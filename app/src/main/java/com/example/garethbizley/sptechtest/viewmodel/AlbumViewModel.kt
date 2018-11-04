package com.example.garethbizley.sptechtest.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.repository.IAlbumRepository
import com.example.garethbizley.sptechtest.util.Utils
import javax.inject.Inject

/**
 * Created by Gaz Biz on 22/9/18.
 */

const val STATE_IDLE = 99
const val STATE_LOADING = 88
const val STATE_READY = 77
const val STATE_ERROR = 66

class AlbumViewModel @Inject constructor(application: Application) : AndroidViewModel(application), IAlbumViewModel {

    override val albumsList = ArrayList<Album>()
    private val observableVMState = MutableLiveData<Int>()
    override var errorString: String? = null

    @Inject
    lateinit var albumRepository: IAlbumRepository

    init {
        (application as AlbumsApplication).appComponent.inject(this)
        observableVMState.value = STATE_IDLE
    }

    //region IAlbumViewModel functions
    override fun requestAlbums() {

        observableVMState.value = STATE_LOADING
        val albumsSingle = albumRepository.getAlbums()
        albumsSingle
                .doOnSuccess {
                    updateAlbumList(it)
                    observableVMState.value = STATE_READY
                }
                .onErrorReturn {
                    observableVMState.value = STATE_ERROR
                    errorString = it.message
                    observableVMState.value = STATE_IDLE
                    arrayListOf()
                }
                .subscribe()
    }
    //endregion

    private fun updateAlbumList(returnedList: List<Album>) {
        albumsList.clear()
        albumsList.addAll(returnedList)
        Utils.sortAlbumsListByTitle(albumsList)
    }

    override fun getObservableState(): MutableLiveData<Int> {
        return observableVMState
    }
}