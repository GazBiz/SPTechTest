package com.example.garethbizley.sptechtest.repository


import android.app.Application
import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.network.AlbumService
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Gaz Biz on 22/9/18.
 */

class AlbumRepository(application: Application) : IAlbumRepository {

    @Inject
    lateinit var albumService: AlbumService

    init {
        (application as AlbumsApplication).appComponent.inject(this)
    }

    override fun getAlbums(): Single<List<Album>> {
        return Single.create { emitter ->

            val albumCall = albumService.getAlbums()

            albumCall.enqueue(object : Callback<List<Album>> {

                override fun onFailure(call: Call<List<Album>>?, t: Throwable?) {
                    if (t != null)
                        emitter.onError(t)
                }

                override fun onResponse(call: Call<List<Album>>?, response: Response<List<Album>>?) {

                    if (response != null && response.isSuccessful && response.body() != null) {
                        emitter.onSuccess(response.body()!!)
                    }
                    else {
                        emitter.onError(Throwable("Response or Body of request was Null"))
                    }
                }
            })
        }
    }
}
