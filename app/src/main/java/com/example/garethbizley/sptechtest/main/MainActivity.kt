package com.example.garethbizley.sptechtest.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.arch.lifecycle.ViewModelProviders

import com.example.garethbizley.sptechtest.AlbumsApplication
import com.example.garethbizley.sptechtest.R
import com.example.garethbizley.sptechtest.adapter.AlbumsRvAdapter
import com.example.garethbizley.sptechtest.viewmodel.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name
    @Inject
    lateinit var viewModel: IAlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as AlbumsApplication).appComponent.inject(this)

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        setupRecyclerView()

        get_albums_btn.setOnClickListener {
            viewModel.requestAlbums()
        }

        viewModel.getObservableState().observe(this, Observer {
            when (it) {
                STATE_IDLE -> showStateIdle()
                STATE_ERROR -> showStateError()
                STATE_LOADING -> showStateLoading()
                STATE_READY -> showStateReady()
                else -> showError("UNKNOWN STATE")
            }
        })
    }

    private fun setupRecyclerView() {
        albums_display_rv.layoutManager = LinearLayoutManager(this)
        albums_display_rv.adapter = AlbumsRvAdapter(viewModel.albumsList, this)
        val dividerDecoration = DividerItemDecoration(albums_display_rv.context,
                LinearLayout.VERTICAL)
        albums_display_rv.addItemDecoration(dividerDecoration)
    }

    private fun onAlbumsReturned() {
        albums_display_rv.adapter?.notifyDataSetChanged()

        if (albums_display_rv.adapter?.itemCount == 0)
            showError("No Albums were returned")
    }

    private fun showError(message: String?) {
        //log error for debugging
        Log.e(TAG, "Error getting albums: $message")
        //inform user of error
        Toast.makeText(this, "Something went wrong! Try again?", Toast.LENGTH_SHORT).show()
    }

    //region view update functions
    private fun showStateIdle() {
        loading_display_ll.visibility = View.GONE
        albums_display_rv.visibility = View.GONE
        get_albums_btn.visibility = View.VISIBLE
    }

    private fun showStateLoading() {
        loading_display_ll.visibility = View.VISIBLE
        albums_display_rv.visibility = View.GONE
        get_albums_btn.visibility = View.GONE
    }

    private fun showStateReady() {
        onAlbumsReturned()
        loading_display_ll.visibility = View.GONE
        albums_display_rv.visibility = View.VISIBLE
        get_albums_btn.visibility = View.GONE
    }

    private fun showStateError() {
        showError(viewModel.errorString)
        loading_display_ll.visibility = View.GONE
        albums_display_rv.visibility = View.GONE
        get_albums_btn.visibility = View.VISIBLE
    }
    //endregion
}
