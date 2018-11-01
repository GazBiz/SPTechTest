package com.example.garethbizley.sptechtest.repository


import android.app.Application
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
const val RETRIES = 3

class AlbumRepository(application: Application): IAlbumRepository {

    var networkAttempts = 0

    @Inject
    lateinit var albumService: AlbumService

    private lateinit var callbackListener: IRepositoryCallback

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

                //Happy path, albums returned ok then update listener and return
                if(response != null && response.isSuccessful) {
                    callbackListener.onAlbumsReturned(response.body() ?: emptyList())
                    return
                }

                //If some issue, retry 3 times then fire an error
                if(++networkAttempts <= RETRIES) {
                    getAlbumsFromApi()
                }
                else
                    callbackListener.onErrorReturned("Network Timeout :(")
            }
        })
    }

    override fun setRepoCallbackListener(callbackListener: IRepositoryCallback){
        this.callbackListener = callbackListener
    }
    //endregion
}