package com.example.garethbizley.sptechtest.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.adapter.AlbumsRvAdapter
import com.example.garethbizley.sptechtest.viewmodel.AlbumViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //region Instantiate Class Variables
    private val TAG = MainActivity::class.java.name
    private lateinit var loadingDisplay: View
    private lateinit var getAlbumsButton: Button
    private lateinit var albumsRecyclerView: RecyclerView
    @Inject
    lateinit var viewModel: AlbumViewModel
    //endregion

    //region Overridden Activity Functions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.create().inject(this)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        getAlbumsButton = findViewById(R.id.get_albums_btn)
        loadingDisplay = findViewById(R.id.loading_display_ll)
        albumsRecyclerView = findViewById(R.id.albums_display_rv)

        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
        albumsRecyclerView.adapter = AlbumsRvAdapter(viewModel.albumsList, this)
        val dividerDecoration = DividerItemDecoration(albumsRecyclerView.context,
                LinearLayout.VERTICAL)
        albumsRecyclerView.addItemDecoration(dividerDecoration)

        if (viewModel.albumsList.isEmpty()) {
            getAlbumsButton.visibility = View.VISIBLE
        } else {
            getAlbumsButton.visibility = View.GONE
        }

        getAlbumsButton.setOnClickListener {
            viewModel.requestAlbums()
            getAlbumsButton.visibility = View.GONE
            loadingDisplay.visibility = View.VISIBLE
        }

        observeViewModel()
    }
    //endregion

    private fun onAlbumsReturned() {
        loadingDisplay.visibility = View.GONE
        albumsRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun onErrorReturned() {
        //log error for debugging
        Log.e(TAG, "Unknown Error getting albums")
        //inform user of error
        Toast.makeText(this, "Something went wrong! Try again?", Toast.LENGTH_SHORT).show()
        //allow another request attempt
        getAlbumsButton.visibility = View.VISIBLE
        loadingDisplay.visibility = View.GONE
    }

    private fun observeViewModel() {
        viewModel.getAlbumObservable().observe(this, Observer {

            if (it != null && it.isNotEmpty()) {
                viewModel.sortAlbumsByTitle()
                onAlbumsReturned()
            } else
                onErrorReturned()
        })
    }
}
