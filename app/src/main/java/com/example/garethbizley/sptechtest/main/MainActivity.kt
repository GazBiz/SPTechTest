package com.example.garethbizley.sptechtest.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import android.arch.lifecycle.ViewModelProviders
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.adapter.AlbumsRvAdapter
import com.example.garethbizley.sptechtest.contract.IAlbumRequestListener
import com.example.garethbizley.sptechtest.model.Album
import com.example.garethbizley.sptechtest.repository.AlbumRepository
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel

class MainActivity : AppCompatActivity(), IAlbumRequestListener {

    //region Instantiate Class Variables
    private val TAG = MainActivity::class.java.name
    private val albumRepository = AlbumRepository(this)
    private lateinit var loadingDisplay: View
    private lateinit var getAlbumsButton: Button
    private lateinit var albumsRecyclerView: RecyclerView
    private lateinit var viewModel: AlbumViewModel
    //endregion

    //region Overridden Activity Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        getAlbumsButton = findViewById(R.id.get_albums_btn)
        loadingDisplay = findViewById(R.id.loading_display_ll)
        albumsRecyclerView = findViewById(R.id.albums_display_rv)

        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
        albumsRecyclerView.adapter = AlbumsRvAdapter(viewModel.albumsList, this)
        val dividerDecoration = DividerItemDecoration(albumsRecyclerView.context,
                LinearLayout.VERTICAL)
        albumsRecyclerView.addItemDecoration(dividerDecoration)

        if(viewModel.albumsList.isEmpty()){
            getAlbumsButton.visibility = View.VISIBLE
        }
        else {
            getAlbumsButton.visibility = View.GONE
        }

        getAlbumsButton.setOnClickListener {
            albumRepository.getAlbums()
            getAlbumsButton.visibility = View.GONE
            loadingDisplay.visibility = View.VISIBLE
        }
    }
    //endregion

    //region Overridden IAlbumRequestListener Functions
    override fun onSuccessfulRequest(returnedAlbumList: List<Album>) {
        loadingDisplay.visibility = View.GONE
        viewModel.albumsList.addAll(returnedAlbumList)
        viewModel.sortAlbumsByTitle()
        albumsRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onFailedRequest(errorMessage: String) {
        //log error for debugging
        Log.e(TAG, "Error getting albums: $errorMessage")
        //inform user of error
        Toast.makeText(this, "Something went wrong! Try again?", Toast.LENGTH_SHORT).show()
        //allow another request attempt
        getAlbumsButton.visibility = View.VISIBLE
        loadingDisplay.visibility = View.GONE
    }
    //endregion
}
