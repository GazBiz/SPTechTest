package com.example.garethbizley.sptechtest.repository


import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.contract.IRepositoryCallback
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.network.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Gaz Biz on 22/9/18.
 */
class AlbumRepository(application: Application): IAlbumRepository {

    @Inject
    lateinit var albumService: AlbumService

    lateinit var callbackListener: IRepositoryCallback

    init {
        (application as AlbumsApplication).appComponent.inject(this)
    }

    //region IAlbumRepository functions
    override fun getAlbumsFromApi(){
        val call = albumService.getAlbums()
        call.enqueue(object : Callback<List<Album>> {

            override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                callbackListener.onErrorReturned(t?.localizedMessage ?: "No Error Message given")
            }

            override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {
                callbackListener.onAlbumsReturned(response?.body() ?: emptyList())
            }
        })
    }

    override fun setRepoCallbackListener(callbackListener: IRepositoryCallback){
        this.callbackListener = callbackListener
    }
    //endregion
}